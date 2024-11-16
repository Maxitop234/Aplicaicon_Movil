package com.example.evaluacion3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "remedios.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_USER = "users";
    private static final String KEY_ID = "id";
    private static final String KEY_NOMBRE = "nombre";
    private static final String KEY_CANTIDAD = "cantidad";
    private static final String KEY_FECHAVENCIMIENTO = "fecha";
    private static final String KEY_MG = "mg";
    private static final String KEY_CATEGORIA = "categoria";
    private static final String KEY_DESCRIPCION = "descripcion";

    private static final String CREATE_TABLE_REMEDIOS = "CREATE TABLE "
            + TABLE_USER + " (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_NOMBRE + " TEXT, "
            + KEY_CANTIDAD + " INTEGER, "
            + KEY_FECHAVENCIMIENTO + " TEXT, "
            + KEY_MG + " INTEGER, "
            + KEY_CATEGORIA + " TEXT, "
            + KEY_DESCRIPCION + " TEXT)";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_REMEDIOS);
    }
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        Log.d("table", CREATE_TABLE_REMEDIOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_USER + "'");
        onCreate(db);
    }
    public long addRemedio(String nombre, int cantidad, String fechaVencimiento, int mg, String categoria, String descripcion) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NOMBRE, nombre);
        values.put(KEY_CANTIDAD, cantidad);
        values.put(KEY_FECHAVENCIMIENTO, fechaVencimiento);
        values.put(KEY_MG, mg);
        values.put(KEY_CATEGORIA, categoria);
        values.put(KEY_DESCRIPCION, descripcion);


        long insert = db.insert(TABLE_USER, null, values);

        return insert;
    }
    public ArrayList<ModeloRemedios> getAllRemedios() {
        ArrayList<ModeloRemedios> remediosList = new ArrayList<>();
        // Modify the SQL query to order the results by 'nombre' alphabetically
        String selectQuery = "SELECT * FROM " + TABLE_USER + " ORDER BY " + KEY_NOMBRE + " ASC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Obteniendo todos los valores de remedio
        if (cursor.moveToFirst()) {
            do {
                ModeloRemedios remedio = new ModeloRemedios();
                remedio.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                remedio.setNombre(cursor.getString(cursor.getColumnIndex(KEY_NOMBRE)));
                remedio.setCantidad(cursor.getInt(cursor.getColumnIndex(KEY_CANTIDAD)));
                remedio.setFechaVencimiento(cursor.getString(cursor.getColumnIndex(KEY_FECHAVENCIMIENTO)));
                remedio.setMg(cursor.getInt(cursor.getColumnIndex(KEY_MG)));
                remedio.setCategoria(cursor.getString(cursor.getColumnIndex(KEY_CATEGORIA)));
                remedio.setDescripcion(cursor.getString(cursor.getColumnIndex(KEY_DESCRIPCION)));

                // Agregar todo a la lista
                remediosList.add(remedio);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return remediosList;
    }
    public int updateRemedio(int id, String nombre, int cantidad, String fechaVencimiento, int mg, String categoria, String descripcion) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Establecimiento
        ContentValues values = new ContentValues();
        values.put(KEY_NOMBRE, nombre);
        values.put(KEY_CANTIDAD, cantidad);
        values.put(KEY_FECHAVENCIMIENTO, fechaVencimiento);
        values.put(KEY_MG, mg);
        values.put(KEY_CATEGORIA, categoria);
        values.put(KEY_DESCRIPCION, descripcion);

        // Update la tabla usando la Id
        return db.update(TABLE_USER, values, KEY_ID + " = ?", new String[]{String.valueOf(id)});
    }
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        // Enabling SQL logging
        db.execSQL("PRAGMA sqlite_trace = ON;");
    }

    public void deleteUSer(int id) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
    }



}
