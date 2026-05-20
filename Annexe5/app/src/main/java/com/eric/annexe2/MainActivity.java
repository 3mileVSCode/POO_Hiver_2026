package com.eric.annexe2;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    Ecouteur ec;
    Button boutonEnvoyer;
    EditText champEmail, champTransfert;

    Spinner spinnerNom;
    TextView champSolde;

    ArrayList<String> choix;

    DecimalFormat df = new DecimalFormat("#,##0.00$");

    HashMap<String, Compte> hm = new HashMap<>();

    int solde;
    Compte compteChoisi;

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

        boutonEnvoyer = findViewById(R.id.boutonEnvoyer);
        spinnerNom = findViewById(R.id.spinnerNom);
        champSolde = findViewById(R.id.champSolde);
        champEmail = findViewById(R.id.champEmail);
        champTransfert = findViewById(R.id.champTransfert);


        choix = new ArrayList<>();
        hm.put("Chèque", new Compte("Chèque", 1000));
        hm.put("Épargne", new Compte( "Épargne", 200));
        hm.put("Épargneplus", new Compte("Épargneplus", 490));

        choix.addAll(hm.keySet());

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, choix);
        spinnerNom.setAdapter(adapter);



        // etape 1
        ec = new Ecouteur();
        // etape 2
        boutonEnvoyer.setOnClickListener(ec);
        spinnerNom.setOnItemSelectedListener(ec);

    }

    // etape 3
    private class Ecouteur implements View.OnClickListener, AdapterView.OnItemSelectedListener
    {

        @Override
        public void onClick(View source) {

            // boutton envoyer
            String courriel = String.valueOf(champEmail.getText());
            courriel = courriel.trim(); //Enlever les espaces du début et de la fin
            if (!courriel.isEmpty()){
                String montant = champTransfert.getText().toString();
                int transfert = Integer.parseInt(montant);

                if (compteChoisi.transfert(transfert)){
                    champSolde.setText(df.format(compteChoisi.getSolde()));
                    champTransfert.setText("");
                }
                else {
                    champTransfert.setText("");

                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Attention")
                            .setMessage("Il manque de fonds")
                            .show(); //
                }
            }


        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            //String toastString = "";
            //méthode jacob
            //toastString = parent.getAdapter().getItem(position).toString();
            //méthode ludovic
            //toastString = choix.get(position);
            //méthode emile-eric
            //toastString = (String) parent.getSelectedItem();
            //méthode eric
            //TextView temp = (TextView) view;
            //toastString = temp.getText().toString();
            //méthode 5
            //toastString = (String) parent.getItemAtPosition(position);
            //Toast.makeText(MainActivity.this, toastString, Toast.LENGTH_SHORT).show();

            String nomCompte = "";
            nomCompte = choix.get(position);
            compteChoisi = hm.get(nomCompte);
            champSolde.setText(df.format(compteChoisi.getSolde()));



        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }




}