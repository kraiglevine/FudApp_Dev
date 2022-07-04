package com.jvillacorta.fudapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jvillacorta.fudapp.dao.FirebaseDAO;
import com.jvillacorta.fudapp.dao.PlatoDAO;
import com.jvillacorta.fudapp.entity.Plato;
import com.jvillacorta.fudapp.util.PlatoRecycler;

import java.util.ArrayList;
import java.util.List;

public class ListarPlatosActivity extends AppCompatActivity {

    RecyclerView recyclerPlato;
    FloatingActionButton btnNuevoPlato;

    private List<Plato> listaPlatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_platos);

        recyclerPlato = findViewById(R.id.rvPlatos);
        //mostrarPlatos();
        mostrarPlatosF();

        btnNuevoPlato = findViewById(R.id.fbtnNuevoPlato);
        btnNuevoPlato.setOnClickListener(v -> startActivity(new Intent(ListarPlatosActivity.this, RegistrarPlato.class)));

        eliminarPlato();
    }

    private void mostrarPlatosF() {
        listaPlatos = new ArrayList<>();

        FirebaseDAO dao = new FirebaseDAO(this);
        dao.cargarPlatos("Platos", listaPlatos, recyclerPlato);
    }

    private void mostrarPlatos(){
        //List<Plato> listaPlatos;
        PlatoDAO platoDAO = new PlatoDAO(this);
        platoDAO.abrirBD();
        listaPlatos = platoDAO.listarPlatos();

        PlatoRecycler platoRecycler = new PlatoRecycler(this, listaPlatos);
        recyclerPlato.setAdapter(platoRecycler);
        recyclerPlato.setLayoutManager(new LinearLayoutManager(this));
    }

    private void eliminarPlato() {
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int pos = viewHolder.getAdapterPosition();
                String plato_id = listaPlatos.get(pos).getId();
                mostrarMensaje("Estás a punto de eliminar este plato ...", pos, plato_id);
            }
        }).attachToRecyclerView(recyclerPlato);
    }

    private void mostrarMensaje(String mensaje, int pos, String id){
        AlertDialog.Builder ventana = new AlertDialog.Builder(ListarPlatosActivity.this);
        ventana.setTitle("Mensaje de Acción");
        ventana.setMessage(mensaje);
        ventana.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listaPlatos.remove(pos);
                FirebaseDAO dao = new FirebaseDAO(ListarPlatosActivity.this);
                dao.eliminarObjeto("Platos", id, listaPlatos);
            }
        });
        ventana.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(ListarPlatosActivity.this, ListarPlatosActivity.class));
            }
        });
        ventana.create().show();
    }
}