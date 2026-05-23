package com.example.annexe12_b;



import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Ecouteur ec;
    ListView listView;
    TextView question , BonRep;
    ArrayList<String> choix;
    GestionBD instance;


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
        instance = GestionBD.getInstance(getApplicationContext());
        System.out.println(instance.retounerInventions().size());

        listView = findViewById(R.id.listView);
        question = findViewById(R.id.question);
        BonRep = findViewById(R.id.BonRep);
        ec = new Ecouteur();
        choix = instance.retounerInventions();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, choix);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(ec);

    }


    public class Ecouteur implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String rep = choix.get(position);
            try{
                if(instance.aBonneReponse("Mary Anderson",rep)){
                    BonRep.setText("BONNE RÉPONCE !");
                    //listView.setBackgroundColor(Color.GREEN);
                }else{
                    BonRep.setText("MAUVAISE RÉPONCES !");
                    //listView.setBackgroundColor(Color.RED);
                }
            }catch (Exception e){
                Toast.makeText(MainActivity.this,e.getMessage(), Toast.LENGTH_LONG).show();
            }

        }
    }
}