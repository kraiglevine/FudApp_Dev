package com.jvillacorta.fudapp.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UsuariosTB extends SQLiteOpenHelper {

    public UsuariosTB(Context context){
        super(context, DatosBD.NOMBRE_BD, null, DatosBD.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //String query = "CREATE TABLE "+DatosBD.TB_USUARIO+" (id INTEGER PRIMARY KEY AUTOINCREMENT, correo TEXT NOT NULL, password TEXT NOT NULL);";
        //db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
