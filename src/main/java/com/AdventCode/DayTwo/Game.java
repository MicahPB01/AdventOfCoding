package com.AdventCode.DayTwo;

import java.util.List;

public class Game {
    private int largestRed;
    private int largestGreen;
    private int largestBlue;
    private int gameID;
    private boolean isValid = true;

    private List<Pull> pulls;





    public Game() {

    }

    public void setPulls(List<Pull> pulls)   {
        this.pulls = pulls;
    }

    public List<Pull> getPulls()   {
        return pulls;
    }

    public void setValid(boolean isValid)   {
        this.isValid = isValid;
    }

    public void setLargestRed(int largestRed) {
        this.largestRed = largestRed;
    }

    public void setLargestGreen(int largestGreen) {
        this.largestGreen = largestGreen;
    }

    public void setLargestBlue(int largestBlue) {
        this.largestBlue = largestBlue;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public int getLargestRed() {
        return largestRed;
    }

    public int getLargestGreen() {
        return largestGreen;
    }

    public int getLargestBlue() {
        return largestBlue;
    }

    public int getGameID() {
        return gameID;
    }





}
