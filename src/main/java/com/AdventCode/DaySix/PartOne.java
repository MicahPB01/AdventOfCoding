package com.AdventCode.DaySix;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class PartOne {
    private static final int NUMBER_OF_SEEDS = 10;
    private static final int SIZE_OF_MAP = 3;
    private static final int NUMBER_OF_LEVELS =  7;
    private static final int SEEDS_TO_SOIL = 0;
    private static final int SOIL_TO_FERTILIZER = 1;
    private static final int FERTILIZER_TO_WATER = 2;
    private static final int WATER_TO_LIGHT = 3;
    private static final int LIGHT_TO_TEMPERATURE = 4;
    private static final int TEMPERATURE_TO_HUMIDITY = 5;
    private static final int HUMIDITY_TO_LOCATION = 6;



    public static void main(String args[]) throws IOException {


        List<String> input = getInput("src/main/java/com/AdventCode/DaySix/DaySixInput.txt");
        int[] seedNumbers = getSeedNumbers(input.get(0));
        List<List<String>> blocks = findNumbersToMap(input);
        blocks = removeSeeds(blocks);
        List<HashMap<Integer, Integer>> maps = createMapHead(blocks);
        int closestSeed = findClosestSeed(seedNumbers, maps);










    }

    private static List<String> getInput(String filePath) throws IOException {

        return Files.readAllLines(Path.of(filePath));

    }

    private static int[] getSeedNumbers(String input)   {


        String[] splitTemp = input.split(": ");
        int[] seedNumbers = new int[NUMBER_OF_SEEDS];
        System.out.println(splitTemp[1]);
        splitTemp = splitTemp[1].split(" ");

        for(int i = 0; i < splitTemp.length; i++)   {

            seedNumbers[i] = getIntFromSeedArray(splitTemp[i]);


        }

        return seedNumbers;

    }

    private static int getIntFromSeedArray(String stringToInt)   {
        int seedNumber;
        stringToInt = stringToInt.trim();

        seedNumber = Integer.parseInt(stringToInt);

        return seedNumber;

    }

    private static List<HashMap<Integer, Integer>> createMapHead(List<List<String>> numbersToMap)   {
        List<HashMap<Integer, Integer>> maps = new ArrayList<>();

        for(int i = 0; i < NUMBER_OF_LEVELS; i++)   {

            HashMap<Integer, Integer> map = createMapFoot(numbersToMap.get(i));
            maps.add(map);

            System.out.println(maps.get(i));

        }

        return maps;

    }

    private static HashMap<Integer, Integer> createMapFoot(List<String> numbersToMap)   {
        int length;
        int base;
        int equivalent;


        HashMap<Integer, Integer> map = new HashMap<>();
        String[] lineNeedingMapping;

        for(int i = 0; i < numbersToMap.size(); i++)   {



            lineNeedingMapping = numbersToMap.get(i).split(" ");
            equivalent = Integer.parseInt(lineNeedingMapping[0]);
            base = Integer.parseInt(lineNeedingMapping[1]);
            length = Integer.parseInt(lineNeedingMapping[2]);

            for(int j = 0; j < length; j++) {
                map.put(base + j, equivalent + j);

            }
            //System.out.println("Completed line");




        }
        //System.out.println(map);




        return map;
    }

    private static List<List<String>> removeSeeds(List<List<String>> input)   {

        input.remove(0);

        return input;
    }





    private static List<List<String>> findNumbersToMap(List<String> input)   {


        int currentLine = 0;
        int size = input.size();

        System.out.println(size);

        List<String> blocksOfNumbers = new ArrayList<>();
        List<List<String>> allBlocks = new ArrayList<>();

        for(int i = 0; i < NUMBER_OF_LEVELS + 1 ; i++) {
            blocksOfNumbers = new ArrayList<>();

            while (input.get(currentLine) != null  ) {

                if(input.get(currentLine).equals(""))   {
                    currentLine += 2;
                    break;
                }

                blocksOfNumbers.add(input.get(currentLine));
                //System.out.println("Adding line " + currentLine);

                currentLine++;

                if(currentLine == input.size())   {
                    break;
                }

            }
            //System.out.println("Creating next box");

            for(int j = 0; j < blocksOfNumbers.size(); j++)   {
                System.out.println(blocksOfNumbers.get(j));
            }
            System.out.println();

            allBlocks.add(blocksOfNumbers);

        }

        //System.out.println("Finished created boxes");

        //debugPrintBoxes(allBlocks);

        return allBlocks;

    }

    private static int findClosestSeed(int[] seeds, List<HashMap<Integer, Integer>> maps)   {

        int currentNumber;
        int newNumber;
        int oldNumber;

        for(int i= 0; i < seeds.length; i++)   {

            currentNumber = seeds[i];
           //System.out.println("STARTING: " + currentNumber);
           //System.out.println();



            for(int j = 0; j < NUMBER_OF_LEVELS  ; j++)   {

                //System.out.println("LOOKING FOR: " + currentNumber);
               //System.out.println("LOOKING AT THIS MAP: " + maps.get(j));

                //System.out.println("KEY AT CURRENT NUMBER: " + maps.get(j).get(currentNumber));


                if(maps.get(j).containsKey(currentNumber))   {
                    //System.out.println("FOUND RELATIONSHIP");
                    currentNumber = maps.get(j).get(currentNumber);
                }
                else   {
                    //System.out.println("NOT PRESENT");

                }





            }

            System.out.println("SEED: " + i + " is at location " + currentNumber);


        }


        return 0;
    }


    private static void debugPrintBoxes(List<List<String>> boxes)   {
        for(int i = 0; i< NUMBER_OF_LEVELS; i++)   {
            for(int j = 0; j < boxes.get(i).size(); j++)   {
                System.out.println("Box: " + i + " " + boxes.get(i).get(j));
            }
            System.out.println();
        }

    }



}
