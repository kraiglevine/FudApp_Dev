package com.jvillacorta.fudapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SumarFragment extends Fragment {

    private EditText txtN1, txtN2, txtResultado;
    private Button btnSumar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sumar, container, false);

        txtN1 = view.findViewById(R.id.fr_sumar_n1);
        txtN2 = view.findViewById(R.id.fr_sumar_n2);
        txtResultado = view.findViewById(R.id.fr_sumar_resultado);

        btnSumar = view.findViewById(R.id.fr_sumar_btnSumar);
        btnSumar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtResultado.setText(Float.parseFloat(txtN1.getText().toString())+Float.parseFloat(txtN2.getText().toString())+"");
            }
        });

        return view;
    }
}