package com.example.watertracker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivityVoyage extends AppCompatActivity {

    double total = 0;
    int nbVols, nbHotels = 0;
    ImageView boutonVol, boutonHotel;

    Button boutonTotal;
    TextView titreTotal, vols, hotels;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_voyage);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.total), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        boutonVol = findViewById(R.id.vol);
        boutonHotel = findViewById(R.id.hotel);
        boutonTotal = findViewById(R.id.boutonTotal);
        vols = findViewById(R.id.vols);
        hotels = findViewById(R.id.hotels);
        titreTotal = findViewById(R.id.titreTotal);

        //1
        Ecouteur ec = new Ecouteur();

        //2
        boutonVol.setOnClickListener(ec);
        boutonHotel.setOnClickListener(ec);
        boutonTotal.setOnClickListener(ec);


    }

    private class Ecouteur implements View.OnClickListener{
        Commande c = new Commande();
        @Override
        public void onClick(View source){
            if (source == boutonVol){
                nbVols += 1;
            } else if (source == boutonHotel){
                nbHotels += 1;
            } else {
                HebergementHotel hHotel = new HebergementHotel(nbHotels);
                BilletAvion bAvion = new BilletAvion(nbVols);
                c.ajouterProduit(hHotel);
                c.ajouterProduit(bAvion);
            }



            vols.setText(String.valueOf(nbVols));
            hotels.setText(String.valueOf(nbHotels));
            titreTotal.setText(new DecimalFormat("0,000.00$").format(c.grandTotal()));
        }

    }
}