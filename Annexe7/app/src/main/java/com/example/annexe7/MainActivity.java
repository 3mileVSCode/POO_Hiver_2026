package com.example.annexe7;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    SurfaceDessin surf;
    ConstraintLayout main;

    Ecouteur ec;

    float xIni, yIni = 0;
    float xFin, yFin = 0;


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

        main = findViewById(R.id.main);

        //1
        surf = new SurfaceDessin(this);
        //2
        surf.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        //3
        main.addView(surf);


        ec = new Ecouteur();
        surf.setOnTouchListener(ec);

    }

    private class SurfaceDessin extends View {
        Paint crayon;

        public SurfaceDessin(Context context) {
            super(context);
            this.setBackgroundResource(R.drawable.carte);
            crayon = new Paint (Paint.ANTI_ALIAS_FLAG); //Anti alias pour smooth out les courbes
            crayon.setColor(Color.RED);
            crayon.setStrokeWidth(15);
        }

        @Override
        protected void onDraw(@NonNull Canvas canvas) {
            super.onDraw(canvas);
            if (xIni != 0 && yIni != 0){
                canvas.drawRect(xIni - 25,yIni - 25,xIni + 25,yIni + 25, crayon);
            }
            if (xFin != 0 && yFin != 0){
                canvas.drawLine(xIni,yIni,xFin,yFin,crayon);
                canvas.drawRect(xFin - 25, yFin - 25, xFin + 25, yFin + 25, crayon);
            }
        }

    }

    private class Ecouteur implements View.OnTouchListener {

        @Override
        public boolean onTouch(View source, MotionEvent event) {
            xFin = 0;
            yFin = 0;
            if (event.getAction() == MotionEvent.ACTION_DOWN){
                xIni = event.getX();
                yIni = event.getY();

            }
            else if (event.getAction() == MotionEvent.ACTION_MOVE){
                xFin = event.getX();
                yFin = event.getY();
            }
            else if (event.getAction() == MotionEvent.ACTION_UP){
                xFin = event.getX();
                yFin = event.getY();
            }
            surf.invalidate();

            return true;
        }
    }
}