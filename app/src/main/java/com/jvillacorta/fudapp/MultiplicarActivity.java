package com.jvillacorta.fudapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MultiplicarActivity extends AppCompatActivity {

    EditText txtN1, txtN2;
    Button btnMultiplicar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplicar);

        txtN1 = findViewById(R.id.ac_multiplicar_n1);
        txtN2 = findViewById(R.id.ac_multiplicar_n2);

        btnMultiplicar = findViewById(R.id.ac_multiplicar_btnMultiplicar);
        btnMultiplicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float n1 = Float.parseFloat(txtN1.getText().toString());
                float n2 = Float.parseFloat(txtN2.getText().toString());
                float resultado = n1*n2;
                Toast.makeText(MultiplicarActivity.this, "Resultado: "+resultado, Toast.LENGTH_SHORT).show();
            }
        });
    }
}