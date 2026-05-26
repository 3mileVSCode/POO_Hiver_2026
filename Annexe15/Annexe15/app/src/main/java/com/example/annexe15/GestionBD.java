package com.example.annexe15;

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
        super(context, "annexe15", null, 1);
        this.context = context;
        ouvrieConnexionBD();
    }
    public int executerFichier(SQLiteDatabase db , int resourceID)  throws IOException {
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
            executerFichier(db,R.raw.villes);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public boolean villeExiste(String nom){
        String[] tab = {nom};
        Cursor c = database.rawQuery("SELECT * FROM villes_quebec WHERE NOM = ?",tab);
        boolean rep =  c.moveToFirst();
        c.close();
        return rep;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
