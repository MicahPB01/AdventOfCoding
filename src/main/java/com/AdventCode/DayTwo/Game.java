package com.AdventCode.DayTwo;

public class Game {
    private int largestRed;
    private int largestGreen;
    private int largestBlue;
    private int gameID;


    public  Game(int largestRed, int largestGreen, int largestBlue, int gameID)   {
        this.largestRed = largestRed;
        this.largestGreen = largestGreen;
        this.largestBlue = largestBlue;
        this.gameID = gameID;
    }




    public int getLargestRed()   {
        return largestRed;
    }

    public int getLargestGreen()   {
        return largestGreen;
    }
    public int getLargestBlue()   {
        return largestBlue;
    }
    public int getGameID()   {
        return gameID;
    }





}
