package com.jvillacorta.fudapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SumarActivity extends AppCompatActivity {

    EditText txtN1, txtN2;
    Button btnSumar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sumar);

        txtN1 = findViewById(R.id.ac_sumar_n1);
        txtN2 = findViewById(R.id.ac_sumar_n2);

        btnSumar = findViewById(R.id.ac_sumar_btnSumar);
        btnSumar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float n1 = Float.parseFloat(txtN1.getText().toString());
                float n2 = Float.parseFloat(txtN2.getText().toString());
                float resultado = n1+n2;
                Toast.makeText(SumarActivity.this, "Resultado: "+resultado, Toast.LENGTH_SHORT).show();
            }
        });
    }
}