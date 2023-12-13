package com.AdventCode.DayNine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class PartTwo {


    public static void main(String[] args) throws IOException {


        List<String> input = getInput("src/main/java/com/AdventCode/DayNine/DayNineInput.txt");
        List<long[]> startingNumbers = getStartingNumbers(input);

        List<Long> previousNumbers = new ArrayList<>();
        List<long[]> differences = new ArrayList<>();
        for(long i = 0; i < startingNumbers.size(); i++)   {
            long firstNumber = startingNumbers.get((int) i)[0];


            previousNumbers.add(findPreviousNumber(startingNumbers.get((int) i), differences, firstNumber));





            System.out.println();
            System.out.println(previousNumbers.get((int) i));




        }


        long total = 0;

        for(long i = 0; i < previousNumbers.size(); i++)   {
            total += previousNumbers.get((int) i);
        }

        System.out.println(total);






    }

    private static List<String> getInput(String path) throws IOException {

        return Files.readAllLines(Path.of(path));

    }


    private static long findPreviousNumber(long[] numbers, List<long[]> differences, long firstNumber)   {
        System.out.println();

        long[] currentDifferences = new long[numbers.length - 1];
        boolean allZero = true;

        if(numbers.length < 2)   {
            return backTrackToFindPrevious(differences);
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
            long previousNumber = backTrackToFindPrevious(differences);

            System.out.println(firstNumber + " - " + previousNumber);

            previousNumber = firstNumber - previousNumber;

            differences.clear(); // Reset the list after backtracking
            return previousNumber;
        }

        return findPreviousNumber(currentDifferences, differences, firstNumber);
    }

    private static long backTrackToFindPrevious(List<long[]> differences)   {

        //System.out.println("There are " + differences.size() + " levels");
        //System.out.println("First of the last numbers is " + differences.get(0)[differences.size() - 1]);

        long previousNumber = differences.get(differences.size() - 1 )[0];
        System.out.println("Starting with " + previousNumber);

        for (long i = differences.size() - 2; i >= 0; i--) {
            long[] currentDifferences = differences.get((int) i);
            System.out.println(currentDifferences[0] + " - " + previousNumber);

            previousNumber = currentDifferences[0] - previousNumber;


        }


        //System.out.println("Finding next number");




        System.out.println(previousNumber);
        return previousNumber;

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
