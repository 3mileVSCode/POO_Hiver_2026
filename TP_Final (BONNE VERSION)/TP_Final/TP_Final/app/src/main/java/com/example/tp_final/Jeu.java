package com.example.tp_final;

import java.util.ArrayList;

public class Jeu {

    private GrilleDeJeu grille;
    private Mot mot;
    private int scoreTotal;
    private ArrayList<String> motsTrouves;

    public Jeu () {
        this.grille = new GrilleDeJeu();
        this.mot = new Mot();
        this.scoreTotal = 0;
        this.motsTrouves = new ArrayList<>();
    }

    public void ajouterLettre(Lettre l) {
        //AJOUTER LES VERIFICATIONS
        mot.ajoutLettreAuMot(l);
    }

    public boolean motValide(GestionBD bd) {
        String s = "";
        for (Lettre l : mot.getMot()) {
            s += l.getLettre();
        }

        return bd.motExiste(s);
    }
}
