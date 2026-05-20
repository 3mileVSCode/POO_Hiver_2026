package com.example.myapplication;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;


public class Rectangle extends Forme{

    public Rectangle (int couleur, int largeur){
        super(couleur, largeur);
    }


    @Override
    public void dessiner(Canvas c) {
        if (getDebut() == null || getFin() == null) return;

        Paint crayon = new Paint(Paint.ANTI_ALIAS_FLAG);
        crayon.setStyle(Paint.Style.STROKE);
        crayon.setColor(getCouleur());
        crayon.setStrokeWidth(getLargeur());
        c.drawRect(getDebut().x,getDebut().y, getFin().x,getFin().y, crayon);
    }
}
