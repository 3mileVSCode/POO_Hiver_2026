package com.example.projetadressage;


import bla.HashtableAssociation;

public class Inscrit {
    private String nom;
    private String prenom;
    private String adresse;
    private String capitale;
    private String etat;
    private String codeZip;

    public Inscrit(String nom, String prenom, String adresse, String capitale, String etat, String codeZip) throws AdresseException {

        if(nom.trim().isEmpty() ){
            throw new AdresseException(nom);
        }else {
            this.nom = nom;
        }if (prenom.trim().isEmpty()){
            throw new AdresseException(prenom);
        }else {
            this.prenom = prenom;
        }if(adresse.trim().isEmpty()){
            throw new AdresseException(adresse);
        }else {
            this.adresse = adresse;
        }if(codeZip.trim().isEmpty()){
            throw new AdresseException(adresse);
        }else{
            this.codeZip = codeZip;
        }
        // vérifier si la capitale fait partie de l'état à l'aide d'une Hashtable secrète ( classe HashtableAssociation )
        HashtableAssociation h = new HashtableAssociation();
        String etatTrouve = h.get(capitale);

        if(!etatTrouve.equals(etat)){ //pas un bon match
            throw new AdresseException(capitale, etat);
        }
        this.etat = etat;
        this.capitale = capitale;

    }
}
