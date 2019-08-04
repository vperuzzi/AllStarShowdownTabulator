package com.example.tiaperuzzi.allstarshowdowntabulator;

import java.io.Serializable;

public class Improviser extends StarMember implements Serializable {
    private String name;
    private int score;
    private Team team;
    private CustomButton button;

    public Improviser(String name, Team team){
        this.name = name;
        this.team = team;
        score = 0;
    }

    @Override
    public void setButton(CustomButton button) {
        this.button = button;
    }

    @Override
    public int getScore(){
        return score;
    }

    @Override
    public String getName(){
        return name;
    }

    public Team getTeam(){
        return team;
    }

    @Override
    public void add(int val){
        score += val;
        button.setScore("" + score);
        team.addIndividual(val);
    }

    public void clear(){
        this.score = 0;
        button.setScore(""+score);
    }

    @Override
    public void undo(int val) {
        score -= val;
        button.setScore(""+score);
        team.undoIndividual(val);
    }
}
