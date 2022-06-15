package com.jvillacorta.fudapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.jvillacorta.fudapp.dao.PlatoDAO;
import com.jvillacorta.fudapp.entity.Plato;
import com.jvillacorta.fudapp.util.PlatoRecycler;

import java.util.ArrayList;
import java.util.List;

public class ListarPlatosActivity extends AppCompatActivity {

    RecyclerView recyclerPlato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_platos);

        recyclerPlato = findViewById(R.id.rvPlatos);
        mostrarPlatos();
    }

    private void mostrarPlatos(){
        List<Plato> listaPlatos;
        PlatoDAO platoDAO = new PlatoDAO(this);
        platoDAO.abrirBD();
        listaPlatos = platoDAO.listarPlatos();

        PlatoRecycler platoRecycler = new PlatoRecycler(this, listaPlatos);
        recyclerPlato.setAdapter(platoRecycler);
        recyclerPlato.setLayoutManager(new LinearLayoutManager(this));
    }
}