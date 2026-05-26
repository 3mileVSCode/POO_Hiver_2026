package com.example.tp_final;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    GestionBD instance;
    private Jeu jeu;
    private TextView motEnCours, pointsTotal, pointsMot;
    private SeekBar sb;
    private CountDownTimer timer;

    @SuppressLint("MissingInflatedId")
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

        //instance de BD
        instance = GestionBD.getInstance(getApplicationContext());

        sb = findViewById(R.id.seekBar);
        sb.setMax(75000);
        sb.setProgress(75000);
        sb.setEnabled(false); //pour enlever que la barre est interactive

        timer = new CountDownTimer(75000, 1) {
            @Override
            public void onFinish() {
                sb.setProgress(0);
                //CHANGER L'ÉCRAN A UN ECRAN DE SCORE etc
                //Intent i = new Intent(MainActivity.this, FinDePartie.class);
                //startActivity(i);
            }

            @Override
            public void onTick(long msRestant) {
                int ms = (int)msRestant;
                sb.setProgress(ms);
            }
        }.start();

        motEnCours = findViewById(R.id.mot);
        pointsMot = findViewById(R.id.pointsMot);
        pointsTotal = findViewById(R.id.pointsPartie);

        //mon écouteur
        Ecouteur e = new Ecouteur();
        jeu = new Jeu();
        Lettre[][] lettres = jeu.getGrille().getTabLettres();



        TableLayout tl = findViewById(R.id.tableLayout);
        for (int i = 0; i < tl.getChildCount(); i++){
            TableRow row = (TableRow) tl.getChildAt(i);
            for (int j = 0; j < row.getChildCount(); j++){
                Composante composante = (Composante) row.getChildAt(j);
                composante.setOnDragListener(e);
                composante.setOnTouchListener(e);
                composante.setLettre(lettres[i][j]);
            }
        }
    }

    public class Ecouteur implements View.OnTouchListener, View.OnDragListener {
        @Override
        public boolean onTouch(View view, MotionEvent e) {
            if (e.getAction() == MotionEvent.ACTION_DOWN) {
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDragAndDrop(null, shadowBuilder, view, 0); // démarre le drag
                System.out.println("WOW BRAVO");
            }
            return true;
        }

        @Override
        public boolean onDrag(View view, DragEvent e) {
            if (e.getAction() == DragEvent.ACTION_DRAG_ENTERED) {
                // Mettre en surbrillance la zone cible (feedback visuel)
                view.setVisibility(INVISIBLE);
                System.out.println("ACTION_DRAG_ENTERED");
            }
            if (e.getAction() == DragEvent.ACTION_DRAG_EXITED) {
                // Enlever la surbrillance
                System.out.println("ACTION_DRAG_EXITED");
            }
            if (e.getAction() == DragEvent.ACTION_DROP) {
                // Traiter les données droppées
                System.out.println("ACTION_DROP");
            }
            if (e.getAction() == DragEvent.ACTION_DRAG_ENDED) {
                // Nettoyer / reset l'UI peu importe ce qui s'est passé
                view.setVisibility(VISIBLE);
            }

            return true;
        }
    }

    private static class ShadowInvisible extends View.DragShadowBuilder {
        @Override
        public void onProvideShadowMetrics(Point outShadowSize, Point outShadowTouchPoint) {
            // tout petit
            outShadowSize.set(1, 1);
            outShadowTouchPoint.set(0, 0);
        }

        @Override
        public void onDrawShadow(Canvas canvas) {
            // rien faire, on ne dessine rien
        }
    }

}