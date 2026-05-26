package com.example.annexe8;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Ecouteur ec;
    Surface surf;
    ConstraintLayout parent;
    EditText textX,textY;
    Button button;
    Path path ;
    int valeurX,valeurY;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ec = new Ecouteur();
        parent = findViewById(R.id.parent);
        surf = new Surface(this);
        surf.setLayoutParams(new ConstraintLayout.LayoutParams(-1,-1));
        parent.addView(surf);
        textX = findViewById(R.id.textX);
        textY = findViewById(R.id.textY);
        button = findViewById(R.id.button);
        path = new Path();


        button.setOnClickListener(ec);


    }
    private class Ecouteur implements View.OnClickListener{

        @Override
        public void onClick(View source) {

             valeurX = Integer.parseInt(textX.getText().toString());
             valeurY = Integer.parseInt(textY.getText().toString());
                if(path.isEmpty()){
                    path.moveTo(valeurX,valeurY);
                }else{
                    path.lineTo(valeurX,valeurY);
                }
            surf.invalidate();


        }
    }
    private  class Surface extends View{
        Paint crayon;
        public Surface(Context context) {
            super(context);
            crayon = new Paint(Paint.ANTI_ALIAS_FLAG);
            crayon.setColor(Color.BLACK);
            crayon.setStyle(Paint.Style.STROKE);//pour le TP!! fill par defaut
            crayon.setStrokeWidth(10);

        }

        @Override
        protected void onDraw(@NonNull Canvas canvas) {
            super.onDraw(canvas);
            //setBackgroundColor(Color.MAGENTA);
            canvas.drawPath(path,crayon);

        }
    }
}