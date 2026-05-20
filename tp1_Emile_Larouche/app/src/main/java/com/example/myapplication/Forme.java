package com.example.myapplication;

import android.graphics.Canvas;
import android.graphics.Point;

public class Forme {
    private int couleur;
    private int largeur;
    private Point debut;
    private Point fin;


    public Forme (int couleur, int largeur) {
        this.couleur = couleur;
        this.largeur = largeur;
    }

    public int getCouleur() {
        return couleur;
    }

    public void setCouleur(int couleur) {
        this.couleur = couleur;
    }

    public int getLargeur() {
        return largeur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public void dessiner (Canvas c) {

    }

    public Point getDebut() {
        return debut;
    }

    public Point getFin() {
        return fin;
    }

    public void setDebut(Point debut) {
        this.debut = debut;
    }

    public void setFin(Point fin) {
        this.fin = fin;
    }
}

