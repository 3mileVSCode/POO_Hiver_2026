package com.example.tp_final;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
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

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    GestionBD instance;
    private Jeu jeu;
    private Mot mot;
    private int scoreTotal;
    private TextView motEnCours;
    private TextView pointsTotal;
    private TextView pointsMot;
    private SeekBar sb;
    private CountDownTimer timer;
    String wordConstruction;

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
        sb.setProgress(5000);
        sb.setEnabled(false); //pour enlever que la barre est interactive

        timer = new CountDownTimer(5000, 1) {
            @Override
            public void onFinish() {
                sb.setProgress(0);
                //inserer score dans table
                Intent i = new Intent(MainActivity.this, FinDePartie.class);
                startActivity(i);
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
        wordConstruction = "";


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
                ShadowInvisible shadowBuilder = new ShadowInvisible();
                view.startDragAndDrop(null, shadowBuilder, view, 0); // démarre le drag
                System.out.println("WOW BRAVO");
            }
            return true;
        }
        Drawable normal = getResources().getDrawable(R.drawable.background_contenant,null);
        Drawable selectionne = getResources().getDrawable(R.drawable.background_contenant_selectionne,null);
        @Override
        public boolean onDrag(View view, DragEvent e) {
            Composante c = (Composante) view;

            if (e.getAction() == DragEvent.ACTION_DRAG_ENTERED) {
                // Mettre en surbrillance la zone cible (feedback visuel)
                System.out.println("ACTION_DRAG_ENTERED");
                view.setBackground(selectionne); //changer les couleur dans les drwables xml
                TextView caseActuelle = c.findViewById(R.id.lettre);
                jeu.getMot().ajoutLettreAuMot(c.getL());


                wordConstruction += caseActuelle.getText();
                motEnCours.setText(wordConstruction);
            }
            if (e.getAction() == DragEvent.ACTION_DRAG_EXITED) {
                // Enlever la surbrillance
                System.out.println("ACTION_DRAG_EXITED");
            }
            if (e.getAction() == DragEvent.ACTION_DROP) {
                // Traiter les données droppées
                System.out.println("ACTION_DROP");
                view.setBackground(normal);
                if (!instance.motExiste(wordConstruction)){
                    ObjectAnimator shake = ObjectAnimator.ofFloat(motEnCours, "translationX",
                            0f, 30f, -30f, 30f, -30f);
                    shake.setDuration(450);
                    shake.start();
                    jeu.getMot().resetMot();
                } else {
                    int pointsDuMot = jeu.getMot().calculerValeurMot();
                    scoreTotal += pointsDuMot;
                    pointsTotal.setText(String.valueOf(scoreTotal));
                    jeu.getMot().resetMot();
                }
                wordConstruction = "";
                motEnCours.setText("");
            }
            if (e.getAction() == DragEvent.ACTION_DRAG_ENDED) {
                view.setBackground(normal);

                // Nettoyer / reset l'UI peu importe ce qui s'est passé
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