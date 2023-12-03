package com.AdventCode.DayThree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PartOne {

    private static final String[] symbols = {"*", "#", "+", "$"};
    private static final int length = 140;


    public static void main(String args[]) throws IOException {
        String filePath = "src/main/java/com/AdventCode/DayThree/DayThreeInput.txt";

        String[][] input = getInput(filePath);


        mainLogic(input);


    }

    private static void mainLogic(String[][] input) {
        String[][] filledOutArray = fillOutArray(input);
        int total = 0;

        for(int i = 0; i < filledOutArray.length; i++)   {
            for(int j = 0; j < filledOutArray[i].length; j++)   {


                   // System.out.println("CHECKING IF " + filledOutArray[i][j] + " is a number");
                    if(filledOutArray[i][j].matches("[0-9]+")) {
                       //System.out.println("CHECKING TO SEE IF " + input[i][j] + " has a symbol the location is" + input[i][j].length() + " digits");



                    //checking for a symbol to the right
                    if (j + 1 < length && filledOutArray[i][j + 1].matches("[^0-9.]")) {
                        //System.out.println("FOUND A SYMBOL");
                        System.out.println("Adding: " + input[i][j] + " to " + total);
                        total += Integer.parseInt(filledOutArray[i][j]);


                    //check for symbol to the left
                    }
                    else if(j - 1 >= 0 && filledOutArray[i][j - 1].matches("[^0-9.]")) {
                        System.out.println("Adding: " + input[i][j] + " to " +total);
                        total += Integer.parseInt(filledOutArray[i][j]);

                    }
                    //check for up
                    else if (i - 1 >= 0 && filledOutArray[i - 1][j].matches("[^0-9.]")) {
                        System.out.println("Adding: " + input[i][j] + " to " +total);
                        total  += Integer.parseInt(filledOutArray[i][j]);
                        j += input[i][j].length();
                    }
                    //check for down
                    else if (i + 1 < length && filledOutArray[i + 1][j].matches("[^0-9.]")) {
                        System.out.println("Adding: " + input[i][j] + " to " +total);
                        total  += Integer.parseInt(filledOutArray[i][j]);
                        j += input[i][j].length();
                    }

                    //check for up-left
                    else if(j - 1 >= 0 && i - 1 >= 0 && filledOutArray[i - 1][j - 1].matches("[^0-9.]")) {
                        System.out.println("Adding: " + input[i][j] + " to " +total);
                        total  += Integer.parseInt(filledOutArray[i][j]);
                        j += input[i][j].length();
                    }
                    //check for up-right
                    else if(j + 1 < length && i - 1 >= 0 && filledOutArray[i - 1][j + 1].matches("[^0-9.]")) {
                        System.out.println("Adding: " + input[i][j] + " to " +total);
                        total += Integer.parseInt(filledOutArray[i][j]);
                        j += input[i][j].length();
                    }

                    //check for down left
                    else if(i + 1 < filledOutArray.length && j - 1 >= 0 && filledOutArray[i + 1][j - 1].matches("[^0-9.]"))   {
                        System.out.println("Adding: " + input[i][j] + " to " +total);
                        total += Integer.parseInt(filledOutArray[i][j]);

                    }
                    //check for down right
                    else if(i + 1 < filledOutArray.length && j + 1 < length && filledOutArray[i + 1][j + 1].matches("[^0-9.]")) {
                        System.out.println("Adding: " + input[i][j] + " to " +total);
                        total += Integer.parseInt(filledOutArray[i][j]);
                        j += input[i][j].length();
                    }



                }


            }




        }

        System.out.println("TOTAL IS: " + total);


    }


    private static String[][] getInput(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(filePath));

        String[][] ranksAndFiles = new String[lines.size()][length];


        for (int j = 0; j < lines.size(); j++) {
            for (int k = 0; k < lines.get(j).length(); k++) {

                ranksAndFiles[j][k] = String.valueOf(lines.get(j).charAt(k));


            }


        }


        return ranksAndFiles;
    }

    private static String[][] fillOutArray(String[][] input) {
        String concatNumber;

        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length; j++) {

                //System.out.print(input[i][j]);
                //find out if this location is a number

                if (input[i][j].matches("[0-9]")) {
                    concatNumber = input[i][j];
                    //make sure we dont go past the arraysize
                    if (j + 1 < length) {

                        for (int k = j + 1; k < length; k++) {
                            //checking to see if the location to the right of the found number is also a number
                            if (input[i][k].matches("[0-9]")) {
                                concatNumber = concatNumber + input[i][k];
                            } else {
                                //as soon as we get something that isnt a number, we need to break
                                break;
                            }

                        }

                        int fillerLength = concatNumber.length();

                        for (int k = 0; k < fillerLength; k++) {
                            input[i][j + k] = concatNumber;
                        }


                    }


                }

                System.out.print(input[i][j]);


            }
            System.out.println();


        }
        return input;
    }
}

