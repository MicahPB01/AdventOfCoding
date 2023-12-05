package com.AdventCode.DayFour;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class PartTwo {

    private static final int AMOUNT_OF_WINNING_NUMBERS = 10;
    private static final int AMOUNT_OF_DRAWN_NUMBERS = 25;




    public static void main(String[] args) throws IOException {

        List<String> input =  readLines("src/main/java/com/AdventCode/DayFour/DayFourInput.txt");
        List<LotteryCard> lotteryCards = createLotteryCards(input);

        List<LotteryCard> updatedCards = findMultiples(lotteryCards);

        int total = calculateTotalValueOfAllCards(updatedCards);
        printResults(total);

    }

    private static List<String> readLines(String filePath) throws IOException {

        List<String> input = Files.readAllLines(Path.of(filePath));

        return input;
    }

    private static List<LotteryCard> createLotteryCards(List<String> input)   {

        List<LotteryCard> lotteryCards = new ArrayList<>();

        for(int i = 0; i < input.size(); i ++)   {
            LotteryCard currentCard = buildCard(input.get(i));
            lotteryCards.add(currentCard);
        }

        return lotteryCards;
    }

    private static LotteryCard buildCard(String input)   {
        int gameID;
        int[] winningNumbers;
        int[] drawnNumbers;
        int cardValue;


        gameID = findCardNumber(input);
        winningNumbers = findWinningNumbers(input);
        drawnNumbers = findDrawnNumbers(input);

        LotteryCard lotteryCard = new LotteryCard(gameID, winningNumbers, drawnNumbers);



        cardValue = findValueOfCard(lotteryCard);

        lotteryCard.setCardValue(cardValue);


        return  lotteryCard;

    }


    private static int findCardNumber(String input)   {
        String cleanedString;
        String[] tempString;
        int cardNumber;

        tempString = input.split(":");
        cleanedString = tempString[0].replaceAll("[^0-9]+", "");
        cardNumber = Integer.parseInt(cleanedString);

        return cardNumber;
    }

    private static int[] findWinningNumbers(String input)   {
        int[] winningNumbers = new int[AMOUNT_OF_DRAWN_NUMBERS];
        String[] colonSplit;
        String[] barSplit;
        String[] spaceSplit;
        String cleanedString;

        //find beginning of winning numbers
        colonSplit = input.split(": ");
        //find end of winning numbers
        barSplit = colonSplit[1].split(" \\|");

        //remove extra spaces
        cleanedString = barSplit[0].replaceAll("  ", " ");
        cleanedString = cleanedString.trim();

        //split winning numbers up
        spaceSplit = cleanedString.split(" ");

        //convert strings to numbers
        for(int i = 0; i < AMOUNT_OF_WINNING_NUMBERS; i++)   {

            winningNumbers[i] = convertStringToInt(spaceSplit[i]);

        }

        return winningNumbers;
    }

    private static int[] findDrawnNumbers(String input)   {
        int[] drawnNumbers = new int[AMOUNT_OF_DRAWN_NUMBERS];
        String[] barSplit;
        String[] spaceSplit;
        String cleanedString;

        //find beginning of drawn numbers
        barSplit = input.split("\\| ");

        //remove extra spaces
        cleanedString = barSplit[1].replaceAll("  ", " ");
        cleanedString = cleanedString.trim();

        spaceSplit = cleanedString.split(" ");

        for(int i = 0; i < AMOUNT_OF_DRAWN_NUMBERS; i++)   {
            drawnNumbers[i] = convertStringToInt(spaceSplit[i]);
        }

        return drawnNumbers;

    }

    private static int findValueOfCard(LotteryCard lotteryCard)   {
        int[] winningNumbers = lotteryCard.getWinningNumbers();
        int[] drawnNumbers = lotteryCard.getDrawnNumbers();
        int numberOfMatchedNumbers = 0;
        int cardValue;
        boolean foundNumber;



        for(int i = 0; i < AMOUNT_OF_WINNING_NUMBERS; i++)   {


            foundNumber = compareWinningToDrawnNumbers(drawnNumbers, winningNumbers[i]);

            if(foundNumber)   {
                numberOfMatchedNumbers++;
            }


        }

        lotteryCard.setNumberOfMatchedNumbers(numberOfMatchedNumbers);


        cardValue = createValueOfCardFromNumberOfMatchedNumbers(numberOfMatchedNumbers);



        return cardValue;

    }

    private static boolean compareWinningToDrawnNumbers(int[] drawnNumbers, int currentWinningNumber)   {


        for(int i = 0; i < AMOUNT_OF_DRAWN_NUMBERS; i++)   {

            if(drawnNumbers[i] == currentWinningNumber)   {
                return true;
            }


        }
        return false;
    }

    private static int createValueOfCardFromNumberOfMatchedNumbers(int numberOfMatchedValues)   {
        int value = 1;


        for(int i = 0; i < numberOfMatchedValues - 1; i++)   {
            value *= 2;
        }

        if(numberOfMatchedValues == 0 )   {
            value = 0;
        }

        return value;
    }

    private static int calculateTotalValueOfAllCards(List<LotteryCard> lotteryCards)   {
        int total = 0;

        for(int i = 0; i < lotteryCards.size(); i++)   {

            total += lotteryCards.get(i).getNumberOfCards();

        }
        return total;

    }

    private static List<LotteryCard> findMultiples(List<LotteryCard> lotteryCards) {
        List<LotteryCard> updatedCards = new ArrayList<>(lotteryCards);

        for (int i = 0; i < updatedCards.size(); i++) {

            incrementCards(updatedCards, i);
        }

        return updatedCards;
    }


    private static void incrementCards(List<LotteryCard> cards, int index) {
        LotteryCard currentCard = cards.get(index);
        int originalNumberOfCards = currentCard.getNumberOfCards();

        for (int k = 0; k < originalNumberOfCards; k++) {
            incrementCardsForMatchedNumbers(cards, index);
        }
    }

    private static void incrementCardsForMatchedNumbers(List<LotteryCard> cards, int index) {
        LotteryCard currentCard = cards.get(index);

        for (int j = 1; j <= currentCard.numberOfMatchedNumbers; j++) {
            cards.get(index + j).incrementNumberOfCards();
        }
    }

    private static void printResults(int total)   {
        System.out.println("Total: " + total);
    }

    private static int convertStringToInt(String string)   {

        string = string.trim();
        return Integer.parseInt(string);

    }


}
