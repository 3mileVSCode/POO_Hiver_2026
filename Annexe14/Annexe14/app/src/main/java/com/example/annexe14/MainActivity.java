package com.example.annexe14;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Ecouteur ec;

    LinearLayout main;


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
        ec = new Ecouteur();
        main = findViewById(R.id.main);

        for(int i = 0; i < main.getChildCount(); i++){
           LinearLayout enfant = (LinearLayout) main.getChildAt(i);
           enfant.setOnDragListener(ec); // colonnes
           enfant.getChildAt(0).setOnTouchListener(ec); //les jetons
        }


    }
    public class Ecouteur implements View.OnTouchListener , View.OnDragListener{

        @Override
        public boolean onTouch(View source, MotionEvent event) {
            //creer une ombre
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(source);
            source.startDragAndDrop(null,shadowBuilder,source,0); //data --> pour transporter des infos sur la  source
            //cacher la source temporairement
            source.setVisibility(INVISIBLE);
            return true;
        }
        Drawable normal = getResources().getDrawable(R.drawable.background_contenant,null);
        Drawable selectionne = getResources().getDrawable(R.drawable.background_contenant_selectionne,null);
        View jeton = null;
        @Override
        public boolean onDrag(View v, DragEvent event) {
            switch (event.getAction()){
                case DragEvent.ACTION_DRAG_ENTERED:
                    v.setBackground(selectionne);
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    v.setBackground(normal);
                    break;
                case DragEvent.ACTION_DROP:
                    jeton = (View) event.getLocalState();
                    // il sagit du jeton qu'on a tranmis dans l'ombre qu'on drag
                    LinearLayout parent = (LinearLayout) jeton.getParent();
                    //on recupère le parent
                    parent.removeView(jeton); //on l'enleve des son conteneur de départ !
                    LinearLayout destination = (LinearLayout) v; // chercher la colonne d'arrivé
                    destination.addView(jeton);// ajouter dans la colonne de destiontion
                    jeton.setVisibility(VISIBLE); // il était encore invisible .
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    v.setBackground(normal);
                    break;

            }
            return true;
        }
    }
}