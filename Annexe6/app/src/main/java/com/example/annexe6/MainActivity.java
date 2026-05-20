package com.example.annexe6;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
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
        //surf.setLayoutParams(new ConstraintLayout.LayoutParams(convertirDpEnPx(200), convertirDpEnPx(200)));
        //surf.setLayoutParams(new ConstraintLayout.LayoutParams(-1,-1)); -1 = MATCH_PARENT

        //3
        main.addView(surf);
    }

    //méthode de conversion de dp en pixels
    public int convertirDpEnPx (int dp){
        float densite = this.getResources().getDisplayMetrics().density;
        return Math.round(densite * dp);
    }


    private class SurfaceDessin extends View {

        Paint crayon;

        public SurfaceDessin(Context context) {
            super(context);
            this.setBackgroundColor(Color.CYAN);
            crayon = new Paint (Paint.ANTI_ALIAS_FLAG); //Anti alias pour smooth out les courbes
            crayon.setColor(Color.BLACK);
        }

        //la méthode est appelée automatiquement quand on instancie la surface de dessin
        @Override
        protected void onDraw(@NonNull Canvas canvas) {
            super.onDraw(canvas);

            canvas.drawCircle(100,100,80,crayon);

            crayon.setColor(Color.YELLOW);
            crayon.setStyle(Paint.Style.STROKE);
            crayon.setStrokeWidth(10);
            canvas.drawCircle(280,100,80,crayon);

            crayon.setStyle(Paint.Style.FILL);
            crayon.setColor(Color.YELLOW);
            canvas.drawArc(400,10,600,200,120,120,true,crayon);
            crayon.setColor(Color.BLUE);
            canvas.drawArc(400,10,600,200,240,120,true,crayon);
            crayon.setColor(Color.RED);
            canvas.drawArc(400,10,600,200,360,120,true,crayon);
        }
    }


}