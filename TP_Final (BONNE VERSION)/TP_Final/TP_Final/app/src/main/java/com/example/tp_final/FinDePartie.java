package com.example.tp_final;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.tabs.TabLayout;

public class FinDePartie extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GestionBD bd = GestionBD.getInstance(getApplicationContext());
        //TableLayout table = findViewById(R.id.tableLayout);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fin);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.linearLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button boutonRejouer = findViewById(R.id.rejouer);
        FinDePartie.Ecouteur e = new Ecouteur();
        boutonRejouer.setOnClickListener(e);

    }




    public class Ecouteur implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent i = new Intent(FinDePartie.this, MainActivity.class);
            startActivity(i);
        }
    }
}
