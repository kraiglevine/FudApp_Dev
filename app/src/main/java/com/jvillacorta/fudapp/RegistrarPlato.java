package com.jvillacorta.fudapp;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.ClipboardManager;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.jvillacorta.fudapp.dao.FirebaseDAO;
import com.jvillacorta.fudapp.entity.Plato;
import com.jvillacorta.fudapp.util.Herramientas;

import java.util.Calendar;
import java.util.HashMap;
import java.util.UUID;

public class RegistrarPlato extends AppCompatActivity {

    EditText txtNombre, txtIngredientes, txtPrecio, txtDescripcion, txtOferta, txtFecha, txtImagenURL;
    DatePickerDialog.OnDateSetListener escogerFecha;
    Button btnRegistrarPlato;
    ImageButton btnMostrarImgPlato;
    ImageView ivPlato;

    TextInputLayout txtFecha_il, txtImagenURL_il;

    //ActivityResultLauncher<Intent> activityResultLauncher;

    String mensaje, id;
    boolean registra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_plato);

        asignarReferencias();
        obtenerValores();

        txtFecha.setKeyListener(null);
        txtFecha.setFocusable(false);
        mostrarDatePicker();


        txtImagenURL_il.setEndIconOnClickListener(v -> {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(RegistrarPlato.CLIPBOARD_SERVICE);

            String portaPapeles = clipboard.getPrimaryClip().getItemAt(0).getText().toString();
            if (portaPapeles.startsWith("http") || portaPapeles.startsWith("https")) {
                txtImagenURL.setText(portaPapeles);
            }
        });

        btnMostrarImgPlato.setOnClickListener(v -> {
            /*
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            activityResultLauncher.launch(intent);
            */
            Herramientas.cargarImagenURLenIV(txtImagenURL.getText().toString(), ivPlato);
        });

        /*
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == RESULT_OK && null != result.getData()){
                    Uri uri = result.getData().getData();
                    ivPlato.setImageURI(uri);
                }
            }
        });
        */

        btnRegistrarPlato.setOnClickListener(v -> registrarPlato());
    }

    private void asignarReferencias(){
        txtNombre = findViewById(R.id.txtNombrePlato);
        txtIngredientes = findViewById(R.id.txtIngredientesPlato);
        txtPrecio = findViewById(R.id.txtPrecioPlato);
        txtDescripcion = findViewById(R.id.txtDescripcionPlato);
        txtOferta = findViewById(R.id.txtOfertaPlato);
        txtFecha = findViewById(R.id.txtFechaPlato);

        txtImagenURL = findViewById(R.id.txtImagenURL);

        txtFecha_il = findViewById(R.id.txtFechaPlato_il);

        txtImagenURL_il = findViewById(R.id.txtImagenURL_il);

        btnRegistrarPlato = findViewById(R.id.btnRegistrarPlato);

        btnMostrarImgPlato = findViewById(R.id.btnExaminarImgPlato);
        ivPlato = findViewById(R.id.ivPlato);
    }

    private void mostrarDatePicker(){
        Calendar calendario = Calendar.getInstance();

        final int anio = calendario.get(Calendar.YEAR);
        final int mes = calendario.get(Calendar.MONTH);
        final int dia = calendario.get(Calendar.DAY_OF_MONTH);

        txtFecha_il.setEndIconOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    RegistrarPlato.this,
                    android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                    escogerFecha, anio, mes, dia);
            datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            datePickerDialog.show();
        });

        escogerFecha = (view, year, month, dayOfMonth) -> {
            month = month + 1;
            String formatoFecha = String.format("%02d" , dayOfMonth)+"/"+String.format("%02d" , month)+"/"+String.format("%02d" , year);
            txtFecha.setText(formatoFecha);
        };
    }

    private void registrarPlato(){
        String nombre, ingredientes, descripcion, fecha, imagenURL;
        float precio, oferta;

        nombre = txtNombre.getText().toString();
        ingredientes = txtIngredientes.getText().toString();
        descripcion = txtDescripcion.getText().toString();
        fecha = txtFecha.getText().toString();

        precio = Float.parseFloat(txtPrecio.getText().toString());
        oferta = Float.parseFloat(txtOferta.getText().toString());

        imagenURL = txtImagenURL.getText().toString();

        Plato plato = new Plato(
                UUID.randomUUID().toString(), nombre, ingredientes, precio, descripcion, oferta, fecha, imagenURL
        );

        FirebaseDAO dao = new FirebaseDAO(this);
        if (registra) {
            dao.registrarObjeto("Platos", plato.getId(), plato);
            mensaje = "Se registró correctamente";
        } else {
            HashMap map = new HashMap();
            map.put("nombre", nombre);
            map.put("ingredientes", ingredientes);
            map.put("descripcion", descripcion);
            map.put("precio", precio);
            map.put("oferta", oferta);
            map.put("fecha", fecha);
            map.put("imagenURL", imagenURL);
            dao.actualizarObjeto("Platos", id, map);
            mensaje = "Se actualizó correctamente";
        }

        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(RegistrarPlato.this, ListarPlatosActivity.class));
    }

    private void obtenerValores() {
        if (getIntent().hasExtra("pl_id")){
            registra = false;
            id = getIntent().getStringExtra("pl_id")+"";
            txtNombre.setText(getIntent().getStringExtra("pl_nombre"));
            txtIngredientes.setText(getIntent().getStringExtra("pl_ingredientes"));
            txtDescripcion.setText(getIntent().getStringExtra("pl_descripcion"));
            txtPrecio.setText(getIntent().getStringExtra("pl_precio"));
            txtOferta.setText(getIntent().getStringExtra("pl_oferta"));
            txtFecha.setText(getIntent().getStringExtra("pl_fecha"));
            txtImagenURL.setText(getIntent().getStringExtra("pl_imagenURL"));
            Herramientas.cargarImagenURLenIV(txtImagenURL.getText().toString(), ivPlato);
        } else {
            registra = true;
        }
    }

}