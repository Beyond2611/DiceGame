package org.example.models;

public class BasePlayer implements AddPoints {
    private String name;
    private int curPoint;
    private boolean isWinner;
    private boolean isLost;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurPoint() {
        return curPoint;
    }

    public void setCurPoint(int curPoint) {
        this.curPoint = curPoint;
    }
    public void setWinner(boolean state){
        this.isWinner = state;
    }
    public boolean getWinner(){
        return this.isWinner;
    }
    public boolean getLost(){
        return this.isLost;
    }
    public void setLost(boolean state){
        this.isLost = state;
    }
    public BasePlayer(String name){
        setName(name);
        setCurPoint(0);
        setWinner(false);
        setLost(false);
    }
    @Override
    public void addPoints(int points){
        setCurPoint(getCurPoint() + points);
    }

    @Override
    public String toString(){
        return getName() + " : " + Integer.toString(getCurPoint());
    }
}
