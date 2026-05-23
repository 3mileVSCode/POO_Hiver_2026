package com.example.annexe_13;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {

    ListView listView;
    Ecouteur ec;
    ArrayList<String> bieres;
    GestionBD instance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        instance = GestionBD.getInstance(getApplicationContext());
        listView = findViewById(R.id.listView);
        bieres = instance.retournerBiere();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, bieres);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(ec);
    }
    //question examen ! pourquoi on creer un singleton --> pour eviter de creer plusieur fois le meme objet!
    public class Ecouteur implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        }
    }
}