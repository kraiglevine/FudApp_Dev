package com.jvillacorta.fudapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;

public class InicioActivity extends AppCompatActivity {

    LottieAnimationView animacion_nombre, animacion_vapor;
    boolean activar = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_inicio);

        animacion_nombre = findViewById(R.id.iv_animacionNombre);
        animacion_vapor = findViewById(R.id.iv_animacionVapor);

        if (activar) {
            activar = false;

            animacionNombre();
            animacionVapor();
        }

        new Handler().postDelayed(() -> {
            startActivity(new Intent(InicioActivity.this, WelcomeActivity.class));
            finish();
        }, 3000);
    }

    private void animacionNombre(){
        animacion_nombre.setAnimation(R.raw.nombre);
        animacion_nombre.setRepeatCount(LottieDrawable.INFINITE);
        animacion_nombre.playAnimation();
    }

    private void animacionVapor(){
        animacion_vapor.setAnimation(R.raw.vapor);
        animacion_vapor.setRepeatCount(LottieDrawable.INFINITE);
        animacion_vapor.playAnimation();
    }
}