package com.jvillacorta.fudapp.dao;

import android.content.Context;

import androidx.annotation.InspectableProperty;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.api.LabelDescriptor;
import com.google.api.MetricDescriptor;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.jvillacorta.fudapp.ListarPlatosActivity;
import com.jvillacorta.fudapp.entity.Plato;
import com.jvillacorta.fudapp.util.Firebase;
import com.jvillacorta.fudapp.util.PlatoRecycler;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class FirebaseDAO {
    private Firebase firebase;
    private DatabaseReference reference;
    private Context context;

    public FirebaseDAO(Context context){
        firebase = new Firebase(context);
        reference = firebase.getReference();
        this.context = context;
    }

    public void registrarObjeto(String nombreTabla, String id, Object objeto){
        reference.child(nombreTabla).child(id).setValue(objeto);
    }

    public void actualizarObjeto(String nombreTabla, String id, HashMap map){
        reference.child(nombreTabla).child(id).updateChildren(map);
    }

    public void eliminarObjeto(String nombreTabla, String id, List<Plato> lista){
        PlatoRecycler platoRecycler = new PlatoRecycler(context, lista);
        platoRecycler.notifyDataSetChanged();
        reference.child(nombreTabla).child(id).removeValue();
    }

    public void cargarPlatos(String nombreTabla, List<Plato> lista, RecyclerView rv){
        reference.child(nombreTabla).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                lista.clear();

                for (DataSnapshot item:snapshot.getChildren()) {
                    Plato plato = item.getValue(Plato.class);
                    lista.add(plato);
                }

                PlatoRecycler platoRecycler = new PlatoRecycler(context, lista);
                rv.setAdapter(platoRecycler);
                rv.setLayoutManager(new LinearLayoutManager(context));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
