package com.example.myapplication;

public class Evaluation {

    private int matricule, nbServices;

    public Evaluation (int matricule, int nbServices){
        this.matricule = matricule;
        this.nbServices = nbServices;
    }

    public int getNbServices(){
        return nbServices;
    }


    public int getMatricule() {
        return matricule;
    }

    public void setMatricule(int matricule) {
        this.matricule = matricule;
    }

    public void setNbServices(int nbServices) {
        this.nbServices = nbServices;
    }
}
