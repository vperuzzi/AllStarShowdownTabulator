package com.example.tiaperuzzi.allstarshowdowntabulator;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class CustomButton extends ConstraintLayout {
    private TextView name;
    private TextView score;

    public CustomButton(Context context) {
        super(context);
        init();
    }

    public CustomButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    public void setScore(String newScore){
        score.setText(newScore);
    }

    public void setName(String newName){
        name.setText(newName);
    }

    public void init(){
        inflate(getContext(), R.layout.custom_button, this);
        this.name = (TextView) findViewById(R.id.cb_name);
        this.score = (TextView) findViewById(R.id.cb_score);
    }

    public void load(StarMember s){
        s.setButton(this);
        this.setTag(s);
        this.setName(s.getName());
        this.setScore(""+0);
    }
}
