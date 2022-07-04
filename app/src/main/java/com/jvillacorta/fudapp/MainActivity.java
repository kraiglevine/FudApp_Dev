package com.jvillacorta.fudapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.concurrent.atomic.AtomicReference;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        asignarReferencias();

        getSupportFragmentManager().beginTransaction().add(R.id.content, new InicioFragment()).commit();
        setTitle("Inicio");

        setSupportActionBar(toolbar);

        navigationView.setNavigationItemSelectedListener(item -> {
            selectItemNav(item);
            return true;
        });

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if(item.getItemId() == R.id.menu_drawer){
                drawerLayout.open();
            }
            if(item.getItemId() == R.id.menu_sumar){
                selectItemBottomNav(item);
                //navigationView.setCheckedItem(R.id.nav_sumar);
            }
            if(item.getItemId() == R.id.menu_multiplicar){
                selectItemBottomNav(item);
                navigationView.setCheckedItem(R.id.nav_multiplicar);
            }
            return false;
        });


    }

    private void asignarReferencias(){
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.tool_bar);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
    }

    private void selectItemNav(MenuItem item){
        FragmentManager fm = getSupportFragmentManager();

        FragmentTransaction ft = fm.beginTransaction();
        switch (item.getItemId()){
            case R.id.nav_home:
                ft.replace(R.id.content, new InicioFragment()).commit();
                break;
            case R.id.nav_listaPlatos:
                startActivity(new Intent(MainActivity.this, ListarPlatosActivity.class));
                break;
            case R.id.nav_multiplicar:
                ft.replace(R.id.content, new MultiplicarFragment()).commit();
                break;
                /*
            case R.id.nav_sumar:
                ft.replace(R.id.content, new SumarFragment()).commit();
                break;
            case R.id.nav_multiplicar:
                ft.replace(R.id.content, new MultiplicarFragment()).commit();
                break;
                */
        }
        setTitle(item.getTitle());
        drawerLayout.closeDrawers();
    }

    private void selectItemBottomNav(MenuItem item){
        FragmentManager fm = getSupportFragmentManager();

        FragmentTransaction ft = fm.beginTransaction();
        switch (item.getItemId()){
            case R.id.menu_sumar:
                startActivity(new Intent(MainActivity.this, SumarActivity.class));
                break;
            case R.id.menu_multiplicar:
                startActivity(new Intent(MainActivity.this, MultiplicarActivity.class));
                break;
        }
        drawerLayout.closeDrawers();
    }
}