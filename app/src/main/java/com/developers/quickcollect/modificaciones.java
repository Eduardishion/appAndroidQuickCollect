package com.developers.quickcollect;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.developers.quickcollect.BaseDatos.CollectDataBaseManager;


public class modificaciones extends ActionBarActivity {

    private TextView tv1;
    private TextView tv2;
    private TextView tv3;

    private long id;
    /*para base de datos*/
    private CollectDataBaseManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificaciones);



        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);

        Intent i = getIntent();
        String _id = i.getStringExtra("id");
        String nombre = i.getStringExtra("nombre");


        id = Long.parseLong(_id);
        tv1.setText(nombre);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_modificaciones, menu);
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

    public void modificarRegistros(View view){
        manager = new CollectDataBaseManager(this);
            String nombre = tv1.getText().toString();
            String costo = tv2.getText().toString();
            String descrip = tv3.getText().toString();
        manager.actualizarDatos(id,nombre,costo,descrip);
        this.returnHome();
    }

    public void eliminarRegistro(View view){
        manager = new CollectDataBaseManager(this);
        manager.eliminarPorID(id);
        this.returnHome();

    }

    public void returnHome() {
        Intent i = new Intent(getApplicationContext(), vistaProductos.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}
