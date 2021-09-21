package com.developers.quickcollect;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.developers.quickcollect.BaseDatos.CollectDataBaseManager2;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class productosSeleccionados extends ActionBarActivity {

    private ArrayList<productos> productoYventidadDeventa = new ArrayList <productos>();
    private ListView lv1;
    private EditText et1;
    //private int posicion;

    /*para base de datos*/
    private CollectDataBaseManager2 manager2;
    private Cursor cursor2;
    private SimpleCursorAdapter adapter2;
    private TextView tv1;



    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos_seleccionados);
       // manager2 = new CollectDataBaseManager2(this);
        lv1 = (ListView) findViewById(R.id.lv1);
        et1 = (EditText) findViewById(R.id.et1);



        /*campos que deseamos mostrar  desde el cursor de base da datos*/
        /*
        String[] from = new String[]{manager2.CN_ID,manager2.CN_COST2};

        int[] to = new int[] {android.R.id.text1,android.R.id.text2};

        cursor2 = manager2.cargarCursor2();
        adapter2 = new SimpleCursorAdapter(this,android.R.layout.two_line_list_item,cursor2,from,to,0);
        lv1.setAdapter(adapter2);

        */



        //Bundle extra = getIntent().getExtras();

        //productoYventidadDeventa = extra.getParcelable("vectorProductos");

        /*para pasar un vectos de objetos por parametro atra vez de un activity*/
        Intent i = getIntent();
        productoYventidadDeventa=(ArrayList<productos>)i.getSerializableExtra("vectorObjetos");

        //ArrayList<productos> productoYventidadDeventa = (ArrayList<productos>) i.getSerializableExtra("vectorProductos");
        //vector=productoYventidadDeventa.clone();

       //para recuperar datos de vector de objetos
       /*
        int val=productoYventidadDeventa.size();
        String vector[] = new String[val];



        for (int x=0;x<productoYventidadDeventa.size();x++){
            vector[x]= String.valueOf(productoYventidadDeventa.get(x));
        }


        for(int x=0; x< val; x++) {
            vector[x]= String.valueOf(productoYventidadDeventa.get(x));
        }
        */

        //ArrayAdapter <Product> adapter = new ArrayAdapter<Product>(this, android.R.layout.simple_list_item_1, items);
       // ArrayAdapter<productos> Adapter = new ArrayAdapter<productos>(this,android.R.layout.simple_list_item_1, productoYventidadDeventa);
       // lv1.setAdapter(Adapter);

        final productoAdapter Adapter = new productoAdapter(this,productoYventidadDeventa);
        lv1.setAdapter(Adapter);




/*       vector estatico
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                //tv3.setText("Costo  de "+ lv1.getItemAtPosition(position) + " es de"+ costo [position]);
                //tv5.setText("" + lv1.getItemAtPosition(position));
                //posicion =  position;
                //lv1.getItemAtPosition(position);

                AlertDialog.Builder adb=new AlertDialog.Builder(productosSeleccionados.this);
                adb.setTitle("Emiliminar?");
                adb.setMessage("Are you sure you want to delete " + position);
                final int positionToRemove = position;

                adb.setNegativeButton("Cancel", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        lv1.remove(positionToRemove);
                        adapter.notifyDataSetChanged();
                    }});
                adb.show();

            }
        });
    */
    }

   private  class productoAdapter extends ArrayAdapter<productos> {

        Activity context;
        ArrayList<productos> datos;

        productoAdapter (Activity context,ArrayList <productos> datos){
            super(context, R.layout.formatofila,productoYventidadDeventa);
            this.context = context;
            this.datos = datos;
        }
        @Override
        public View getView(int position, View convertView,ViewGroup parent){
            LayoutInflater mInflater = LayoutInflater.from(getContext());
            View mItem = mInflater.inflate(R.layout.formatofila, null);

            TextView producto = (TextView) mItem.findViewById(R.id.producto);
            producto.setText(datos.get(position).getProducto());


            TextView costo = (TextView) mItem.findViewById(R.id.costo);
            costo.setText(String.valueOf(datos.get(position).getCosto()));


            TextView cantidad = (TextView) mItem.findViewById(R.id.cantidad);
            cantidad.setText(String.valueOf(datos.get(position).getCantidad()));

            return mItem;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_productos_seleccionados, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*metodo para regresar a activity principal de producto*/
    public void regresaPrincipal(View view){
        Intent i = new Intent(this,MainActivity.class);
            /*se regresa el vector de productos al activity principal para poder cargar mas elementos*/
            //i.putExtra("vectorProductos2",productoYventidadDeventa);
            //i.putExtra("bandera",bandera);
        startActivity(i);
    }

    public void hacerCuenta(View view){

        /*/para loos ficheros*/
        OutputStreamWriter escritor=null;

        /*para operaciones*/
        int tam=productoYventidadDeventa.size();
        int totalproductos=0;
        int subtotal=0;
        String str = "";


        for (int i=0;i<tam;i++){
            subtotal=subtotal+productoYventidadDeventa.get(i).getCosto()*productoYventidadDeventa.get(i).getCantidad();
            totalproductos=totalproductos+productoYventidadDeventa.get(i).getCantidad();
            str = ""+str+productoYventidadDeventa.get(i).getProducto()+"\n";
        }
        /*se genera la cadenaa mostrar*/

        String sentencia = fechaHoraActual()+", \nLa cantidad de productos son: "+totalproductos
                +"\nEl total a pagar fue:"+subtotal+" $"+"\nLos pruductos fueron:\n"+str+"\n";


        //String sentencia = fechaHoraActual()+", La cantidad de productos son: "+totalproductos
        //        +",El total a pagar fue:"+subtotal+", los pruductos fueron:\n"+str;

        /*
        try
        {
            escritor=new OutputStreamWriter(openFileOutput("ventas.txt", Context.MODE_PRIVATE));
            escritor.write(sentencia);
        }
        catch (Exception ex)
        {
            Log.e(" ", "Error al escribir fichero a memoria interna");
        }
        finally
        {
            try {
                if(escritor!=null)
                    escritor.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
*/

        try {
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("ventas.txt", Activity.MODE_APPEND));
            archivo.write(sentencia);
            archivo.flush();
            archivo.close();
        } catch (IOException e) {
            Log.e(" ", "Error al escribir fichero a memoria interna");
        }

        Toast.makeText(getApplicationContext(),fechaHoraActual()+"El total a pagar es:" + subtotal + " de  los "+  totalproductos +" productos", Toast.LENGTH_SHORT).show();

        Intent i = new Intent(this, venta.class);
            i.putExtra("total_productos",totalproductos);
            i.putExtra("pago",subtotal);
            i.putExtra("productos",str);
        startActivity(i);

    }

    public String fechaHoraActual(){
        return new SimpleDateFormat( "yyyy-MM-dd_HH:mm:ss", java.util.Locale.getDefault()).format(Calendar.getInstance() .getTime());
    }



    public void irVentas(View view ){
        Intent i = new Intent(this, mostrarVentas.class);
        startActivity(i);
    }






}
