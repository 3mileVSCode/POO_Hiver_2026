package com.example.annexe_a;

public class NegatitifException extends Exception{
    //inclut donc un message, getMessage
    private double valeurErronee;
    private String nomVariableQuiACauseException;

    public NegatitifException(double valeurErronee, String nomVariableQuiACauseException) {
        //appel du constructeur d'execption qui prend en paramètre
        super(nomVariableQuiACauseException + " a la valeur" + valeurErronee + " qui est négative. Veulliez recommencer");
        this.valeurErronee = valeurErronee;
        this.nomVariableQuiACauseException = nomVariableQuiACauseException;
    }
}
