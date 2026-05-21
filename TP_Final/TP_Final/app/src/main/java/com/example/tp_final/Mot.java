package com.example.tp_final;


import java.util.ArrayList;

public class Mot {
    private ArrayList<Lettre> mot;
    private int points;

    public Mot() {

    }

    public void resetMot(){
        mot.clear();
    }

    public void ajoutLettreAuMot(Lettre l){
        mot.add(l);
    }

    public ArrayList<Lettre> getMot() {
        return mot;
    }

    public int calculerValeurMot(){
        for (Lettre l :mot) {
            points += l.getPoints();
        }
        return points;
    }
}
