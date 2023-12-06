package com.AdventCode.ActualDaySix;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class PartTwo {


    public static void main(String[] args) throws IOException {

        List<String> input = getInput("src/main/java/com/AdventCode/ActualDaySix/DaySixInput.txt");
        long[] race = createRace(input);
        long totalWaysToWin = findWaysToWin(race);

        printResult(totalWaysToWin);






    }


    private static List<String> getInput(String filePath) throws IOException {
        return Files.readAllLines(Path.of(filePath));
    }

    private static void printResult(long total)   {
        System.out.println(total);
    }

    private static long[] createRace(List<String> input)   {

        long time = findRaceInfo(input.get(0));
        long distance = findRaceInfo(input.get(1));

        long[] race = {time, distance};

        return race;

    }

    private static long findRaceInfo(String inputString)   {

        String tempString = inputString.replaceAll(" ","");
        String[] info = tempString.split(":");

        return Long.parseLong(info[1]);




    }



    private static long findWaysToWin(long[] race)    {

        ArrayList<Long> allWaysToWin = new ArrayList<>();

        long currentWins = 0;
        long driveTime = race[0];
        long distanceToBeat = race[1];
        //System.out.println("Distance to beat: " + distanceToBeat);
        int heldButtonTime = 0;

        for(long i = driveTime; i > driveTime / 2; i--)   {
            driveTime = i;

            if(wouldWin(heldButtonTime, distanceToBeat, driveTime))   {
                currentWins++;
            }

            heldButtonTime++;
        }

        allWaysToWin.add(currentWins);


        return multiplyTotal(allWaysToWin);



    }

    private static boolean wouldWin(long heldButton, long distanceToBeat, long driveTime)   {
        if(heldButton * driveTime > distanceToBeat)   {
            //System.out.println(heldButton + " * " + driveTime + " beats " + distanceToBeat);
            return true;
        }
        else   {
            return false;
        }

    }

    private static int multiplyTotal(ArrayList<Long> waysToWin) {


        int base = 1;

        for (int i = 0; i < waysToWin.size(); i++) {
            base *= waysToWin.get(i);
        }

        return base;



    }










}
