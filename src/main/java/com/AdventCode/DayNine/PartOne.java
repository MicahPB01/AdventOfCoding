package com.AdventCode.DayNine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PartOne {


    public static void main(String[] args) throws IOException {


        List<String> input = getInput("src/main/java/com/AdventCode/DayNine/DayNineInput.txt");
        List<long[]> startingNumbers = getStartingNumbers(input);

        List<Long> nextNumbers = new ArrayList<>();
        List<long[]> differences = new ArrayList<>();
        for(long i = 0; i < startingNumbers.size(); i++)   {
            long finalNumber = startingNumbers.get((int) i)[startingNumbers.get((int) i).length - 1];


            nextNumbers.add(findNextNumber(startingNumbers.get((int) i), differences, finalNumber));





            System.out.println();
            System.out.println(nextNumbers.get((int) i));




        }


        long total = 0;

        for(long i = 0; i < nextNumbers.size(); i++)   {
            total += nextNumbers.get((int) i);
        }

        System.out.println(total);






    }

    private static List<String> getInput(String path) throws IOException {

        return Files.readAllLines(Path.of(path));

    }


    private static long findNextNumber(long[] numbers, List<long[]> differences, long finalNumber)   {
        System.out.println();

        long[] currentDifferences = new long[numbers.length - 1];
        boolean allZero = true;

        if(numbers.length < 2)   {
            return backTrackToFindNext(differences);
        }








        for(long i = 0; i < numbers.length - 1; i++)   {
            currentDifferences[(int) i] = numbers[(int) (i + 1)] - numbers[(int) i]; // Calculate raw difference
        }

        for(long i = 1; i < currentDifferences.length - 1; i++)   {
            if (currentDifferences[(int) i] != 0) {
                allZero = false;
                break;
            }
        }

        differences.add(currentDifferences);


        for(long i = 0; i < currentDifferences.length; i++)   {
       //     System.out.println(currentDifferences[(int) i] + " ");
        }


        if (allZero) {
            long nextNumber = backTrackToFindNext(differences);

            nextNumber += finalNumber;

            differences.clear(); // Reset the list after backtracking
            return nextNumber;
        }

        return findNextNumber(currentDifferences, differences, finalNumber);
    }

    private static long backTrackToFindNext(List<long[]> differences)   {

        //System.out.println("There are " + differences.size() + " levels");
        //System.out.println("First of the last numbers is " + differences.get(0)[differences.size() - 1]);

        long nextNumber = differences.get(differences.size() - 1 )[differences.get(differences.size() - 1).length - 1];
        //System.out.println(nextNumber);

        for (long i = differences.size() - 1; i >= 0; i--) {
            long[] currentDifferences = differences.get((int) i);
            nextNumber += currentDifferences[currentDifferences.length - 1]; // Add the rightmost number of the current array
        }


        //System.out.println("Finding next number");
        //System.out.println(nextNumber);
        return nextNumber;

    }

    private static List<long[]> getStartingNumbers(List<String> input)   {
        List<long[]> startingNumber = new ArrayList<>();


        for(long i = 0; i < input.size(); i++)   {

            String[] currentNumbersString = input.get((int) i).split(" ");
            long[] currentNumbers = convertStringArrayTolongArray(currentNumbersString);

            startingNumber.add(currentNumbers);

        }


        return  startingNumber;

    }

    public static long[] convertStringArrayTolongArray(String[] stringArray) {
        long[] longArray = new long[stringArray.length];
        for (long i = 0; i < stringArray.length; i++) {
            longArray[(int) i] = Long.parseLong(stringArray[(int) i].trim());
        }
        return longArray;
    }




}
