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

        for(int i = 0; i < input.size(); i++) {
            String tempString;
            String tempCleanString;
            String cleanedCurrent;
            int offset;

            ArrayList<Integer> testInteger = new ArrayList<>();


            ArrayList<Integer> locationOfDigits = new ArrayList<>();
            ArrayList<Integer> digits = new ArrayList<>();
            ArrayList<Integer> locationOfSpelledDigits = new ArrayList<>();
            ArrayList<String> spelledNumbers = new ArrayList<>();

            String[] numbers = new String[2];
            String toAdd;


            tempString = input.get(i);

            tempCleanString = tempString;
            System.out.println(tempCleanString);


            for (int j = 0; j < tempCleanString.length(); j++) {

                if (Character.isDigit(tempCleanString.charAt(j))) {

                    locationOfDigits.add(j);
                    digits.add(Character.getNumericValue(tempCleanString.charAt(j)));
                    System.out.println("There is a digit at location: " + j + " That digit is: " + tempCleanString.charAt(j));

                }
            }

            for (int j = 0; j < tempCleanString.length() - 1; j++) {

                String currentCheck = StringUtils.substring(tempCleanString, j, j + 5);
                System.out.println(currentCheck);

                for (int k = 0; k < Constants.constantNumbers.length; k++) {
                    if ((currentCheck.endsWith(Constants.constantNumbers[k]) && currentCheck.length() == 5) || currentCheck.startsWith(Constants.constantNumbers[k])) {
                        cleanedCurrent = Constants.constantNumbers[k];





                        if(currentCheck.endsWith(Constants.constantNumbers[k])) {
                            offset = switch (cleanedCurrent) {
                                case "one", "two", "six" -> 2;
                                case "four", "five", "nine" -> 1;
                                default -> 0;
                            };
                            //System.out.println("Adding offset of: " + offset);
                            offset += j;
                        }
                        else   {
                            offset = j;
                        }


                        locationOfSpelledDigits.add(offset);
                        spelledNumbers.add(cleanedCurrent);
                        System.out.println("There is a spelled out number starting at location: " + offset + " That number is: " + cleanedCurrent + "TEST: " + tempCleanString.charAt(offset));
                    }
                }


            }




            if (!locationOfDigits.isEmpty() || !locationOfSpelledDigits.isEmpty()) {

                if(digits.size() == 1 && spelledNumbers.size() == 0)   {
                    firstNumber = digits.get(0);
                    secondNumber = firstNumber;
                }

                if(spelledNumbers.size() == 1 && digits.size() == 0)   {
                    firstNumber = switch (spelledNumbers.get(0)) {
                        case "one" -> 1;
                        case "two" -> 2;
                        case "three" -> 3;
                        case "four" -> 4;
                        case "five" -> 5;
                        case "six" -> 6;
                        case "seven" -> 7;
                        case "eight" -> 8;
                        case "nine" -> 9;
                        default -> firstNumber;
                    };
                    secondNumber = firstNumber;
                }

                if(digits.size() > 1 && spelledNumbers.size() == 0)   {
                    firstNumber = digits.get(0);
                    secondNumber = digits.get(digits.size()-1);
                }

                if(spelledNumbers.size() == 2 && digits.size() == 0) {
                    firstNumber = switch (spelledNumbers.get(0)) {
                        case "one" -> 1;
                        case "two" -> 2;
                        case "three" -> 3;
                        case "four" -> 4;
                        case "five" -> 5;
                        case "six" -> 6;
                        case "seven" -> 7;
                        case "eight" -> 8;
                        case "nine" -> 9;
                        default -> firstNumber;
                    };
                    secondNumber = switch (spelledNumbers.get(spelledNumbers.size()-1)) {
                        case "one" -> 1;
                        case "two" -> 2;
                        case "three" -> 3;
                        case "four" -> 4;
                        case "five" -> 5;
                        case "six" -> 6;
                        case "seven" -> 7;
                        case "eight" -> 8;
                        case "nine" -> 9;
                        default -> secondNumber;
                    };
                }



                if (!locationOfDigits.isEmpty() && !locationOfSpelledDigits.isEmpty()) {
                    if (locationOfDigits.get(0) < locationOfSpelledDigits.get(0)) {
                        firstNumber = digits.get(0);
                    } else {
                        firstNumber = switch (spelledNumbers.get(0)) {
                            case "one" -> 1;
                            case "two" -> 2;
                            case "three" -> 3;
                            case "four" -> 4;
                            case "five" -> 5;
                            case "six" -> 6;
                            case "seven" -> 7;
                            case "eight" -> 8;
                            case "nine" -> 9;
                            default -> firstNumber;
                        };
                    }


                    if (locationOfDigits.get(locationOfDigits.size() - 1) > locationOfSpelledDigits.get(locationOfSpelledDigits.size() - 1)) {
                        secondNumber = digits.get(digits.size() - 1);
                    } else {
                        //System.out.println("SWITCHING: " + spelledNumbers.get(spelledNumbers.size() - 1));
                        switch (spelledNumbers.get(spelledNumbers.size() - 1)) {
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
                }









                numbers[0] = String.valueOf(firstNumber);
                numbers[1] = String.valueOf(secondNumber);

                toAdd = numbers[0] + numbers[1];


                //System.out.println(toAdd);
                cleanedStrings.add(toAdd);

                locationOfDigits = null;
                locationOfSpelledDigits = null;
                spelledNumbers = null;
                digits = null;

                System.out.println("First Number: " + firstNumber + " Second Number: " + secondNumber);
                firstNumber = 0;
                secondNumber = 0;
            }
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






}
