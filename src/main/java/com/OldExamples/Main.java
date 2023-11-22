package com.OldExamples;

import javax.swing.text.Keymap;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Scanner;





public class Main {
    //switch constants
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int FOUR = 4;
    private static final int FIVE = 5;
    private static final int SIX = 6;
    private static final int SEVEN = 7;
    private static final int EIGHT = 8;
    private static final int NINE = 9;

    //scanner for initial input
    private static final Scanner scanner = new Scanner(System.in);


    private static String[] singles = new String[]{"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    private static String[] doubles = new String[]{"", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
    private static String[] multiples = new String[]{"","", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "sighty", "ninety"};
    private static String[] powers = new String[]{"", "hundred", "thousand", "million", "billion"};



    public static void main(String[] args) {

        while(true) {
            String startingInput = receiveInput();
            HashMap mapOfNumbers = findIntegers(startingInput);
            printResults(mapOfNumbers);

        }




    }

    private static String receiveInput()   {
        System.out.println("Enter starting number: ");
        return scanner.nextLine();
    }

    private static HashMap findIntegers(String input)   {
        HashMap<Integer, Integer> mapOfNumbers = new HashMap<>();

        //initialize map for numbers 0-9 each having account of 0
        for(int i = 0; i < 10; i++)   {
            mapOfNumbers.put(i,0);
        }

        //increment appearances of each integer
        for(int i = 0; i < input.length(); i++)   {
            int activeInt = Integer.parseInt(String.valueOf(input.charAt(i)));

            switch (activeInt) {
                case 0 -> mapOfNumbers.put(0, mapOfNumbers.get(0) + 1);
                case 1 -> mapOfNumbers.put(1, mapOfNumbers.get(1) + 1);
                case 2 -> mapOfNumbers.put(2, mapOfNumbers.get(2) + 1);
                case 3 -> mapOfNumbers.put(3, mapOfNumbers.get(3) + 1);
                case 4 -> mapOfNumbers.put(4, mapOfNumbers.get(4) + 1);
                case 5 -> mapOfNumbers.put(5, mapOfNumbers.get(5) + 1);
                case 6 -> mapOfNumbers.put(6, mapOfNumbers.get(6) + 1);
                case 7 -> mapOfNumbers.put(7, mapOfNumbers.get(7) + 1);
                case 8 -> mapOfNumbers.put(8, mapOfNumbers.get(8) + 1);
                case 9 -> mapOfNumbers.put(9, mapOfNumbers.get(9) + 1);
                default -> System.out.println("Error in parsing numbers");
            }
        }

        return mapOfNumbers;
    }

    private static void printResults(HashMap<Integer, Integer> mapOfNumbers)   {

        String printStatement = null;

        for(int i = 0; i < mapOfNumbers.size(); i ++)   {
            if(mapOfNumbers.get(i) > 0)   {

                switch (i) {
                    case ZERO -> {
                        if (mapOfNumbers.get(ZERO) > 1) {
                            printStatement = translateIntToString(mapOfNumbers.get(ZERO)) + " Zeros";
                        } else {
                            printStatement = translateIntToString(mapOfNumbers.get(ZERO)) + " Zero";
                        }
                    }
                    case ONE -> {
                        if (mapOfNumbers.get(ONE) > 1) {
                            printStatement = translateIntToString(mapOfNumbers.get(ONE)) + " Ones";
                        } else {
                            printStatement = translateIntToString(mapOfNumbers.get(ONE)) + " One";
                        }
                    }
                    case TWO -> {
                        if (mapOfNumbers.get(TWO) > 1) {
                            printStatement = translateIntToString(mapOfNumbers.get(TWO)) + " Twos";
                        } else {
                            printStatement = translateIntToString(mapOfNumbers.get(TWO)) + " Two";
                        }
                    }
                    case THREE -> {
                        if (mapOfNumbers.get(THREE) > 1) {
                            printStatement = translateIntToString(mapOfNumbers.get(THREE)) + " Threes";
                        } else {
                            printStatement = translateIntToString(mapOfNumbers.get(THREE)) + " Three";
                        }
                    }
                    case FOUR -> {
                        if (mapOfNumbers.get(FOUR) > 1) {
                            printStatement = translateIntToString(mapOfNumbers.get(FOUR)) + " Fours";
                        } else {
                            printStatement = translateIntToString(mapOfNumbers.get(FOUR)) + " Four";
                        }
                    }
                    case FIVE -> {
                        if (mapOfNumbers.get(FIVE) > 1) {
                            printStatement = translateIntToString(mapOfNumbers.get(FIVE)) + " Fives";
                        } else {
                            printStatement = translateIntToString(mapOfNumbers.get(FIVE)) + " Five";
                        }
                    }
                    case SIX -> {
                        if (mapOfNumbers.get(SIX) > 1) {
                            printStatement = translateIntToString(mapOfNumbers.get(SIX)) + " Sixes";
                        } else {
                            printStatement = translateIntToString(mapOfNumbers.get(SIX)) + " Six";
                        }
                    }
                    case SEVEN -> {
                        if (mapOfNumbers.get(SEVEN) > 1) {
                            printStatement = translateIntToString(mapOfNumbers.get(SEVEN)) + " Sevens";
                        } else {
                            printStatement = translateIntToString(mapOfNumbers.get(SEVEN)) + " Seven";
                        }
                    }
                    case EIGHT -> {
                        if (mapOfNumbers.get(EIGHT) > 1) {
                            printStatement = translateIntToString(mapOfNumbers.get(EIGHT)) + " Eights";
                        } else {
                            printStatement = translateIntToString(mapOfNumbers.get(EIGHT)) + " Eight";
                        }
                    }
                    case NINE -> {
                        if (mapOfNumbers.get(TWO) > 1) {
                            printStatement = translateIntToString(mapOfNumbers.get(NINE)) + " Nines";
                        } else {
                            printStatement = translateIntToString(mapOfNumbers.get(NINE)) + " Nine";
                        }
                    }
                    default -> System.out.println("Error printing");
                }
                System.out.println(printStatement);
            }


        }



    }

    private static String translateIntToString(int input)   {
        String temp = String.valueOf(input);
        int heldTens;
        int numDigits = temp.length();
        int heldOnes;
        int heldHundreds;
        String buildNumber;

        //System.out.println("There are " + numDigits + " Digits");

        if(numDigits == 1)   {
            return singles[input];
        }

        if(numDigits == 2 && input <= 19)   {
            return doubles[input-10];
        }

        if(numDigits == 2 && input > 19 && input < 100)   {
            //get ones place
            heldOnes = input % 10;
            //get tens place
            heldTens = input / 10;

            buildNumber = multiples[heldTens] + singles[heldOnes];
            return buildNumber;
        }
//test with 111
        if(numDigits == 3 && input >= 100 && input <= 119)   {
            heldTens = (input / 100) % 10; //111 / 100 % 10

            heldHundreds = input / 100; // 111 / 100


            buildNumber = singles[heldHundreds] + powers[heldHundreds] + doubles[heldTens + 1];
            return buildNumber;
        }

        if(numDigits == 3 && input >=100 && input > 119)   {
            heldOnes = input % 10;

            heldTens = (input / 10) % 10; //111 / 100 % 10

            heldHundreds = input / 100; // 111 / 100


            buildNumber = singles[heldHundreds] + powers[heldHundreds - 1] + multiples[heldTens] + singles[heldOnes];
            return buildNumber;
        }
        return null;
    }



}