package com.example.tp_final;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.tp_final.R;

public class Composante extends ConstraintLayout {
    private TextView lettre,bonus,points;
    private Lettre l;


    public Composante(@NonNull Context context) {
        super(context);
        init(context);
    }

    public Composante(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Composante(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        //boutton.xml
        LayoutInflater.from(context).inflate(R.layout.lettre,this,true);
        //initialiser les composantes internes avec find my id
        lettre = findViewById(R.id.lettre);
        bonus = findViewById(R.id.bonus);
        points = findViewById(R.id.points);
    }

    public void setLettre(Lettre l) {
        this.l = l;
        lettre.setText(String.valueOf(l.getLettre()));
        points.setText(String.valueOf(l.getPoints()));

        //Affichage selon le multiplicapteur
        switch (l.getMultiplicateur()) {
            case DOUBLE_LETTRE:
                bonus.setText("2L");
                break;
            case TRIPLE_LETTRE:
                bonus.setText("3L");
                break;
            case MOT_DOUBLE:
                bonus.setText("2M");
                break;
            default:
                bonus.setText("");
        }
    }

    public void setPoints(Jeu jeu){
        points.setText(String.valueOf(jeu.getScoreTotal()));
    }

    public void setL(Lettre l) {
        this.l = l;
    }

    public Lettre getL() {
        return l;
    }
}
