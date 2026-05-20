package com.example.annexe_13;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class GestionBD extends SQLiteOpenHelper {
    private static GestionBD instance;
    private SQLiteDatabase database;
    public static GestionBD getInstance(Context context){ //constructeur
        if(instance == null)
            instance = new GestionBD(context);
        return instance;
    }

    private GestionBD(Context context){
        super(context,"annexe13", null,1);
        ouvrieConnexionBD();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE bieres (id INTEGER PRIMARY KEY AUTOINCREMENT , nom TEXT, microbrasserie TEXT ,review DECIMAL) ");
    }
    public void ajouterBiere(Evaluation h){
        ContentValues cv = new ContentValues();
        cv.put("nom",h.getNom());
        cv.put("microbrasserie",h.getMicrobrasserie());
        cv.put("review",h.getReview());
        database.insert("bieres",null,cv);
    }
    public ArrayList<String> retournerBiere(){
        ArrayList<String> listBiere = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT nom FROM bieres ORDER BY review DESC LIMIT 3",null);
        while(cursor.moveToNext()){
            listBiere.add(cursor.getString(0));
        }
        cursor.close();
        return listBiere;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS bieres");
        onCreate(db);
    }
    public void ouvrieConnexionBD(){
        database = this.getWritableDatabase();
    }


}
