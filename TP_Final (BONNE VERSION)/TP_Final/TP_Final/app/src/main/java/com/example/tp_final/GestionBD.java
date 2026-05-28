package com.example.tp_final;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;








import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GestionBD extends SQLiteOpenHelper {
    private static GestionBD instance;
    private SQLiteDatabase database;
    private Context context;
    public static GestionBD getInstance(Context context){
        if (instance == null)
            instance = new GestionBD(context);
        return instance;
    }
    private GestionBD(@Nullable Context context) { // doit toujour etre private !
        super(context, "tpFinal", null, 1);
        this.context = context;
        ouvrieConnexionBD();
    }
    public int executerFichier(SQLiteDatabase db , int resourceID)  throws IOException {
        db.execSQL("CREATE TABLE lexique (ortho TEXT,`phon` TEXT,`lemme` TEXT,`cgram` TEXT,`genre` TEXT,`nombre` TEXT,`freqlemfilms` REAL,`freqlemlivres` REAL,`freqfilms` REAL,`freqlivres` REAL,`infover` TEXT,`nbhomogr` INTEGER,`nbhomoph` INTEGER,`islem` INTEGER,`nblettres` INTEGER,`nbphons` INTEGER,`cvcv` TEXT,`p_cvcv` TEXT,`voisorth` INTEGER,`voisphon` INTEGER,`puorth` INTEGER,`puphon` INTEGER,`syll` TEXT,`nbsyll` INTEGER,`cv_cv` TEXT,`orthrenv` TEXT,`phonrenv` TEXT,`orthosyll` TEXT)");
        int compteur = 0;
        InputStream insertStream = context.getResources().openRawResource(resourceID);
        BufferedReader br = new BufferedReader(new InputStreamReader(insertStream)); // traduire les donné brutes
        while (br.ready()){ // tant qu'il n'est pas vide
            String enonce = br.readLine();
            db.execSQL(enonce);
            compteur ++;
        }
        br.close(); // meme chose que le cursor.
        return compteur;
    }
    public void ouvrieConnexionBD(){
        database = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            executerFichier(db,R.raw.data);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public boolean motExiste(String nom){
        nom = nom.toLowerCase();
        String[] tab = {nom};
        Cursor c = database.rawQuery("SELECT * FROM lexique WHERE ortho = ?",tab);
        boolean rep =  c.moveToFirst();
        c.close();
        return rep;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
