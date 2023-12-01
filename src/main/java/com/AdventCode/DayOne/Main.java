package com.AdventCode.DayOne;

import com.AdventCode.Constants;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Main {






    public static void main(String args[]) throws IOException {



        List<String> input = getInput("C:\\Users\\micah\\IdeaProjects\\AdventOfCoding\\src\\main\\java\\com\\AdventCode\\DayOne\\DayOneInput.txt");
        List<String> cleanedInput = regex(input);
        int total = sumOfInputs(cleanedInput);
        print(total);




    }




    private static List<String> getInput(String filePath) throws IOException {

        List<String> lines = Files.readAllLines(Path.of(filePath));

        return lines;
    }

    private static List<String> regex(List<String> input)   {

        List<String> cleanedStrings = new ArrayList<>();
        int firstNumber = 0;
        int secondNumber = 0;

        for(int i = 0; i < input.size(); i++)   {
            String tempString;
            String tempCleanString;
            String cleanedCurrent;

            ArrayList<Integer> testInteger = new ArrayList<>();



            ArrayList<Integer> locationOfDigits = new ArrayList<>();
            ArrayList<Integer> digits = new ArrayList<>();
            ArrayList<Integer> locationOfSpelledDigits = new ArrayList<>();
            ArrayList<String> spelledNumbers = new ArrayList<>();

            String[] numbers = new String[2];
            String toAdd;


            tempString = input.get(i);

            tempCleanString = tempString.replaceAll("[^0-9 |one|two|three|four|five|six|seven|eight|nine]", "");
            System.out.println(tempCleanString);



            for(int j = 0; j < tempCleanString.length(); j++) {

                if(Character.isDigit(tempCleanString.charAt(j))) {

                    locationOfDigits.add(j);
                    digits.add(Character.getNumericValue(tempCleanString.charAt(j)));
                    System.out.println("There is a digit at location: " + j + " That digit is: " + tempCleanString.charAt(j));

                }
            }

            for(int j = 0; j < tempCleanString.length() - 2; j++)   {

                String currentCheck = StringUtils.substring(tempCleanString, j, j + 5);
                System.out.println(currentCheck);

                for(int k = 0; k < Constants.constantNumbers.length; k++)   {
                    if(currentCheck.endsWith(Constants.constantNumbers[k]))   {
                        cleanedCurrent = Constants.constantNumbers[k];
                        locationOfSpelledDigits.add(j);
                        spelledNumbers.add(cleanedCurrent);
                        System.out.println("There is a spelled out number starting at location: " + j + " That number is: " + cleanedCurrent);
                    }
                }



            }


            if(locationOfDigits.get(0) < locationOfSpelledDigits.get(0))   {
                firstNumber = digits.get(0);
            }
            else   {
                switch (spelledNumbers.get(0))   {
                    case "one":
                        firstNumber = 1;
                        break;
                    case "two":
                        firstNumber = 2;
                        break;
                    case "three":
                        firstNumber = 3;
                        break;
                    case "four":
                        firstNumber = 4;
                        break;
                    case "five":
                        firstNumber = 5;
                        break;
                    case "six":
                        firstNumber = 6;
                        break;
                    case "seven":
                        firstNumber = 7;
                        break;
                    case "eight":
                        firstNumber = 8;
                        break;
                    case "nine":
                        firstNumber = 9;
                }
            }

            if(locationOfDigits.get(locationOfDigits.size() - 1) > locationOfSpelledDigits.get(locationOfSpelledDigits.size() - 1))   {
                secondNumber = digits.get(digits.size() - 1);
            }
            else   {
                System.out.println("SWITCHING: " + spelledNumbers.get(spelledNumbers.size() - 1));
                switch (spelledNumbers.get(spelledNumbers.size() - 1))   {
                    case "one":
                        secondNumber = 1;
                        break;
                    case "two":
                        secondNumber = 2;
                        break;
                    case "three":
                        secondNumber = 3;
                        break;
                    case "four":
                        secondNumber = 4;
                        break;
                    case "five":
                        secondNumber = 5;
                        break;
                    case "six":
                        secondNumber = 6;
                        break;
                    case "seven":
                        secondNumber = 7;
                        break;
                    case "eight":
                        secondNumber = 8;
                        break;
                    case "nine":
                        secondNumber = 9;
                }
            }

            System.out.println("First Number: " + firstNumber + " Second Number: " + secondNumber);
















            numbers[0] = String.valueOf(tempCleanString.charAt(0));
            numbers[1] = String.valueOf(tempCleanString.charAt(tempCleanString.length()-1));

            toAdd = numbers[0] + numbers[1];


            //System.out.println(toAdd);
            cleanedStrings.add(toAdd);

            locationOfDigits.clear();
            locationOfSpelledDigits.clear();
            spelledNumbers.clear();
            digits.clear();


        }

        return cleanedStrings;
    }

    private static int sumOfInputs(List<String> cleanedInputs)   {
        int currentTotal = 0;


        for(int i = 0; i < cleanedInputs.size(); i++)   {
            int nextNumber = Integer.parseInt(cleanedInputs.get(i));
            currentTotal += nextNumber;
        }

        return currentTotal;
    }



    private static void print(int total)   {
        System.out.println(total);
    }

    private static void checkForNumber(String input)   {



    }





}
