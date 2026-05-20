package com.example.watertracker;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    int qEau = 0;
    Ecouteur ec;
    ImageView boutonBidon, boutonBouteille, boutonVerre;
    ProgressBar progressBar;

    TextView eau;

    TextView champEau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.total), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        boutonBidon = findViewById(R.id.boutonBidon);
        boutonBouteille = findViewById(R.id.boutonBouteille);
        boutonVerre = findViewById(R.id.boutonVerre);
        progressBar = findViewById(R.id.progressBar);
        eau = findViewById(R.id.eau);

        ec = new Ecouteur();

        boutonBidon.setOnClickListener(ec);
        boutonBouteille.setOnClickListener(ec);
        boutonVerre.setOnClickListener(ec);
    }

    private class Ecouteur implements View.OnClickListener{
        @Override
        public void onClick(View source){
            if (source == boutonBidon){
                qEau += 1500;
            } else if (source == boutonBouteille) {
                qEau += 330;
            } else {
                qEau += 150;
            }
            if (qEau >= 2000){
                Toast toast = Toast.makeText(MainActivity.this, "Risque de potomanie", Toast.LENGTH_SHORT);
                toast.show();
                qEau = 2000;
            }
            progressBar.setProgress(qEau);
            eau.setText(String.valueOf(qEau + " ml"));
        }
    }
}