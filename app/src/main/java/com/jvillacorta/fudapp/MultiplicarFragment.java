package com.jvillacorta.fudapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class MultiplicarFragment extends Fragment {

    private EditText txtN1, txtN2, txtResultado;
    private Button btnMultiplicar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_multiplicar, container, false);

        txtN1 = view.findViewById(R.id.fr_multiplicar_n1);
            txtN2 = view.findViewById(R.id.fr_multiplicar_n2);
        txtResultado = view.findViewById(R.id.fr_multiplicar_resultado);

        btnMultiplicar = view.findViewById(R.id.fr_multiplicar_btnMultiplicar);
        btnMultiplicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtResultado.setText(Float.parseFloat(txtN1.getText().toString())*Float.parseFloat(txtN2.getText().toString())+"");
            }
        });

        return view;
    }
}