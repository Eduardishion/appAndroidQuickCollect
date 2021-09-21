package com.developers.quickcollect;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class venta extends AppCompatActivity {

    private int totalproductos=0;
    private int subtotal=0;
    private String str = "";


    private TextView et1;
    private TextView et2;
    private TextView et3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venta);

         et1 = (TextView) findViewById(R.id.et1);
         et2 = (TextView) findViewById(R.id.et1);
         et3 = (TextView) findViewById(R.id.et1);

        //Intent intent = getIntent();
        Bundle extra = this.getIntent().getExtras();


        int  totalproductos = extra.getInt("total_productos");
        int  subtotal = extra.getInt("subtotal");
        String  str = extra.getString("str");

        et1.setText("El total de productos son: "+totalproductos);
        et2.setText("El total a pagar es:"+subtotal);
        et3.setText("Los productos fueron:"+str);

    }


    public void regresarTodo(View view ){
        Intent i = new Intent(this,seleccion.class);
        startActivity(i);
    }
}
