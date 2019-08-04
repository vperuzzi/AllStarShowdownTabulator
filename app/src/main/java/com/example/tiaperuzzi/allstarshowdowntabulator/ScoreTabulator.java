package com.example.tiaperuzzi.allstarshowdowntabulator;

import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Stack;

public class ScoreTabulator extends AppCompatActivity implements View.OnClickListener, PointDialog.NoticeDialogListener, ClearDialog.ClearDialogListener {
    CustomButton cb_t1Name;
    CustomButton cb_t1_i1;
    CustomButton cb_t1_i2;
    CustomButton cb_t1_i3;

    CustomButton cb_t2Name;
    CustomButton cb_t2_i1;
    CustomButton cb_t2_i2;
    CustomButton cb_t2_i3;

    Button clearButton;
    Button undoButton;
    ToggleButton rapidFireButton;

    Boolean rapidFire = false;
    int rapidFireVal;

    StarMember lastPressed;

    Team team1;
    Team team2;

    Stack<StarMember> starMemberStack = new Stack<>();
    Stack<Integer> scoreStack = new Stack<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        team1 = (Team) intent.getSerializableExtra("team1");
        team2 = (Team) intent.getSerializableExtra("team2");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_tabulator);
        clearButton = findViewById(R.id.clearButton);
        undoButton = findViewById(R.id.undoButton);
        rapidFireButton = findViewById(R.id.toggleButton);
        rapidFireButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    rapidFire = true;
                    showDialog();
                    cb_t1Name.setClickable(false);
                    cb_t2Name.setClickable(false);
                }
                else {
                    rapidFire = false;
                    cb_t1Name.setClickable(true);
                    cb_t2Name.setClickable(true);
                }
            }
        });

        cb_t1Name = findViewById(R.id.t1_name);
        cb_t1_i1 = findViewById(R.id.t1_i1name);
        cb_t1_i2 = findViewById(R.id.t1_i2name);
        cb_t1_i3 = findViewById(R.id.t1_i3name);

        cb_t1Name.setOnClickListener(this);
        cb_t1_i1.setOnClickListener(this);
        cb_t1_i2.setOnClickListener(this);
        cb_t1_i3.setOnClickListener(this);

        cb_t1Name.load(team1);
        cb_t1_i1.load(team1.getChair1());
        cb_t1_i2.load(team1.getChair2());
        cb_t1_i3.load(team1.getChair3());

        cb_t2Name = findViewById(R.id.t2_name);
        cb_t2_i1 = findViewById(R.id.t2_i1name);
        cb_t2_i2 = findViewById(R.id.t2_i2name);
        cb_t2_i3 = findViewById(R.id.t2_i3name);

        cb_t2Name.setOnClickListener(this);
        cb_t2_i1.setOnClickListener(this);
        cb_t2_i2.setOnClickListener(this);
        cb_t2_i3.setOnClickListener(this);

        cb_t2Name.load(team2);
        cb_t2_i1.load(team2.getChair1());
        cb_t2_i2.load(team2.getChair2());
        cb_t2_i3.load(team2.getChair3());

    }

    @Override
    public void onClick(View view){
        lastPressed = (StarMember) view.getTag();
        if (!rapidFire)
            showDialog();
        else{
            lastPressed.add(rapidFireVal);
            starMemberStack.push(lastPressed);
            scoreStack.push(rapidFireVal);
        }
    }

    public void showDialog(){
        PointDialog d = new PointDialog();
        d.show(getFragmentManager(), "dialog");
    }

    @Override
    public void onPointDialogPositiveClick(int val){
        if (rapidFire){
           rapidFireVal = val;
           Toast.makeText(this,"Rapid Fire Activated",Toast.LENGTH_SHORT).show();
        }
        else {
            lastPressed.add(val);
            starMemberStack.push(lastPressed);
            scoreStack.push(val);
        }
    }

    @Override
    public void onPointDialogNegativeClick(DialogFragment dialog) {

    }

    public void clearClick(View view){
        ClearDialog c = new ClearDialog();
        c.show(getFragmentManager(), "clear dialog");
    }

    @Override
    public void onClearDialogPositiveClick(){
        team1.clear();
        team2.clear();
        starMemberStack.clear();
        scoreStack.clear();
    }

    @Override
    public void onClearDialogNegativeClick(){

    }

    public void undoClick(View view){
        if (!starMemberStack.isEmpty() && !scoreStack.isEmpty()) {
            StarMember s = starMemberStack.pop();
            int val = scoreStack.pop();
            s.undo(val);
        }
        else{
            Toast.makeText(this,"Nothing to Undo", Toast.LENGTH_SHORT).show();
        }
    }
}
