package com.developers.quickcollect.BaseDatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by Developer on 14/12/2015.
 * clase encargada de generar solo la base de datos
 */
public class CollectDbHelper extends SQLiteOpenHelper {

    /*Nombre de la base de datos y vercion de la baseda datos*/
    private static final String DB_NAME = "ventasproductos.sqlite";
    private static final int DB_SHEME_VERCION = 1;

    /*constructor para crear base de datos*/
    public CollectDbHelper(Context contexto) {
        super(contexto,DB_NAME,null,DB_SHEME_VERCION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CollectDataBaseManager.CREATE_TABLE);
        db.execSQL(CollectDataBaseManager2.CREATE_TABLE2);
       // db.execSQL(DATABASE_CREATE_MULTIPLE_TABLES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("DROP TABLE IF EXISTS"+ CREATE_TABLE);
        onCreate(db);
    }
     /*
     Scripts que se utilizan para hacer una tabla

     emeplos de los scrips usar
     *  String sqlCreate = "CREATE TABLE productos (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "nombre TEXT , " +
            "costo INTEGER)";
     *
     * db.execSQL( "CREATE TABLE HIPOTECA(" +
                " _id INTEGER PRIMARY KEY," +
                " hip_nombre TEXT NOT NULL, " +
                " hip_condiciones TEXT, " +
                " hip_contacto TEXT," +
                " hip_email TEXT," +
                " hip_telefono TEXT," +
                " hip_observaciones TEXT)" );

     *
     *  sqlite> CREATE TABLE COMPANY(
               ID INTEGER PRIMARY KEY   AUTOINCREMENT,
               NAME           TEXT      NOT NULL,
               AGE            INT       NOT NULL,
               ADDRESS        CHAR(50),
               SALARY         REAL
     *
     * db.execSQL("INSERT INTO HIPOTECA(_id, hip_nombre) VALUES(1,'Santander')");
        db.execSQL("INSERT INTO HIPOTECA(_id, hip_nombre) VALUES(2,'BBVA')");
        db.execSQL("INSERT INTO HIPOTECA(_id, hip_nombre) VALUES(3,'La Caixa')");
        db.execSQL("INSERT INTO HIPOTECA(_id, hip_nombre) VALUES(4,'Cajamar')");
        db.execSQL("INSERT INTO HIPOTECA(_id, hip_nombre) VALUES(5,'Bankia')");
        db.execSQL("INSERT INTO HIPOTECA(_id, hip_nombre) VALUES(6,'Banco Sabadell')");
        db.execSQL("INSERT INTO HIPOTECA(_id, hip_nombre) VALUES(7,'Banco Popular')");
     *
     *
        Log.i(this.getClass().toString(), "Datos iniciales HIPOTECA insertados");

        Log.i(this.getClass().toString(), "Base de datos creada");

     *
     *
     * */

}
