package com.jvillacorta.fudapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jvillacorta.fudapp.dao.PlatoDAO;
import com.jvillacorta.fudapp.entity.Plato;
import com.jvillacorta.fudapp.util.Herramientas;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

public class frmRegistrarPlato extends AppCompatActivity {

    EditText txtNombre, txtIngredientes, txtPrecio, txtDescripcion, txtOferta, txtFecha;
    TextView marcoFecha;
    DatePickerDialog.OnDateSetListener escogerFecha;
    Button btnRegistrarPlato;
    ImageButton btnExaminarImgPlato;
    ImageView ivPlato;

    ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm_registrar_plato);

        asignarReferencias();
    }

    private void asignarReferencias(){
        txtNombre = findViewById(R.id.txtNombrePlato);
        txtIngredientes = findViewById(R.id.txtIngredientesPlato);
        txtPrecio = findViewById(R.id.txtPrecioPlato);
        txtDescripcion = findViewById(R.id.txtDescripcionPlato);
        txtOferta = findViewById(R.id.txtOfertaPlato);
        txtFecha = findViewById(R.id.txtFechaPlato);
        marcoFecha = findViewById(R.id.marcoFecha);
        mostrarDatePicker();

        btnRegistrarPlato = findViewById(R.id.btnRegistrarPlato);
        btnRegistrarPlato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarPlato();
            }
        });

        btnExaminarImgPlato = findViewById(R.id.btnExaminarImgPlato);
        ivPlato = findViewById(R.id.ivPlato);
        btnExaminarImgPlato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                activityResultLauncher.launch(intent);
            }
        });

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == RESULT_OK && null != result.getData()){
                    Uri uri = result.getData().getData();
                    ivPlato.setImageURI(uri);
                }
            }
        });
    }

    private void mostrarDatePicker(){
        Calendar calendario = Calendar.getInstance();

        final int anio = calendario.get(Calendar.YEAR);
        final int mes = calendario.get(Calendar.MONTH);
        final int dia = calendario.get(Calendar.DAY_OF_MONTH);

        marcoFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        frmRegistrarPlato.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        escogerFecha, anio, mes, dia);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        escogerFecha = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String formatoFecha = String.format("%02d" , dayOfMonth)+"/"+String.format("%02d" , month)+"/"+String.format("%02d" , year);
                txtFecha.setText(formatoFecha);
            }
        };
    }

    private void registrarPlato(){
        String nombre, ingredientes, descripcion, fecha;
        float precio, oferta;
        Bitmap imagen;

        nombre = txtNombre.getText().toString();
        ingredientes = txtIngredientes.getText().toString();
        descripcion = txtDescripcion.getText().toString();
        fecha = txtFecha.getText().toString();

        precio = Float.parseFloat(txtPrecio.getText().toString());
        oferta = Float.parseFloat(txtOferta.getText().toString());

        imagen = Herramientas.drawableToBitmap(ivPlato.getDrawable());

        Plato plato = new Plato(
                nombre, ingredientes, precio, descripcion, oferta, fecha, imagen
        );
        PlatoDAO platoDAO = new PlatoDAO(this);
        platoDAO.abrirBD();
        String mensaje = platoDAO.registrarPlato(plato);
        mostrarMensaje(mensaje);
    }



    private void mostrarMensaje(String mensaje){
        AlertDialog.Builder ventana = new AlertDialog.Builder(frmRegistrarPlato.this);
        ventana.setTitle("Mensaje de Acción");
        ventana.setMessage(mensaje);
        ventana.setPositiveButton("Aceptar", null);
        ventana.create().show();
    }
}