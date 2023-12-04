package com.AdventCode.DayFour;

public class LotteryCard {


    int[] winningNumbers;
    int[] drawnNumbers;
    int cardValue;
    int cardID;
    int numberOfMatchedNumbers;
    int numberOfCards = 1;

    public LotteryCard(int cardID, int[] winningNumbers, int[] drawnNumbers)   {
        this.winningNumbers = winningNumbers;
        this.drawnNumbers = drawnNumbers;
        this.cardID = cardID;
    }


    public void setCardValue(int cardValue) {
        this.cardValue = cardValue;
    }

    public int getCardValue()   {
        return cardValue;
    }

    public int[] getWinningNumbers()   {
        return winningNumbers;
    }

    public int[] getDrawnNumbers()   {
        return drawnNumbers;
    }

    public int getCardID()   {
        return cardID;
    }

    public void setNumberOfMatchedNumbers(int numberOfMatchedNumbers)   {
        this.numberOfMatchedNumbers = numberOfMatchedNumbers;
    }

    public int getNumberOfMatchedNumbers()   {
        return numberOfMatchedNumbers;
    }

    public void setNumberOfCards(int numberOfCards)   {
        this.numberOfCards = numberOfCards;
    }
    public int getNumberOfCards()   {
        return numberOfCards;
    }

    public void incrementNumberOfCards()   {
        numberOfCards++;
    }





}
