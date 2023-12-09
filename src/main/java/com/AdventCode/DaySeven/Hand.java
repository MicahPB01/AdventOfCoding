package com.AdventCode.DaySeven;

import java.util.ArrayList;

public class Hand {

    private String cards;
    private int betValue;
    private int handStrength;
    private ArrayList<String[]> handInfo;


    public Hand(String cards, int betValue)   {

        this.cards = cards;
        this.betValue = betValue;

    }


    public void setHandStrength(int handStrength) {
        this.handStrength = handStrength;
    }

    public void setBetValue(int betValue) {
        this.betValue = betValue;
    }

    public void setCards(String cards) {
        this.cards = cards;
    }


    public int getBetValue() {
        return betValue;
    }

    public int getHandStrength() {
        return handStrength;
    }

    public String getCards() {
        return cards;
    }

    public ArrayList<String[]> getHandInfo() {
        return handInfo;
    }

    public void setHandInfo(ArrayList<String[]> handInfo) {
        this.handInfo = handInfo;
    }
}
