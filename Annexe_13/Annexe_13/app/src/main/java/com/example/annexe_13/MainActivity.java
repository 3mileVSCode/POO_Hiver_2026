package com.example.annexe_13;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button evaluation , bierre_eva;
    Ecouteur ec;

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
        evaluation = findViewById(R.id.evaluation);
        bierre_eva = findViewById(R.id.bierre_eva);
        ec = new Ecouteur();
        evaluation.setOnClickListener(ec);
        bierre_eva.setOnClickListener(ec);
    }

    public class Ecouteur implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if(v == evaluation){
                Intent i = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(i);

            } else if (v == bierre_eva) {
                Intent i = new Intent(MainActivity.this, MainActivity3.class);
                startActivity(i);
            }
        }
    }
}