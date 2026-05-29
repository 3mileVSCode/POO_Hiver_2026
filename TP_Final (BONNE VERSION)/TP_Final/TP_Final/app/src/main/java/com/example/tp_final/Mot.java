package com.example.tp_final;


import java.util.ArrayList;

public class Mot {
    private ArrayList<Lettre> mot = new ArrayList<>();
    private int pointsMot;
    public void ajoutLettreAuMot(Lettre l) { mot.add(l); }
    public void resetMot() { mot.clear(); }
    public boolean estVide() { return mot.isEmpty(); }
    public ArrayList<Lettre> getMot() { return mot; }

    public boolean contientLaLettre(Lettre l) {
        return mot.contains(l);
    }
    public int calculerValeurMot(){
        int points = 0;
        boolean motDouble = false;
        for (Lettre l :mot) {
            points += l.getPoints();
            if (l.getMultiplicateur() == Lettre.Multiplicateur.MOT_DOUBLE) {
                motDouble = true;
            }
        }
        return motDouble ? (points * 2) : points;
    }
}
