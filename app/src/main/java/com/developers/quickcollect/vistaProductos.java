package com.developers.quickcollect;

import android.annotation.TargetApi;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.developers.quickcollect.BaseDatos.CollectDataBaseManager;


public class vistaProductos extends ActionBarActivity {


    /*para base de datos*/
    private CollectDataBaseManager manager;
    Cursor cursor;
    SimpleCursorAdapter adapter;

    private TextView tv1;
    private TextView tv2;
    private TextView tv3;

    private ListView lv1;


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_productos);


        lv1 = (ListView) findViewById(R.id.lv1);

        /*con estas  sentencias generamos la base da datos
        * getWritableDatabase()  lo que se realiza si la base de datos
        * no exite se crea la la base de datos y la devuelve en
        * modo escritura, si ya exite solamente la devuelve
        * */
        manager = new CollectDataBaseManager(this);

        /*campos que deseamos mostrar en list view */
        //String[] from = new String[]{manager.CN_NAME,manager.CN_COST};
        String[] from = new String[]{manager.CN_ID,manager.CN_NAME};
        /**/
        int[] to = new int[] {android.R.id.text1,android.R.id.text2};
        //int[] to = new int[] {android.R.id.text1,android.R.id.text2};
        //int[] to = new int[] {R.id.id,R.id.nombre};

        cursor = manager.cargarCursorContactos();

        adapter = new SimpleCursorAdapter(this,android.R.layout.two_line_list_item,cursor,from,to,0);
        lv1.setAdapter(adapter);

        /*codigopara seleccionar el que va modificar*/
        // accion cuando hacemos click en item para poder modificarlo o eliminarlo
        //
        //


        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {

                tv1 = (TextView) view.findViewById(android.R.id.text1);
                tv2 = (TextView) view.findViewById(android.R.id.text2);


                String aux_nombre = tv1.getText().toString();
                String aux_costo = tv2.getText().toString();



                Intent i = new Intent(getApplicationContext(), modificaciones.class);
                i.putExtra("id", aux_nombre);
                i.putExtra("nombre", aux_costo);

                startActivity(i);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_vista_productos, menu);
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

    public void entrarAregistro(View view ){
        Intent i = new Intent(this, regristro.class);
        startActivity(i);
    }

    public void regresar(View view){
        Intent i = new Intent(this, seleccion.class);
        startActivity(i);
    }
}
