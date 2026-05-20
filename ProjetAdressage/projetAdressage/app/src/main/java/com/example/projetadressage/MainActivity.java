package com.example.projetadressage;

import androidx.appcompat.app.AppCompatActivity;


import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;


import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import bla.HashtableAssociation;


public class MainActivity extends AppCompatActivity {

    EditText champPrenom, champNom, champAdresse, champZip;
    Spinner spinnerCapitale, spinnerEtat;

    Button bouton;
    Ecouteur ec;

    



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        champPrenom = findViewById(R.id.champPrenom);
        champNom= findViewById(R.id.champNom);
        champAdresse = findViewById(R.id.champAdresse);
        champZip = findViewById(R.id.champZip);

        spinnerCapitale = findViewById(R.id.spinnerCapitale);
        spinnerEtat = findViewById(R.id.spinnerEtat);

        bouton = findViewById(R.id.boutonInscrire);

        // remplir les spinner à l'aide de la Hashtable
        HashtableAssociation h = new HashtableAssociation();

        Set<String> ensCles = h.keySet();
        ArrayList<String> enClesA = new ArrayList<>(ensCles);
        ArrayAdapter adapter1 = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1,enClesA);
        spinnerCapitale.setAdapter(adapter1);

        Collection<String> ensval = h.values();
        ArrayList<String> envalA = new ArrayList<>(ensval);
        ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1,envalA);
        spinnerEtat.setAdapter(adapter2);

        bouton.setOnClickListener(ec);



    }
    private class Ecouteur implements View.OnClickListener , AdapterView.OnItemSelectedListener
    {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
        @Override
        public void onClick(View v) {
              try {
                  Inscrit i = new Inscrit(champNom.getText().toString(),
                          champPrenom.getText().toString(),
                          champAdresse.getText().toString(),spinnerCapitale.getSelectedItem().toString(),
                          spinnerEtat.getSelectedItem().toString(),champZip.toString() );
              }catch (AdresseException a){
                  creerAlertDialog("");
              }

        }
        public void creerAlertDialog(String message) {

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

            //on peut faire ca !!
            builder.setMessage(message)
                    .setTitle("Erreur");


            AlertDialog dialog = builder.create();
            dialog.show();

        }

    }


}


