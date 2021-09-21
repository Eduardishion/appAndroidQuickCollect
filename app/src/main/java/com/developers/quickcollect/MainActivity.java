package com.developers.quickcollect;

import android.annotation.TargetApi;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.developers.quickcollect.BaseDatos.CollectDataBaseManager;
import com.developers.quickcollect.BaseDatos.CollectDataBaseManager2;

import java.util.ArrayList;

//import com.developers.quickcollect.BaseDatos.CollectCursorAdapter;


public class MainActivity extends ActionBarActivity  {

    /*variable glogal */
    private int total=0;
    private int valor=0;
    private int cost ;
    private String produc;
    private int cantidad;
    private int bandera=0;

    /*variables para seekbar*/
    private TextView tv2;
    private SeekBar seekBar1;



    /*variables para lista*/
    private TextView temp1;
    private TextView temp2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;



    private ListView lv1;

    /*para base de datos*/
    private CollectDataBaseManager manager;
    private CollectDataBaseManager2 manager2;
    private Cursor cursor;
    private SimpleCursorAdapter adapter;



    private String[] producto = { "torta cubana", "torta chilena", "Torta portuguesa", "Torta iralndesa",
            "Torta tres quesos", "Torta ecuador", "Torta Brasil", "Torta Colombia", " Torta Venezuela", "Torta Uruguay" };

    private String[] costo = { "35", "50", "60", "20", "15", "50", "93", "42", "36", "96" };

    private ArrayList <productos>  productoYventidadDeventa = new ArrayList <productos>();
    private ArrayList <productos>  productoYventidadDeventaTMP = new ArrayList <productos>();




    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




//        if(productoYventidadDeventa != null){
//            //si el temporal esta vacio
//            Toast.makeText(getApplicationContext(), "Agrega algun producto a la lista de venta", Toast.LENGTH_SHORT).show();
//        }else{
//            //cuando el vector temporal ya esta lleno
//            //productoYventidadDeventa = extra.getParcelable("vectorProductos");
//            if(productoYventidadDeventaTMP != null){
//                Intent i = getIntent();
//                productoYventidadDeventaTMP=(ArrayList<productos>)i.getSerializableExtra("vectorProductos");
//            }else{
//
//            }
//            Toast.makeText(getApplicationContext(), "Deseas agragar algo mas a la lista de venta", Toast.LENGTH_SHORT).show();
//            productoYventidadDeventa=productoYventidadDeventaTMP;
//        }

        tv3 = (TextView) findViewById(R.id.tv3);
        tv4 = (TextView) findViewById(R.id.tv4);
        tv5 = (TextView) findViewById(R.id.tv5);
        tv2 = (TextView) findViewById(R.id.tv2);

        //btn = (ImageButton) findViewById(R.id.imabtn);
        lv1 = (ListView) findViewById(R.id.lv1);

        seekbar();

        /*con estas  sentencias generamos la base da datos
        * getWritableDatabase()  lo que se realiza si la base de datos
        * no exite se crea la la base de datos y la devuelve en
        * modo escritura, si ya exite solamente la devuelve
        * */
        manager = new CollectDataBaseManager(this);
        manager2 = new CollectDataBaseManager2(this);


        /*campos que deseamos mostrar*/
        String[] from = new String[]{manager.CN_NAME,manager.CN_COST};
        /**/
        int[] to = new int[] {android.R.id.text1,android.R.id.text2};

        cursor = manager.cargarCursorContactos();
        adapter = new SimpleCursorAdapter(this,android.R.layout.two_line_list_item,cursor,from,to,0);
        lv1.setAdapter(adapter);

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {

                temp1 = (TextView) view.findViewById(android.R.id.text1);
                temp2 = (TextView) view.findViewById(android.R.id.text2);


                String aux_nombre = temp1.getText().toString();
                String aux_costo = temp2.getText().toString();

                tv3.setText(aux_costo);
                tv5.setText(aux_nombre);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

        if (id==R.id.registro) {
            Intent i = new Intent(this, regristro.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    public void seekbar(){
        tv2 = (TextView) findViewById(R.id.tv2);
        seekBar1 = (SeekBar)findViewById(R.id.seekBar1);

        tv2.setText(""+seekBar1.getProgress());
        tv2.setText("1");

        seekBar1.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {

                    int progress_value;

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progress_value = progress;
                        tv2.setText("" + progress);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        tv2.setText("" + progress_value);
                    }
                }

        );
    }


    /*Metodo para sumar los productos leccionados */


    public void sumarizarYCargarLista(View view){
        productos product= new productos();

        String c = tv3.getText().toString();
        int cv = Integer.parseInt(c);

        String p = tv5.getText().toString();

        String ct = tv2.getText().toString();
        int ctt = Integer.parseInt(ct);

        Log.i("El costo :" + tv3.getText().toString(), "," + tv5.getText().toString() + " " + tv2.getText().toString());

        product.setCosto(cv);
        product.setProducto(p);
        product.setCantidad(ctt);
        productoYventidadDeventa.add(product);

        /*directo a la base de datos*/

        String cos = tv3.getText().toString();
        String nom = tv5.getText().toString();
        String can = tv2.getText().toString();
       // Log.i("El costo :"+tv3.getText().toString(),","+tv5.getText().toString()+" "+tv2.getText().toString());
        //CollectDataBaseManager managr  = new CollectDataBaseManager(this);
        manager2.insertar2(nom,cos,can);

        Toast.makeText(getApplicationContext(),"Se a cargado: "+ tv5.getText().toString() + "\n a la lista",Toast.LENGTH_SHORT).show();

        tv3.setText("costo");
        tv5.setText("producto");
        tv2.setText("1");
        //Log.i("El costo :" + tv3.getText().toString(), "," + tv5.getText().toString() + " " + tv2.getText().toString());
        //para mostrar un previo de total a pagar

        int valor = Integer.parseInt(c);
        total =  total +  valor * ctt;
        String resul= String.valueOf(total);
        tv4.setText(resul);

       // product=null;

    }



    public void mostrar(View view){
        Intent i = new Intent(this, productosSeleccionados.class);
        //vector de objetos
        i.putExtra("vectorObjetos", productoYventidadDeventa);
        startActivity(i);
    }



    /*metodo para cargar datos en el vector que regresesa de activity dos*/
    /*
    public void recargar(){
        Intent i = getIntent();
        productoYventidadDeventaTMP=(ArrayList<productos>)i.getSerializableExtra("vectorProductos2");

        for(int x=0;x<productoYventidadDeventaTMP.size();x++){
            product.setCosto(productoYventidadDeventaTMP.get(x).getCosto());
            product.setProducto(productoYventidadDeventaTMP.get(x).getProducto());
            productoYventidadDeventa.add(product);
        }
    }
    */



}
