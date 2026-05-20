package com.eric.annexe2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Ecouteur ec;
    Button boutonValider, boutonEnvoyer;
    EditText champNomCompte, champEmail, champTransfert;
    TextView champSolde;

    ArrayList<String> choix;

    int solde;

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

        boutonValider = findViewById(R.id.boutonValider);
        boutonEnvoyer = findViewById(R.id.boutonEnvoyer);
        champNomCompte = findViewById(R.id.champNomCompte);
        champSolde = findViewById(R.id.champSolde);
        champEmail = findViewById(R.id.champEmail);
        champTransfert = findViewById(R.id.champTransfert);


        choix = new ArrayList<>();
        choix.add("CHEQUE");
        choix.add("EPARGNE");
        choix.add("EPARGNEPLUS");


        // etape 1
        ec = new Ecouteur();
        // etape 2
        boutonValider.setOnClickListener(ec);
        boutonEnvoyer.setOnClickListener(ec);

    }

    // etape 3
    private class Ecouteur implements View.OnClickListener
    {

        @Override
        public void onClick(View source) {
            if (source == boutonValider){
                // quand on clic on est ici
                String nomCompte = champNomCompte.getText().toString();
                // String nomCompte = String.valueOf ( champNomCompte.getText());
                nomCompte = nomCompte.trim().toUpperCase();
                if ( choix.contains(nomCompte)){
                    solde = 500;
                    champSolde.setText(String.valueOf (solde));
                }
                else {
                    champSolde.setText("pas un bon nom");
                    champNomCompte.setText("");
                }
            }
            // boutton envoyer
            else {
                String courriel = String.valueOf(champEmail.getText());
                courriel = courriel.trim(); //Enlever les espaces du début et de la fin
                if (!courriel.isEmpty()){
                    String montant = champTransfert.getText().toString();
                    int transfert = Integer.parseInt(montant);

                    if (transfert <= solde){
                        solde -= transfert;
                        champSolde.setText(String.valueOf(solde));
                    }
                    else {
                        champEmail.setText("pas assez de fonds");
                        champTransfert.setText("0");
                    }
                }
            }

        }
    }




}