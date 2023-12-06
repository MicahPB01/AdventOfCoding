package com.AdventCode.DaySix;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PartOne {


    public static void main(String[] args) throws IOException {

        List<String> input = getInput("src/main/java/com/AdventCode/ActualDaySix/DaySixInput.txt");
        ArrayList<int[]> races = createRaces(input);
        int totalWaysToWin = findWaysToWin(races);

        printResult(totalWaysToWin);






    }


    private static List<String> getInput(String filePath) throws IOException {
        return Files.readAllLines(Path.of(filePath));
    }

    private static void printResult(int total)   {
        System.out.println(total);
    }

    private static ArrayList<int[]> createRaces(List<String> input)   {

        List<Integer> times = findTimes(input.get(0));
        List<Integer> distances = findDistances(input.get(1));
        ArrayList<int[]> races = new ArrayList<>();


        for(int i = 0; i < times.size(); i++)   {
            int[] race = new int[2];

            race[0] = times.get(i);
            race[1] = distances.get(i);
            races.add(race);

        }


        return races;

    }

    private static List<Integer> findTimes(String timeString)   {
        Pattern pattern = Pattern.compile("\\b\\d+\\b");
        Matcher matcher = pattern.matcher(timeString);

        List<Integer> times = new ArrayList<>();

        while(matcher.find())   {
            times.add(Integer.parseInt(matcher.group()));
        }


        return times;
    }

    private static List<Integer> findDistances(String distanceString)   {
        Pattern pattern = Pattern.compile("\\b\\d+\\b");
        Matcher matcher = pattern.matcher(distanceString);
        List<Integer> distances = new ArrayList<>();

        while(matcher.find())   {
            distances.add(Integer.parseInt(matcher.group()));
        }

        return distances;

    }

    private static int findWaysToWin(ArrayList<int[]> races)    {

        ArrayList<Integer> allWaysToWin = new ArrayList<>();


        for(int i = 0; i < races.size(); i++)   {
            int currentWins = 0;

            int driveTime = races.get(i)[0];
            int distanceToBeat = races.get(i)[1];
            int heldButtonTime = 0;

            for(int j = driveTime; j > driveTime / 2; j--)   {
                driveTime = j;


                if(wouldWin(heldButtonTime, distanceToBeat, driveTime))   {
                    System.out.println("Found a win");
                    currentWins++;
                }

                heldButtonTime++;
            }
            System.out.println("Next game");


            allWaysToWin.add(currentWins);

        }

        System.out.println(List.of(allWaysToWin));

        return multiplyTotal(allWaysToWin);



    }

    private static boolean wouldWin(int heldButton, int distanceToBeat, int driveTime)   {
        if(heldButton * driveTime > distanceToBeat)   {
            System.out.println(heldButton + " * " + driveTime + " beats " + driveTime);
            return true;
        }
        else   {
            return false;
        }




        }

    private static int multiplyTotal(ArrayList<Integer> waysToWin) {


        int base = 1;

        for (int i = 0; i < waysToWin.size(); i++) {
            base *= waysToWin.get(i);
        }

        return base;



    }










}
