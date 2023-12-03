package com.AdventCode.DayTwo;

import com.AdventCode.DayTwo.Models.Game;
import com.AdventCode.DayTwo.Models.Pull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class PartOne {

    private static int MAX_REDS = 12;
    private static int MAX_GREENS = 13;
    private static int MAX_BLUES = 14;


    public static void main(String args[]) throws IOException {
        String filePath ="src/main/java/com/AdventCode/DayTwo/DayTwoInput.txt";

        List<String> input = getInput(filePath);
        allGames(input);

    }



    private static List<String> getInput(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(filePath));
        return lines;
    }



    private static List<Game> allGames(List<String> linesOfInput)   {
        int totalOfValidIDs = 0;

        List<Game> games = new ArrayList<>();
        List<Game> invalidGames = new ArrayList<>();
        List<Game> validGames = new ArrayList<>();

        for(int i = 0; i < linesOfInput.size(); i++) {
            games.add(parseGame(linesOfInput.get(i)));
        }

        for(int i = 0; i < games.size(); i++)   {
            if(!isValid(games.get(i)))   {
                games.get(i).setValid(false);
                invalidGames.add(games.get(i));
                System.out.println("Games Number " + games.get(i).getGameID() + " is invalid!");
            }
            else   {
                validGames.add(games.get(i));
            }
        }

        for(int i = 0; i < validGames.size(); i++)   {
            totalOfValidIDs += validGames.get(i).getGameID();
        }


        System.out.println(totalOfValidIDs);
        return null;
    }

    private static boolean isValid(Game game)   {

        if(game.getLargestRed() > MAX_REDS)   {
            return false;
        }

        if(game.getLargestGreen() > MAX_GREENS)   {
            return false;
        }

        if(game.getLargestBlue() > MAX_BLUES)   {
            return false;
        }

        return true;
    }

    private static Game parseGame(String input)   {

        int largestRed = 0;
        int largestGreen = 0;
        int largestBlue = 0;
        int gameID;
        String cleanColon;

        String[] colonSplit = input.split(":");
        String[] semiColonSplit = colonSplit[1].split(";");

        cleanColon = colonSplit[0].replaceAll("[^0-9]", "");

        gameID = Integer.parseInt(cleanColon);
        List<Pull> pullList = new ArrayList<>();


        for(int i = 0; i < semiColonSplit.length; i++)   {
            int currentRed = 0;
            int currentGreen = 0;
            int currentBlue = 0;

            String[] commaSplit = semiColonSplit[i].split(",");

            for(int j = 0; j < commaSplit.length; j++)   {


                String[] spaceSplit = commaSplit[j].split(" ");
                //spaceSplit[1] is the number which is then followed by the color in spaceSplit[2]
                System.out.println(spaceSplit[1] + " " + spaceSplit[2]);

                if(spaceSplit[2].contains("red"))   {
                    currentRed = Integer.parseInt(spaceSplit[1]);
                }
                if(spaceSplit[2].contains("green"))   {
                    currentGreen = Integer.parseInt(spaceSplit[1]);
                }
                if(spaceSplit[2].contains("blue"))   {
                    currentBlue = Integer.parseInt(spaceSplit[1]);
                }





            }

            Pull currentPull = new Pull(currentRed, currentGreen, currentBlue);
            pullList.add(currentPull);

            if(currentRed > largestRed)   {
                largestRed = currentRed;
            }
            if(currentGreen > largestGreen)   {
                largestGreen = currentGreen;
            }
            if(currentBlue > largestBlue)   {
                largestBlue = currentBlue;
            }

            System.out.println("Created new Pull: " + currentRed + " " + currentGreen + " " + currentBlue);
            System.out.println("NEXT PULL");
        }


        Game newGame = new Game();
        newGame.setGameID(gameID);
        newGame.setLargestRed(largestRed);
        newGame.setLargestGreen(largestGreen);
        newGame.setLargestBlue(largestBlue);
        newGame.setPulls(pullList);


        return newGame;

    }

    private static int parseIntOrZero(String check)   {
        System.out.println("Checking: " + check);

        if(check != null)   {
            return Integer.parseInt(check);
        }
        else   {
            return 0;
        }

    }









}
