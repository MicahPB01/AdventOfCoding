package com.AdventCode.DayTwo;

public class Pull {


    private int numberOfReds;
    private int numberOfGreens;
    private int numberOfBlues;

    public Pull(int numberOfReds, int numberOfGreens, int numberOfBlues)   {
        this.numberOfReds = numberOfReds;
        this.numberOfGreens = numberOfGreens;
        this.numberOfBlues = numberOfBlues;
    }


    public int getNumberOfReds() {
        return numberOfReds;
    }

    public void setNumberOfReds(int numberOfReds) {
        this.numberOfReds = numberOfReds;
    }

    public int getNumberOfGreens() {
        return numberOfGreens;
    }

    public void setNumberOfGreens(int numberOfGreens) {
        this.numberOfGreens = numberOfGreens;
    }

    public int getNumberOfBlues() {
        return numberOfBlues;
    }

    public void setNumberOfBlues(int numberOfBlues) {
        this.numberOfBlues = numberOfBlues;
    }
}
