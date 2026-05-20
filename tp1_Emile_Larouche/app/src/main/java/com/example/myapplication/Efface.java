package com.example.myapplication;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

public class Efface extends Forme{
    private Path p;
    public Efface (int couleur, int largeur){
        super(couleur, largeur);
        this.p = new Path();
    }
    public void ajouterPointDebut(Point p)  { this.p.moveTo(p.x,p.y); }
    public void ajouterPointFin(Point p)    { this.p.lineTo(p.x,p.y); }

    public void dessiner (Canvas c) {
        Paint crayon = new Paint(Paint.ANTI_ALIAS_FLAG);
        crayon.setStyle(Paint.Style.STROKE);
        crayon.setColor(getCouleur());
        crayon.setStrokeWidth(getLargeur());
        c.drawPath(p,crayon);
    }
}
