package com.developers.quickcollect.BaseDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Developer on 31/12/2015.
 */
public class CollectDataBaseManager2 {

    /*nombre de la tabla2*/
    public static final String TABLE_NAME2 = "listaVenta";

    /*nombre de los campos de la tabla2*/
    public static final String CN_ID = "_id";
    public static final String CN_NAME2 = "nombre2";
    public static final String CN_COST2 = "costo2";
    public static final String CN_CANTI2 = "cantidad2";


    /*String del script en sql de para generrar la tabla2*/
    public static final String CREATE_TABLE2 = "CREATE TABLE "+TABLE_NAME2+" ("
            + CN_ID +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
            + CN_NAME2 +" TEXT,"
            + CN_CANTI2 +" TEXT NOT NULL,"
            + CN_COST2 +" TEXT NOT NULL);";

/*
    private static final String DATABASE_CREATE = "CREATE TABLE " + TABLE_NAME +
            " (" +COLUMN_ID+ " integer," + COLUMN_DATE + "VARCHAR," +
            COLUMN_LOCATION+" VARCHAR," +COLUMN_TIME +" VARCHAR);";

  */


    private CollectDbHelper helper;
    private SQLiteDatabase db;

    /*metodo para abrir la base de datos*/
    public CollectDataBaseManager2(Context contexto) {
        helper = new CollectDbHelper(contexto);
        db = helper.getWritableDatabase();
    }

    /*metodos para cerrar la la base de datos*/
    public void cerrar() {
        helper.close();
    }

    private ContentValues generarContentValues2(String nombre2,String costo2,String cantidad2){
        ContentValues valores2 = new ContentValues();
        valores2.put(CN_NAME2,nombre2);
        valores2.put(CN_COST2, costo2);
        valores2.put(CN_CANTI2, cantidad2);

        return valores2;
    }

    /*INSERTA TABLA2*/
    public void insertar2(String nombre2,String costo2,String cantidad2){
        /*los parametros para usar la sentecia insert son
        * parametro 1 nombre de la tabla
        * parametro 2 NULLColumHack paa columnas vacias como un default y compatibilidad de base de datos
        * parametro 3 contenedor  de valores guarda los valores entrantes
        * */
        db.insert(TABLE_NAME2,null,generarContentValues2(nombre2, costo2, cantidad2));
        //db.insert(TABLE_NAME , CN_DESCRIP, generarContentValues(nombre, costo, descripcion));
        //db.insert(TABLE_NAME, null, generarContentValues(nombre, descripcion, costo));

    }

    /*Elimina tabla 2*/
    public void eliminarPorID2(long ID) {
        db.delete(TABLE_NAME2, CN_ID + "=" + ID, null);
    }

    /*cursor de tabla 2*/
    public Cursor cargarCursor2(){
        /*cursor adapter a lisview*/
        String []columnas2= new String[]{CN_ID,CN_COST2};
       return db.query(TABLE_NAME2,columnas2,null,null,null,null,null);

    }


/*
    public String getName(String s) throws SQLException {
        //// TODO Auto-generated method stub
        String[] columns = new String[]{KEY_ID,KEY_NAME,KEY_ROLLNO};
        Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_ID + "" + s, null, null, null, null);
        if(c!=null)
        {
            c.moveToFirst();
            String name = c.getString(1);
            return name;
        }
        return null;

    }


    public String getName(String s) throws SQLException {
        // TODO Auto-generated method stub
        String[] columns = new String[]{KEY_ID,KEY_NAME,KEY_ROLLNO};
        Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_ID + "=" + s, null, null, null, null);// change this line in this method
        if(c!=null)
        {
            c.moveToFirst();
            String name = c.getString(1);
            return name;
        }
        return null;

    }
*/

    /*burcar cursor tabla 2

    public Cursor buscarproducto2(String nombre){
        String []columnas2= new String[]{CN_ID_2,CN_NAME2,CN_COST2,CN_CANTI2};
        return db.query(TABLE_NAME2,columnas2,CN_NAME2 + "=?",new String[]{nombre},null,null,null);
    }
    */
}
