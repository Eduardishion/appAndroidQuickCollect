package com.developers.quickcollect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class verificacion extends AppCompatActivity {


    private EditText et1;
    private EditText et2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificacion);

        et1 = (EditText)findViewById(R.id.et1);
        et2 = (EditText)findViewById(R.id.et2);
    }


    public void verificar(View view){
        String nombre = et1.getText().toString();
        String contra = et2.getText().toString();

        if(!nombre.equals("admin") == true){
            Toast notificacion=Toast.makeText(this,"Lo siento pero ingresa tu nombre no es el correcto.",Toast.LENGTH_LONG);
            notificacion.show();
        }else{
            if(!contra.equals("123456") ){
                Toast notificacion=Toast.makeText(this,"Lo siento pero ingresa una contrase�a correcta.",Toast.LENGTH_LONG);
                notificacion.show();
            }else{
                if(nombre.equals("admin") == true && contra.equals("123456")== true){
                    Toast notificacion=Toast.makeText(this,"Bienvenido es hora de entrar.",Toast.LENGTH_LONG);

                    Intent i = new Intent(this,vistaProductos.class);
                    startActivity(i);
                }

            }
        }

    }

}
