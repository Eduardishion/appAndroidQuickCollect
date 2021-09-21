package com.developers.quickcollect;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.developers.quickcollect.BaseDatos.CollectDataBaseManager;


public class regristro extends ActionBarActivity {

    private TextView tv11;
    private TextView tv22;
    private TextView tv33;
    private CollectDataBaseManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regristro);

        tv11 = (TextView) findViewById(R.id.tv1);
        tv22 = (TextView) findViewById(R.id.tv2);
        tv33 = (TextView) findViewById(R.id.tv3);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_regristro, menu);
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



    public void insertar(View view){
        /*instacia para acesar a metodos para insertat elininar actualizar*/
        manager = new CollectDataBaseManager(this);
        String nombre = tv11.getText().toString();
        String costo = tv22.getText().toString();
        String descripcion = tv33.getText().toString();

        if(tv11.length()== 0 ){
            Toast notificacion=Toast.makeText(this,"Lo siento no has ingresado el producto.",Toast.LENGTH_LONG);
            notificacion.show();
        }else{
            if(tv22.length()== 0 ){
                Toast notificacion=Toast.makeText(this,"Lo siento no has ingrasado el costo .",Toast.LENGTH_LONG);
                notificacion.show();
            }else{
                if(tv33.length()== 0 ){
                    Toast notificacion=Toast.makeText(this,"Lo siento no has ingresado la descripcion.",Toast.LENGTH_LONG);
                    notificacion.show();
                }else{
                    manager.insertar(nombre, costo, descripcion);
                    Toast.makeText(getApplicationContext(),"El producto ha sido cargado exitosamente",Toast.LENGTH_SHORT).show();
                    //para limpiar los textview
                    tv11.setText("");
                    tv22.setText("");
                    tv33.setText("");
                }
            }
        }

    }

    public void regresaSeleccion(View view ){
        Intent i = new Intent(this, vistaProductos.class);
        startActivity(i);
    }



}
