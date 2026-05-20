package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    LinearLayout couleurs, images;
    ConstraintLayout parent;
    Surface s;
    int couleur = Color.MAGENTA;
    int bgCouleur = Color.WHITE;
    String tagObjet = "crayon";
    ArrayList <Forme> formes = new ArrayList<>();
    Forme f;
    Point depart, arrive;
    EcouteurClick ec;
    EcouteurTouch ect;


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

        couleurs = findViewById(R.id.couleurs);
        images = findViewById(R.id.images);
        parent = findViewById(R.id.parent);

        ec = new EcouteurClick();
        ect = new EcouteurTouch();
        s = new Surface(this);
        s.setLayoutParams(new ConstraintLayout.LayoutParams(-1,-1));
        parent.addView(s);
        s.setOnTouchListener(ect);


        //MET ÉCOUTEUR SUR MES COULEURS
        for (int i = 0; i < couleurs.getChildCount(); i++){
            if (couleurs.getChildAt(i) instanceof Button){
                couleurs.getChildAt(i).setOnClickListener(ec);
            }
        }

        //MET ÉCOUTEUR SUR MES BOUTONS D'OBJET
        for (int i = 0; i < images.getChildCount(); i++){
            if (images.getChildAt(i) instanceof ImageButton){
                images.getChildAt(i).setOnClickListener(ec);
            }
        }
    }

    private class EcouteurClick implements View.OnClickListener {
        @Override
        public void onClick(View v){
            if (v.getParent() == couleurs){
                couleur = Color.parseColor(v.getTag().toString());
            } else if (v.getParent() == images){
                tagObjet = v.getTag().toString();
                if (tagObjet.equals("remplir")){
                    bgCouleur = couleur;
                }
            }
        }
    }

    private class EcouteurTouch implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent e) {
            //CRÉE LE BON TYPE D'INSTANCE ET DÉFINIT LE DÉBUT DU TRACÉ
            if (e.getAction() == MotionEvent.ACTION_DOWN) {
                if (tagObjet.equals("crayon"))
                    f = new Trace(couleur,15);
                else if (tagObjet.equals("efface"))
                    f = new Efface(bgCouleur, 30);
                else if (tagObjet.equals("rectangle")) {
                    f = new Rectangle(couleur, 15);
                }

                //vérification null avant d'utiliser f
                if (f == null) return true;

                depart = new Point((int) e.getX(), (int) e.getY());

                if (f instanceof Trace)
                    ((Trace)f).ajouterPointDebut(depart);
                else if (f instanceof Efface)
                    ((Efface)f).ajouterPointDebut(depart);
                else if (f instanceof Rectangle) {
                    f.setDebut(depart);
                }
            } else if (e.getAction() == MotionEvent.ACTION_MOVE) {

                //vérification null pour éviter crash si ACTION_MOVE arrive sans ACTION_DOWN
                if (f == null) return true;

                arrive = new Point((int) e.getX(), (int) e.getY());

                if (f instanceof Trace)
                    ((Trace)f).ajouterPointFin(arrive);
                else if (f instanceof Efface)
                    ((Efface)f).ajouterPointFin(arrive);
                else if (f instanceof  Rectangle) {
                    f.setFin(arrive);
                }
            } else if (e.getAction() == MotionEvent.ACTION_UP) {
                if (f != null){
                    formes.add(f);
                }
            }

            if (s != null) s.invalidate();
            return true;
        }
    }



    private class Surface extends View {
        public Surface (Context context) {
            super(context);
            s = this;
        }


        @Override
        protected void onDraw(@NonNull Canvas canvas) {
            super.onDraw(canvas);

            canvas.drawColor(bgCouleur);

            if (formes != null) {
                for (Forme forme : formes) {
                    //if forme efface change sa coueleur pour bgcolor
                    forme.dessiner(canvas);
                }
            }

            //dessine la forme en cours
            if (f != null) {
                f.dessiner(canvas);
            }
        }
    }



}