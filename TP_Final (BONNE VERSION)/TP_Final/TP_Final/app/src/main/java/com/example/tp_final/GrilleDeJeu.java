package com.example.tp_final;

public class GrilleDeJeu {
    private Lettre[][] lettres = new Lettre[4][4];

    public GrilleDeJeu(){
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                lettres[i][j] = new Lettre();
            }
        }

        //Donner les bonus
        //Tant que le multiplicateur est pas NORMAL on génère une autre case pour attribuer le prochain power up

        // DOUBLE_LETTRE
        int row = genererRandInt(4);
        int col = genererRandInt(4);
        lettres[row][col].setMultiplicateur(Lettre.Multiplicateur.DOUBLE_LETTRE);

        // DOUBLE_LETTRE
        do {
            row = genererRandInt(4);
            col = genererRandInt(4);
        } while (lettres[row][col].getMultiplicateur() != Lettre.Multiplicateur.NORMAL);
        lettres[row][col].setMultiplicateur(Lettre.Multiplicateur.DOUBLE_LETTRE);

        // TRIPLE_LETTRE
        do {
            row = genererRandInt(4);
            col = genererRandInt(4);
        } while (lettres[row][col].getMultiplicateur() != Lettre.Multiplicateur.NORMAL);
        lettres[row][col].setMultiplicateur(Lettre.Multiplicateur.TRIPLE_LETTRE);

        // MOT_DOUBLE
        do {
            row = genererRandInt(4);
            col = genererRandInt(4);
        } while (lettres[row][col].getMultiplicateur() != Lettre.Multiplicateur.NORMAL);
        lettres[row][col].setMultiplicateur(Lettre.Multiplicateur.MOT_DOUBLE);
    }

    public int genererRandInt(int maxExclu){
        return (int)(Math.random() * maxExclu);
    }
    public Lettre getLettre( int row, int col) {
        return lettres[row][col];
    }
    public Lettre[][] getTabLettres(){
        return lettres;
    }

}
