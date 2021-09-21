package com.developers.quickcollect.BaseDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Developer on 14/12/2015.
 * clase encargada de uso del esquema de la base da datos
 * y contiene los metodos de las operacione CRUD
 * insertar
 * eliminar
 * actualizar
 */
public class CollectDataBaseManager {

    /*nombre de la tabla1*/
    public static final String TABLE_NAME = "productos";


    /*nombre de los campos de la tabla1*/
    public static final String CN_ID  = "_id";
    public static final String CN_NAME = "nombre";
    public static final String CN_COST = "costo";
    public static final String CN_DESCRIP = "descripcion";



    /*String del script en sql de para generrar la tabla1*/
    public static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+" ("
            +CN_ID +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
            +CN_NAME +" TEXT NOT NULL,"
            +CN_DESCRIP +" TEXT,"
            +CN_COST +" TEXT NOT NULL);";


    private CollectDbHelper helper;
    private SQLiteDatabase db;

    /*metodo para abrir la base de datos*/
    public CollectDataBaseManager(Context contexto) {
        helper = new CollectDbHelper(contexto);
         db = helper.getWritableDatabase();
    }

    /*metodos para cerrar la la base de datos*/
    public void cerrar() {
        helper.close();
    }


    /*contenedor de valores
    * guarda los valores que se quierene guardar en la base de
    * datos previamente
    * */
    private ContentValues generarContentValues(String nombre,String costo,String descripcion){
        ContentValues valores = new ContentValues();
        valores.put(CN_NAME,nombre);
        valores.put(CN_COST, costo);
        valores.put(CN_DESCRIP, descripcion);

        return valores;
    }



    /*  INSERTA TABLA1 metodo de insertar primera forma
    * nombre y costo son los datos que se insertarn en la base de datos
    * */
    public void insertar(String nombre,String costo,String descripcion){
        /*los parametros para usar la sentecia insert son
        * parametro 1 nombre de la tabla
        * parametro 2 NULLColumHack paa columnas vacias como un default y compatibilidad de base de datos
        * parametro 3 contenedor  de valores guarda los valores entrantes
        * */
        db.insert(TABLE_NAME, CN_DESCRIP, generarContentValues(nombre, costo, descripcion));
        //db.insert(TABLE_NAME, null, generarContentValues(nombre, descripcion, costo));

    }



    /*metodo de insertar segunda forma  forma
    * nombre y costo son los datos que se insertarn en la base de datos
    *
    public void insertar2(String nombre,String descripcion,String costo){
        //db.execSQL("INSERT INTO HIPOTECA(_id, hip_nombre) VALUES(2,'BBVA')");
        db.execSQL("INSERT INTO " + TABLE_NAME + "(nombre,descripcion,costo) VALUES('" + nombre + "','" + descripcion + "'," + costo + ")");
    }*/
    /*medot elimina*/
    public void eliminar(String nombre){
        /*parametros  nombre de la tabla, clausula where,un vector de string por que pueden ser varios elementos */
        db.delete(TABLE_NAME, CN_NAME + "=?", new String[]{nombre});
    }

    /*elimina tabla 1*/
    public void eliminarPorID(long ID) {
        db.delete(TABLE_NAME, CN_ID + "=" + ID, null);
    }



    /*metodo elimina multiple*/
    public void eliminarMultiple(String nombre1,String nombre2){
        db.delete(TABLE_NAME, CN_NAME + "IN (?,?)", new String[]{nombre1, nombre2});
    }
    /*modificar*/
    public void modificar(String nombre,String nuevoCosto,String nuevodescripcion){
        /*para mettros son la tabla, un contenedor de valores, la clausula where, argumentos where*/
        db.update(TABLE_NAME, generarContentValues(nombre, nuevoCosto, nuevodescripcion), CN_NAME + "=?", new String[]{nombre});
    }

    public void actualizarDatos(long ID,String nombre,String nuevoCosto,String nuevodescripcion) {

       db.update(TABLE_NAME, generarContentValues(nombre,nuevoCosto,nuevodescripcion), CN_ID  + " = " + ID, null);

    }

    /*cursor de tabla 1*/
    public Cursor cargarCursorContactos(){
        /*cursor adapter a lisview*/
        String []columnas= new String[]{CN_ID,CN_NAME,CN_COST,CN_DESCRIP};
        return db.query(TABLE_NAME,columnas,null,null,null,null,null);
    }



    /*public Cursor leerDatos() {
        String[] todasLasColumnas = new String[] {DBhelper.MIEMBRO_ID, DBhelper.MIEMBRO_NOMBRE};
        Cursor c = database.query(DBhelper.TABLE_MEMBER, todasLasColumnas, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }*/

    /*burcar cursor tabla 1*/
    public Cursor buscarproducto(String nombre){
        String []columnas= new String[]{CN_ID,CN_NAME,CN_COST,CN_DESCRIP};
        return db.query(TABLE_NAME,columnas,CN_NAME + "=?",new String[]{nombre},null,null,null);
    }

}
