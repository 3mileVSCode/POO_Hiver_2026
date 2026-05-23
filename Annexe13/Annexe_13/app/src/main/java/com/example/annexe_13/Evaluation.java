package com.example.annexe_13;

public class Evaluation {
    private String nom;
    private String microbrasserie;
    private double review;

    public Evaluation(String nom, String microbrasserie, double review) {
        this.nom = nom;
        this.microbrasserie = microbrasserie;
        this.review = review;
    }

    public String getNom() {
        return nom;
    }

    public String getMicrobrasserie() {
        return microbrasserie;
    }

    public double getReview() {
        return review;
    }
}
