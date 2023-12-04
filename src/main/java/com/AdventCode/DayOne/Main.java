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
        List<String> input = getInput("src/main/java/com/AdventCode/DayOne/DayOneInput.txt");
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

        for(int i = 0; i < input.size(); i++) {
            String tempString;
            String cleanedCurrent;
            int offset;

            ArrayList<Integer> locationOfDigits = new ArrayList<>();
            ArrayList<Integer> digits = new ArrayList<>();
            ArrayList<Integer> locationOfSpelledDigits = new ArrayList<>();
            ArrayList<String> spelledNumbers = new ArrayList<>();

            String[] numbers = new String[2];
            String toAdd;

            tempString = input.get(i);


            //System.out.println(tempString);


            for (int j = 0; j < tempString.length(); j++) {

                String currentCheck = StringUtils.substring(tempString, j, j + 5);
                System.out.println(currentCheck);

                if (Character.isDigit(tempString.charAt(j))) {

                    locationOfDigits.add(j);
                    digits.add(Character.getNumericValue(tempString.charAt(j)));
                    //System.out.println("There is a digit at location: " + j + " That digit is: " + tempString.charAt(j));

                }
                else {

                    for (int k = 0; k < Constants.constantNumbers.length; k++) {
                        //when checking for ending, the length has to still be five otherwise a number might be double counted. Without ==5, zztwo will give us two. THen the next run will be ztwo which would give us two again
                        if ((currentCheck.endsWith(Constants.constantNumbers[k]) && currentCheck.length() == 5) || currentCheck.startsWith(Constants.constantNumbers[k])) {
                            cleanedCurrent = Constants.constantNumbers[k];


                            if (currentCheck.endsWith(Constants.constantNumbers[k])) {
                                offset = switch (cleanedCurrent) {
                                    case "one", "two", "six" -> 2;
                                    case "four", "five", "nine" -> 1;
                                    default -> 0;
                                };
                                //System.out.println("Adding offset of: " + offset);
                                offset += j;
                            } else {
                                offset = j;
                            }

                            locationOfSpelledDigits.add(offset);
                            spelledNumbers.add(cleanedCurrent);
                            System.out.println("There is a spelled out number starting at location: " + offset + " That number is: " + cleanedCurrent + "TEST: " + tempString.charAt(offset));
                        }
                    }

                }
            }


            if (!locationOfDigits.isEmpty() || !locationOfSpelledDigits.isEmpty()) {
                //row with only 1 digit
                if (digits.size() == 1 && spelledNumbers.size() == 0) {
                    firstNumber = digits.get(0);
                    secondNumber = firstNumber;
                }
                //row with only one spelled number
                else if (spelledNumbers.size() == 1 && digits.size() == 0) {
                    firstNumber = getSpelledNumberValue(spelledNumbers.get(0));
                    secondNumber = firstNumber;
                }
                //row with only digits
                else if (digits.size() > 1 && spelledNumbers.size() == 0) {
                    firstNumber = digits.get(0);
                    secondNumber = digits.get(digits.size() - 1);
                }
                //row with only spelled out numbers
                else if (spelledNumbers.size() > 2 && digits.size() == 0) {
                    firstNumber = getSpelledNumberValue(spelledNumbers.get(0));
                    secondNumber = getSpelledNumberValue(spelledNumbers.get(spelledNumbers.size() - 1));
                }
                //all other rows
                else if (!locationOfDigits.isEmpty() && !locationOfSpelledDigits.isEmpty()) {
                    firstNumber = locationOfDigits.get(0) < locationOfSpelledDigits.get(0) ?
                            digits.get(0) :
                            getSpelledNumberValue(spelledNumbers.get(0));

                    secondNumber = locationOfDigits.get(locationOfDigits.size() - 1) > locationOfSpelledDigits.get(locationOfSpelledDigits.size() - 1) ?
                            digits.get(digits.size() - 1) :
                            getSpelledNumberValue(spelledNumbers.get(spelledNumbers.size() - 1));
                }

                //assign the two numbers
                numbers[0] = String.valueOf(firstNumber);
                numbers[1] = String.valueOf(secondNumber);
                toAdd = numbers[0] + numbers[1];

                cleanedStrings.add(toAdd);
                //System.out.println("First Number: " + firstNumber + " Second Number: " + secondNumber);
                //reset numbers
                firstNumber = 0;
                secondNumber = 0;
            }

        }
        return cleanedStrings;
    }

    private static int sumOfInputs(List<String> cleanedInputs)   {
        int currentTotal = 0;

        for (String cleanedInput : cleanedInputs) {
            int nextNumber = Integer.parseInt(cleanedInput);
            currentTotal += nextNumber;
        }

        return currentTotal;
    }

    private static void print(int total)   {
        System.out.println(total);
    }

    private static int getSpelledNumberValue(String spelledNumber)   {
        return switch (spelledNumber) {
            case "one" -> 1;
            case "two" -> 2;
            case "three" -> 3;
            case "four" -> 4;
            case "five" -> 5;
            case "six" -> 6;
            case "seven" -> 7;
            case "eight" -> 8;
            case "nine" -> 9;
            default -> 0;
        };
    }






}
