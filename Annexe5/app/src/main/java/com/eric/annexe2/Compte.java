package com.eric.annexe2;

public class Compte {

    private String nom;
    private int solde;

    public Compte(String nom, int solde) {
        this.nom = nom;
        this.solde = solde;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    public boolean transfert(int montant){
        if (montant <= solde){
            solde -= montant;
            return true;
        }
        else return false;
    }
}
