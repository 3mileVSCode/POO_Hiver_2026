package com.example.annexe_13;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {
    Ecouteur ec;
    EditText nomBiere , micro;
    Button eng;
    RatingBar ratingBar;
    GestionBD instance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        instance = GestionBD.getInstance(getApplicationContext());

        ec = new Ecouteur();
        nomBiere = findViewById(R.id.nomBiere);
        micro = findViewById(R.id.micro);
        eng = findViewById(R.id.eng);
        ratingBar = findViewById(R.id.ratingBar);

        eng.setOnClickListener(ec);

    }
    public class Ecouteur implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if(v == eng){
                instance.ajouterBiere(new Evaluation(nomBiere.getText().toString(),micro.getText().toString(),ratingBar.getRating()));
            }
            finish();
        }
    }
}