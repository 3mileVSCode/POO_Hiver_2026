package com.example.myapplication;

import java.util.ArrayList;

public class Groupe {

    private ArrayList<Evaluation> listeEvaluation;

    public Groupe () {
        listeEvaluation = new ArrayList<>();
    }

    public void ajouterEvaluation (Evaluation e) {
        listeEvaluation.add(e);
    }

    public int nbEvaluations () {
        return listeEvaluation.size();
    }

    public int meilleurEleve(){
        int meilleurNbServices = 0;
        int matriculeMeilleur = 0;

        for (Evaluation e : listeEvaluation) {
            if (e.getNbServices() > meilleurNbServices){
                matriculeMeilleur = e.getMatricule();
                meilleurNbServices = e.getNbServices();
            }
        }

        return matriculeMeilleur;
    }


}
