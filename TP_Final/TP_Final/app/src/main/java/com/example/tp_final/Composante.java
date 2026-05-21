package com.example.atelier3;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.tp_final.R;

public class Composante extends ConstraintLayout {
    private TextView textView1,textView2,textView3;


    public Composante(@NonNull Context context) {
        super(context);
        init(context);
    }

    public Composante(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Composante(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        //boutton.xml
        LayoutInflater.from(context).inflate(R.layout.lettre,this,true);
        //initialiser les composantes internes avec findmy id
//        textView1 = findViewById(R.id.textView4);
//        textView2 = findViewById(R.id.textView5);
//        textView3 = findViewById(R.id.textView6);
    }


}
