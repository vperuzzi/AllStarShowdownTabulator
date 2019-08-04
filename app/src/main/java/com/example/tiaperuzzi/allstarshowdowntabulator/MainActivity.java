package com.example.tiaperuzzi.allstarshowdowntabulator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public Team team1;
    public Team team2;

    private Button go;

    private EditText et_team1Name;
    private EditText et_t1_improviser1;
    private EditText et_t1_improviser2;
    private EditText et_t1_improviser3;
    private EditText et_team2Name;
    private EditText et_t2_improviser1;
    private EditText et_t2_improviser2;
    private EditText et_t2_improviser3;

    private String team1Name;
    private String t1_improviser1;
    private String t1_improviser2;
    private String t1_improviser3;
    private String team2Name;
    private String t2_improviser1;
    private String t2_improviser2;
    private String t2_improviser3;

    private ArrayList<EditText> allNames;

    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            disableButton();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        allNames = new ArrayList<EditText>();
        go = findViewById(R.id.go);
        et_team1Name = findViewById(R.id.Team1Name);
        allNames.add(et_team1Name);
        et_t1_improviser1 = findViewById(R.id.t1_improviser1);
        allNames.add(et_t1_improviser1);
        et_t1_improviser2 = findViewById(R.id.t1_improviser2);
        allNames.add(et_t1_improviser2);
        et_t1_improviser3 = findViewById(R.id.t1_improviser3);
        allNames.add(et_t1_improviser3);
        et_team2Name = findViewById(R.id.Team2Name);
        allNames.add(et_team2Name);
        et_t2_improviser1 = findViewById(R.id.t2_improviser1);
        allNames.add(et_t2_improviser1);
        et_t2_improviser2 = findViewById(R.id.t2_improviser2);
        allNames.add(et_t2_improviser2);
        et_t2_improviser3 = findViewById(R.id.t2_improviser3);
        allNames.add(et_t2_improviser3);

        for (EditText e : allNames){
            e.addTextChangedListener(watcher);
        }
        disableButton();
    }

    public void createShow(View view){
        team1Name = et_team1Name.getText().toString();
        t1_improviser1 = et_t1_improviser1.getText().toString();
        t1_improviser2 = et_t1_improviser2.getText().toString();
        t1_improviser3 = et_t1_improviser3.getText().toString();
        team2Name = et_team2Name.getText().toString();
        t2_improviser1 = et_t2_improviser1.getText().toString();
        t2_improviser2 = et_t2_improviser2.getText().toString();
        t2_improviser3 = et_t2_improviser3.getText().toString();
        team1 = new Team(team1Name, t1_improviser1, t1_improviser2, t1_improviser3);
        team2 = new Team(team2Name, t2_improviser1, t2_improviser2, t2_improviser3);
        sendTeams();
    }

    public void sendTeams() {
       Intent intent = new Intent(this, ScoreTabulator.class);

       intent.putExtra("team1", team1);
       intent.putExtra("team2", team2);
       startActivity(intent);
    }

    private void disableButton(){
        go.setEnabled(!areFieldsEmpty());
    }

    private boolean areFieldsEmpty(){
        for (EditText e : allNames){
            if (e.getText().toString().length() == 0)
                return true;
        }
        return false;
    }

}
