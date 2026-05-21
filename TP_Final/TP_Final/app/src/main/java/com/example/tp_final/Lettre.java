package com.example.tp_final;

import java.util.Random;

public class Lettre {
    private char l;
    private int points;
    private int lettreAleatoire;
    private char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
                                'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
                                'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public Lettre(){
        //génère un nombre entre 0 et 25
        lettreAleatoire = (int)(Math.random() * 26);
        //stock la lettre random
        l = alphabet[lettreAleatoire];
        //donne le nombre de points
        points = attributionPoints(l);
    }

    public int getPoints(){
        return points;
    }
    public int attributionPoints(char c){
        int point;
        switch (c) {
            case 'A':
                point = 1;
                break;
            case 'B':
                point = 3;
                break;
            case 'C':
                point = 3;
                break;
            case 'D':
                point = 2;
                break;
            case 'E':
                point = 1;
                break;
            case 'F':
                point = 4;
                break;
            case 'G':
                point = 2;
                break;
            case 'H':
                point = 4;
                break;
            case 'I':
                point = 1;
                break;
            case 'J':
                point = 8;
                break;
            case 'K':
                point = 5;
                break;
            case 'L':
                point = 1;
                break;
            case 'M':
                point = 3;
                break;
            case 'N':
                point = 1;
                break;
            case 'O':
                point = 1;
                break;
            case 'P':
                point = 3;
                break;
            case 'Q':
                point = 10;
                break;
            case 'R':
                point = 1;
                break;
            case 'S':
                point = 1;
                break;
            case 'T':
                point = 1;
                break;
            case 'U':
                point = 1;
                break;
            case 'V':
                point = 4;
                break;
            case 'W':
                point = 4;
                break;
            case 'X':
                point = 8;
                break;
            case 'Y':
                point = 4;
                break;
            case 'Z':
                point = 10;
                break;
            default:
                point = 0;
        }

        return point;
    }
}
