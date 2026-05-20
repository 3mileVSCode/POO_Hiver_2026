package com.example.annexe4b;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    LinearLayout main;
    TextView code;


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

        Ecouteur ec = new Ecouteur();

        main = findViewById(R.id.main);
        code = findViewById(R.id.textView);

        for (int i = 0; i < main.getChildCount(); i++){ //On exclue le dernier enfant car on ne veut pas d'écouteur sur le textView
            if (main.getChildAt(i) instanceof LinearLayout) // on exclue le textView du titre
            {
                LinearLayout temp = (LinearLayout) main.getChildAt(i);
                for (int j = 0; j < temp.getChildCount(); j++) //les enfants sont pour la plupart des button
                {
                    if (temp.getChildAt(j) instanceof Button) //on exclut les Space
                    {
                        temp.getChildAt(j).setOnClickListener(ec);
                    }
                }
            }
        }

    }


    private class Ecouteur implements View.OnClickListener{
        String s = "";
        Button bTemp;
        @Override
        public void onClick(View source){
             bTemp = (Button) source;
             // s += ((Button) source).getText().toString(); VERSION COURTE
            if (s.length() <= 3){
                main.setBackgroundColor(Color.WHITE);
                s += bTemp.getText().toString();
                code.setText(s);
            }
            if (s.length() == 4){
                if (s.equals("1234")){
                    main.setBackgroundColor(Color.CYAN);
                } else {
                    main.setBackgroundColor(Color.RED);
                }
                s = "";
            }


        }


    }

}