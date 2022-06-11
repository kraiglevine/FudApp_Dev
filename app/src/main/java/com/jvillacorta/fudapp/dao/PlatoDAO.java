package com.jvillacorta.fudapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import com.jvillacorta.fudapp.entity.Plato;
import com.jvillacorta.fudapp.util.DatosBD;
import com.jvillacorta.fudapp.util.Herramientas;
import com.jvillacorta.fudapp.util.PlatosTB;

import java.util.ArrayList;
import java.util.List;

public class PlatoDAO {
    private PlatosTB platoTB;
    private SQLiteDatabase db;
    private Context context;

    public PlatoDAO(Context context){
        platoTB = new PlatosTB(context);
        this.context = context;
    }

    public void abrirBD(){
        db = platoTB.getWritableDatabase();
    }

    public String registrarPlato(Plato plato){
        String mensaje = "";

        try {
            ContentValues valores = new ContentValues();
            valores.put("nombre", plato.getNombre());
            valores.put("ingredientes", plato.getIngredientes());
            valores.put("precio", plato.getPrecio());
            valores.put("descripcion", plato.getDescripcion());
            valores.put("oferta", plato.getOferta());
            valores.put("fecha", String.valueOf(plato.getFecha()));
            valores.put("imagen", String.valueOf(plato.getImagen()));

            long resultado = db.insert(DatosBD.TB_PLATO, null, valores);
            if(resultado != -1){
                mensaje = "Se registró el plato correctamente";
            }

        } catch (Exception e){
            mensaje = "No se registró el plato";
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return mensaje;
    }

    public List<Plato> listarPlatos(){
        List<Plato> listaPlatos = new ArrayList<>();
        try{
            Cursor c = db.rawQuery("SELECT * FROM "+DatosBD.TB_PLATO, null);
            while (c.moveToNext()){
                listarPlatos().add(new Plato(
                        c.getInt(0),
                        c.getString(1),
                        c.getString(2),
                        c.getFloat(3),
                        c.getString(4),
                        c.getFloat(5),
                        c.getString(6),
                        Herramientas.convertirBlobABitmap(c, 7)
                ));
            }
        }catch (Exception e){
            Log.d("Error: " , e.getMessage());
        }

        return listaPlatos;
    }

}
