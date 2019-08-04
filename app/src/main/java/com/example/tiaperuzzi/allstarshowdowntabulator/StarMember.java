package com.example.tiaperuzzi.allstarshowdowntabulator;

public abstract class StarMember {
    private String name;
    private int score;

    public abstract void add(int val);

    public abstract String getName();

    public abstract int getScore();

    public abstract void setButton(CustomButton b);

    public abstract void undo(int val);
}
