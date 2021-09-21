package com.developers.quickcollect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class mostrarVentas extends AppCompatActivity {

    EditText et1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_ventas);
        et1= (EditText)findViewById(R.id.et1);
    }

    public void muestraVentas(View view){

        /*
        InputStreamReader flujo=null;
        BufferedReader lector=null;
        try
        {
            flujo= new InputStreamReader(openFileInput("ventas.txt"));
            lector= new BufferedReader(flujo);
            String texto = lector.readLine();
            while(texto!=null)
            {
                et1.setText(texto);
                texto=lector.readLine();
            }
        }
        catch (Exception ex)
        {
            Log.e(" ", "Error al leer fichero desde memoria interna");
        }
        finally
        {
            try {
                if(flujo!=null)
                    flujo.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        */

        //String nomarchivo=et1.getText().toString();
        //nomarchivo=nomarchivo.replace('/','-');

        try {
            InputStreamReader archivo = new InputStreamReader(openFileInput("ventas.txt"));
            BufferedReader br = new BufferedReader(archivo);
            String linea = br.readLine();
            String todo = "";
            while (linea != null) {
                todo = todo + linea + "\n";
                linea = br.readLine();
            }
            br.close();
            archivo.close();
            et1.setText(todo);
        } catch (IOException e) {
        }

        //Toast.makeText(getApplicationContext(),"Operacion realizada...", Toast.LENGTH_SHORT).show();

    }

    public void nuevaVenta(View view){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }
}
