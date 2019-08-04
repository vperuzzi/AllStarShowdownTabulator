package com.example.tiaperuzzi.allstarshowdowntabulator;

import java.io.Serializable;

public class Team extends StarMember implements Serializable {
    private String name;
    private int score;
    private Improviser chair1;
    private Improviser chair2;
    private Improviser chair3;
    private CustomButton button;

    public Team(String name, String iName1, String iName2, String iName3){
        this.name = name;
        chair1 = new Improviser(iName1, this);
        chair2= new Improviser(iName2, this);
        chair3 = new Improviser(iName3, this);
        score = 0;
    }

    @Override
    public void setButton(CustomButton button) {
        this.button = button;
    }

    public Improviser getChair1(){
        return chair1;
    }

    public Improviser getChair2(){
        return chair2;
    }

    public Improviser getChair3(){
        return chair3;
    }

    @Override
    public int getScore(){
        return score;
    }

    @Override
    public String getName(){
        return name;
    }

    @Override
    public void add(int val){
        chair1.add(val);
        chair2.add(val);
        chair3.add(val);
    }

    @Override
    public void undo(int val){
        chair1.undo(val);
        chair2.undo(val);
        chair3.undo(val);
    }

    public void undoIndividual(int val){
        score -= val;
        button.setScore("" + score);
    }

    public void addIndividual(int val){
        score += val;
        button.setScore("" + score);
    }

    public void clear(){
        this.score = 0;
        button.setScore(""+score);
        chair1.clear();
        chair2.clear();
        chair3.clear();
    }

}
