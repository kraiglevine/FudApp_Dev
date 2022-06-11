package com.jvillacorta.fudapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registrar();
    }

    private void registrar(){
        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText txtCorreo; TextInputEditText txtPassword;
                txtCorreo = findViewById(R.id.txtCorreoR);
                txtPassword = findViewById(R.id.txtPasswordR);

                String correo, password;
                correo = txtCorreo.getText().toString();
                password = txtPassword.getText().toString();

                if(!correo.isEmpty() && !password.isEmpty()){
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(correo, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>(){
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(RegisterActivity.this, "Authentication successs", Toast.LENGTH_SHORT).show();
                                        //FirebaseUser user = mAuth.getCurrentUser();

                                        txtCorreo.setText(null);
                                        txtPassword.setText(null);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(RegisterActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }

}