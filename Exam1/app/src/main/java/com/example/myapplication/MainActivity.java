package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView texteMatricule, texteServices, texteStatsNbEtuEva, texteStatsMeilleurEtu;
    Button boutonServiceR, boutonEnregistrer;

    int nbServices, matricule;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        boutonServiceR = findViewById(R.id.serviceR);
        boutonEnregistrer = findViewById(R.id.enregistrer);

        texteMatricule = findViewById(R.id.matricule);
        texteServices = findViewById(R.id.services);
        texteStatsNbEtuEva = findViewById(R.id.statsNbEtuEva);
        texteStatsMeilleurEtu = findViewById(R.id.statsMeilleurEtu);

        Ecouteur ec = new Ecouteur();

        boutonEnregistrer.setOnClickListener(ec);
        boutonServiceR.setOnClickListener(ec);

    }

    private class Ecouteur implements View.OnClickListener{
        //MON CODE EST DÉGUEULASSE DÉSOLÉ !!!
        Groupe g = new Groupe();

        @Override
        public void onClick(View source){
            if (source == boutonServiceR){
                nbServices += 1;
                texteServices.setText(String.valueOf(nbServices));
            }
            if (source == boutonEnregistrer) {
                //(VRAIMENT DÉGUEULASSE !!!)
                if (!texteMatricule.getText().toString().isEmpty() && (nbServices != 0 || Integer.parseInt(texteServices.getText().toString()) != 0)){
                    g.ajouterEvaluation(new Evaluation(Integer.parseInt(texteMatricule.getText().toString()), nbServices));
                    Toast toast = Toast.makeText(MainActivity.this, "Évaluation enregistrée", Toast.LENGTH_LONG);
                    toast.show();
                    texteStatsNbEtuEva.setText(String.valueOf(g.nbEvaluations()));
                    texteStatsMeilleurEtu.setText(String.valueOf(g.meilleurEleve()));

                }
                nbServices = 0;
                texteServices.setText(String.valueOf(nbServices));
                texteMatricule.setText("");
            }
        }

    }
}