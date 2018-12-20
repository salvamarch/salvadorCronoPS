package com.example.usuario.salvadorcronops;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class resultados extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);
    }

    public void volver(View view) {
        Intent i = new Intent(this, MainActivity.class );
        startActivity(i);
    }
}
