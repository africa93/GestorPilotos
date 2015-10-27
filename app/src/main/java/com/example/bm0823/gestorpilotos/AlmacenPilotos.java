package com.example.bm0823.gestorpilotos;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import static com.example.bm0823.gestorpilotos.PilotoContract.tablaPiloto;

public class AlmacenPilotos extends SQLiteOpenHelper {

    //nombre del fichero de BD
    protected static final String DEFAULT_DB_FILENAME = "pilotos.db";

    protected static final int DATABASE_VERSION = 1;

    public AlmacenPilotos(Context context) {
        super(context, DEFAULT_DB_FILENAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String consultaSQL = "CREATE TABLE " + tablaPiloto.TABLE_NAME
                +" (" +tablaPiloto.COL_NAME_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                + tablaPiloto.COL_NAME_NOMBRE + " TEXT, "
                + tablaPiloto.COL_NAME_DORSAL + " INTEGER, "
                + tablaPiloto.COL_NAME_MOTO + " TEXT, "
                + tablaPiloto.COL_NAME_ACTIVO + "  INTEGER)";
        db.execSQL(consultaSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String consultaSQL = "DROP TABLE "+tablaPiloto.TABLE_NAME;
        db.execSQL(consultaSQL);
        onCreate(db);
    }

    public int add(Piloto piloto){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(tablaPiloto.COL_NAME_ID, piloto.get_id());
        valores.put(tablaPiloto.COL_NAME_NOMBRE, piloto.get_nombre());
        valores.put(tablaPiloto.COL_NAME_DORSAL, piloto.get_dorsal());
        valores.put(tablaPiloto.COL_NAME_MOTO, piloto.get_moto());
        if(piloto.is_activo()){
            valores.put(tablaPiloto.COL_NAME_ACTIVO, 1);
        }else{
            valores.put(tablaPiloto.COL_NAME_ACTIVO, 0);
        }
        return (int) db.insert(tablaPiloto.TABLE_NAME,null,valores);
    }

    public ArrayList<Piloto> getAll(){
        ArrayList<Piloto> resultado = new ArrayList<>();
        //TO DO Abrir bd lectura
        SQLiteDatabase db = this.getReadableDatabase();

        String consultaSQL ="SELECT * FROM " + tablaPiloto.TABLE_NAME;
        Cursor cursor = db.rawQuery(consultaSQL, null);
        //TO DO recorrer cursor asignando resultados
        if(cursor != null){
            cursor.moveToFirst();
            do{
                       resultado.add(new Piloto(cursor.getInt(cursor.getColumnIndex(tablaPiloto.COL_NAME_ID)),
                            cursor.getString(cursor.getColumnIndex(tablaPiloto.COL_NAME_NOMBRE)),
                            cursor.getInt(cursor.getColumnIndex(tablaPiloto.COL_NAME_DORSAL)),
                            cursor.getString(cursor.getColumnIndex(tablaPiloto.COL_NAME_MOTO)),
                            cursor.getInt(cursor.getColumnIndex(tablaPiloto.COL_NAME_ACTIVO)) != 0 ));

            }while(cursor.moveToNext());
        }
        try {
            cursor.close();
        }catch (Exception ex){}
        //TO DO devolver resultado
        return resultado;
    }
}
