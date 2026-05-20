package com.example.tp1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.SumPathEffect;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    ConstraintLayout parent;
    Surface s;
    EditText texteX, texteY;
    int x, y;
    Button button;
    Paint c;
    Path p;
    Ecouteur ec;

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
        parent = findViewById(R.id.parent);

        ec = new Ecouteur();
        p = new Path();
        s = new Surface(this);
        s.setLayoutParams(new ConstraintLayout.LayoutParams(-1,-1));
        parent.addView(s);

        texteX = findViewById(R.id.x);
        texteY = findViewById(R.id.y);
        button = findViewById(R.id.button);

        button.setOnClickListener(ec);
    }


    private class Ecouteur implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (v == button){
                x = Integer.parseInt(texteX.getText().toString());
                y = Integer.parseInt(texteY.getText().toString());
                if (p.isEmpty()){
                    p.moveTo(x,y);
                } else {
                    p.lineTo(x,y);
                    s.invalidate();
                }
            }
        }
    }

    private class Surface extends View {
        public Surface (Context context){
            super(context);
            c = new Paint(Paint.ANTI_ALIAS_FLAG);
            c.setStyle(Paint.Style.STROKE);
            c.setColor(Color.BLACK);
            c.setStrokeWidth(15);
        }

        @Override
        protected void onDraw(@NonNull Canvas canvas) {
            super.onDraw(canvas);
            setBackgroundColor(Color.LTGRAY);
            canvas.drawPath(p, c);
        }
    }
}