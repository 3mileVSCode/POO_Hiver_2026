package com.example.annexe_a;



import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.text.DecimalFormat;

public class PlacementActivity extends AppCompatActivity {

    private EditText champMontant;
    private NumberPicker numberPicker;
    private TextView labelReponse;
    private Button bouton;
    Ecouteur ec ;

    Placement p;





    public DecimalFormat d = new DecimalFormat("0.00$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placement);

        champMontant =  findViewById(R.id.champMontant);
        numberPicker = findViewById(R.id.numberPicker);
        labelReponse =  findViewById(R.id.labelReponse);
        bouton = findViewById(R.id.bouton);

        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(5); // valeur 1 2 3 à 5
        NumberPicker.Formatter formatter = new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                int temp = value * 12;
                return "" + temp;
            } //affichage
        };

        numberPicker.setFormatter(formatter);
        
        // 3 étapes
       ec = new Ecouteur();
       bouton.setOnClickListener(ec);




    }
    public class Ecouteur implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if(v == bouton){
                //bloc de code susceptible de lancer un NumberFormatException
                try{
                    double montant = Double.parseDouble(champMontant.getText().toString());
                    int mois = numberPicker.getValue()*12;
                    p = new Placement(montant,mois);
                    //affiher montant finale
                    labelReponse.setText(d.format(p.calculerMontantFinal()));
                }
                catch (NumberFormatException nfe) { //peut mettre RuntimeException ou Exception!
                    //jamais le laisser vide ! --> pas de resultat
                    creerAlertDialog("Le montant n'est pas valide....recommencez");
                    champMontant.setText("");
                    champMontant.setHint("ex.:10000");
                    champMontant.requestFocus(); //placer sur le bon champ

//                }//on peut attraper une seule ball une seule fois!
//                catch (NullPointerException npe){
//                    npe.printStackTrace();
//                }
//                //Tout les reste de exception vont dans ce catch ! on peut pas le mettre dans avnat les autres execptions car il prend tout (du plus spécifique au general!)
//                catch (Exception e){
//                    e.printStackTrace();
//                }
                }catch (NegatitifException ne){
                    creerAlertDialog(ne.getMessage());
                    champMontant.setText("");
                    champMontant.setHint("ex:100");
                    champMontant.requestFocus();
                }

            }


        }
    }


    //pour créer une boite de dialogue simple
    public void creerAlertDialog(String message) {


        AlertDialog.Builder builder = new AlertDialog.Builder(PlacementActivity.this);

        //on peut faire ca !!
        builder.setMessage(message)
                .setTitle("Erreur");


        AlertDialog dialog = builder.create();
        dialog.show();
    }
}








