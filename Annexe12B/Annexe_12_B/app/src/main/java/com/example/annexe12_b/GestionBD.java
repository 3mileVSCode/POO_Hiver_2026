package com.example.annexe12_b;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class GestionBD extends SQLiteOpenHelper {
    private static GestionBD instance; // ref a lui meme
    //le constructeur doit être prive car singleton
    private SQLiteDatabase database;

    public static GestionBD getInstance(Context context) {
        if (instance == null)
            instance = new GestionBD(context);
        return instance;

    }
    private GestionBD(@Nullable Context context) {
        super(context, "annexe12", null, 1);
        ouvrieConnexionBD(); // creer la data base ou on aurait put le faire dans le oncreate du MainActivie
    }

    // excute une seule fois lorsqu'on installe l'app sur un téléphone
    // s'il ya une erreur , desintalle le projet
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE inventeur (id INTEGER PRIMARY KEY AUTOINCREMENT , nom TEXT , origine TEXT  ,invention TEXT , annee INTEGER )");
        ajouterInventeur(new Inventeur("Laszlo Biro","Hongorie","stylo à bille",1938),db);
        ajouterInventeur(new Inventeur("Benjamin Franklin","Etats-Unis","Paratonnerre",1752),db);
        ajouterInventeur(new Inventeur("Mary Anderson ","Etats-Unis","Essuie-glace",1903),db);
        ajouterInventeur(new Inventeur("Grace Hopper ","Etats-Unis","Compilateur",1952),db);
        ajouterInventeur(new Inventeur("Benoit Rouquayrot ","France","Scaphandre",1864),db);

    }
    public void ajouterInventeur(Inventeur i, SQLiteDatabase db){
        ContentValues cv = new ContentValues();
        cv.put("nom",i.getNom());
        cv.put("origine",i.getOrigine());
        cv.put("invention",i.getInvention());
        cv.put("annee",i.getAnnee());
        db.insert("inventeur",null,cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS inventeur");
        onCreate(db);
    }

    public void ouvrieConnexionBD(){
        database = this.getWritableDatabase();
    }
    public ArrayList<String> retounerInventions(){
        ArrayList<String> listeInventions = new ArrayList<>();
        Cursor cursor = database.rawQuery("select invention from inventeur" , null);// question d'examen : "select origine , invention from inventeur"
        while(cursor.moveToNext()){
            listeInventions.add(cursor.getString(0));
        }
        cursor.close();
        return listeInventions;
    }
    public boolean aBonneReponse(String nom , String invention){

        String[] parametre = {nom, invention};
        Cursor cursor = database.rawQuery("select nom , invention from inventeur WHERE nom = ? AND invention = ?", parametre);
        boolean rep = cursor.moveToFirst();
        cursor.close();
        return rep;

    }
    public int trouverIndice (String nom ) throws Exception{
        String []tab = {nom};
        Cursor c = database.rawQuery("SELECT id FROM inventeur WHERE nom = ? ", tab);
        if(c.moveToFirst()){
            int rep = c.getInt(0) -1;
            c.close();
            return rep;
        }else{
            throw new Exception("Le nom l'inventeur n'est pas dans la table");
        }
    }

}
