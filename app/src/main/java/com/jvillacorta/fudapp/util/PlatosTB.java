package com.jvillacorta.fudapp.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PlatosTB extends SQLiteOpenHelper {

    public PlatosTB(Context context){
        super(context, DatosBD.NOMBRE_BD, null, DatosBD.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE "+DatosBD.TB_PLATO+
                        " (id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        "nombre TEXT NOT NULL, "+
                        "ingredientes TEXT NOT NULL, "+
                        "precio FLOAT NOT NULL, "+
                        "descripcion TEXT NOT NULL, "+
                        "oferta FLOAT NOT NULL, "+
                        "fecha DATE NOT NULL, "+
                        "imagen TEXT NOT NULL);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
