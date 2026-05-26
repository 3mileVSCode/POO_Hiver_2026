package com.example.atelier3;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

public class Macomposante extends ConstraintLayout {
    private TextView textView1,textView2,textView3;


    public Macomposante(@NonNull Context context) {
        super(context);
        init(context);
    }

    public Macomposante(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Macomposante(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        //boutton.xml
        LayoutInflater.from(context).inflate(R.layout.ma_composante,this,true);
        //initialiser les composantes internes avec findmy id
        textView1 = findViewById(R.id.textView4);
        textView2 = findViewById(R.id.textView5);
        textView3 = findViewById(R.id.textView6);
    }

    public TextView getTextView1() {
        return textView1;
    }

    public void setTextView1(TextView textView1) {
        this.textView1 = textView1;
    }

    public TextView getTextView2() {
        return textView2;
    }

    public void setTextView2(TextView textView2) {
        this.textView2 = textView2;
    }

    public TextView getTextView3() {
        return textView3;
    }

    public void setTextView3(TextView textView3) {
        this.textView3 = textView3;
    }
}
