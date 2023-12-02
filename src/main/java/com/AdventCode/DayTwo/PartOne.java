package com.AdventCode.DayTwo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PartOne {

    private static int reds = 12;
    private static int greens = 13;
    private static int blues = 14;


    public static void main(String args[]) throws IOException {
        String filePath ="C:\\Users\\micah\\IdeaProjects\\AdventOfCoding\\src\\main\\java\\com\\AdventCode\\DayTwo\\DayTwoInput.txt";

        List<String> input = getInput(filePath);


    }



    private static List<String> getInput(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(filePath));
        return lines;
    }


    private static boolean validateColors()   {


        return false;
    }

    private static int[] findCubesPerGame(String game)   {
return null;
    }

    private static List<Game> buildListOfGames



    private Game createGame(String input)   {
        int largestRed = 0;
        int largestGreen = 0;
        int largestBlue = 0;
        int currentRed;
        int currentGreen;
        int currentBlue;
        int gameID = 0;
        char offsetChar;
        List<Game> listOfGames = new ArrayList<Game>();
        String[] gameIDSplit = input.split(":");

        gameIDSplit[0] = gameIDSplit[0].replaceAll("[^0-9]", "");

        gameID = Integer.parseInt(gameIDSplit[0]);

        String[] setSplit = gameIDSplit[1].split(";");

        for(int i = 0; i < setSplit.length; i++)   {
            String[] colorSplit;

            if(setSplit[i].contains("red"))   {

                colorSplit = setSplit[i].split("red");

                offsetChar = colorSplit[0].charAt(colorSplit[0].length() - 2);

                currentRed = Character.getNumericValue(offsetChar);
                
                if(currentRed > largestRed)   {
                    largestRed = currentRed;
                }

            }

            if(setSplit[i].contains("green"))   {

                colorSplit = setSplit[i].split("green");

                offsetChar = colorSplit[0].charAt(colorSplit[0].length() - 2);

                currentGreen = Character.getNumericValue(offsetChar);

                if(currentGreen > largestGreen)   {
                    largestGreen = currentGreen;
                }

                if(setSplit[i].contains("blue"))   {

                    colorSplit = setSplit[i].split("blue");

                    offsetChar = colorSplit[0].charAt(colorSplit[0].length() - 2);

                    currentBlue = Character.getNumericValue(offsetChar);

                    if(currentBlue > largestBlue)   {
                        largestBlue = currentBlue;
                    }

                }



            }

           return  new Game(largestRed,largestGreen,largestBlue,gameID);


        }















    }



}
