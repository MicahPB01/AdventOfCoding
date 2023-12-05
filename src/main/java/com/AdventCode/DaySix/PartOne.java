package com.AdventCode.DaySix;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class PartOne {
    private static final long NUMBER_OF_SEEDS = 5;
    private static final long SIZE_OF_MAP = 3;
    private static final long NUMBER_OF_LEVELS =  7;
    private static final long SEEDS_TO_SOIL = 0;
    private static final long SOIL_TO_FERTILIZER = 1;
    private static final long FERTILIZER_TO_WATER = 2;
    private static final long WATER_TO_LIGHT = 3;
    private static final long LIGHT_TO_TEMPERATURE = 4;
    private static final long TEMPERATURE_TO_HUMIDITY = 5;
    private static final long HUMIDITY_TO_LOCATION = 6;



    public static void main(String args[]) throws IOException {




        List<String> input = getInput("src/main/java/com/AdventCode/DaySix/DaySixTestInput.txt");
        long[] seedNumbers = getSeedNumbers(input.get(0));
        List<List<String>> blocks = findNumbersToMap(input);
        blocks = removeSeeds(blocks);
        List<HashMap<Long, Long>> maps = createMapHead(blocks, seedNumbers);
        //long closestSeed = findClosestSeed(seedNumbers, maps);










    }

    private static List<String> getInput(String filePath) throws IOException {

        return Files.readAllLines(Path.of(filePath));

    }

    private static long[] getSeedNumbers(String input)   {


        String[] splitTemp = input.split(": ");
        long[] seedNumbers = new long[(int) NUMBER_OF_SEEDS];
        splitTemp = splitTemp[1].split(" ");

        for(long i = 0; i < splitTemp.length; i++)   {

            seedNumbers[(int) i] = getlongFromSeedArray(splitTemp[(int) i]);


        }

        return seedNumbers;

    }

    private static long getlongFromSeedArray(String stringTolong)   {
        long seedNumber;
        stringTolong = stringTolong.trim();

        seedNumber = Long.parseLong(stringTolong);

        return seedNumber;

    }

    private static List<HashMap<Long, Long>> createMapHead(List<List<String>> numbersToMap, long[] seeds) throws IOException {
        List<HashMap<Long, Long>> maps = new ArrayList<>();


        long seed = seeds[0];

            for (long i = 0; i < NUMBER_OF_LEVELS; i++) {

                createMapFoot(numbersToMap.get((int) i), (int) i, 1);
                //System.out.println("MAP NUMBER: " + i);
                //System.out.println("CURRENT MAP: " + map);
                //System.out.println("Looking for seed: " + seed);


                //seed = findClosestSeed(seed, map);


                // maps.add(map);


            }
            System.out.println("Seed is at:" + seed);

        return maps;

    }

    private static void createMapFoot(List<String> numbersToMap, int mapNumber, long seed) throws IOException {
        long length;
        long base;
        long equivalent;
        seed = 79;


        HashMap<Long, Long> map = new HashMap<>();
        String[] lineNeedingMapping;


        for(long i = 0; i < numbersToMap.size(); i++)   {

            System.out.println("NEW SEED: " + seed);

            lineNeedingMapping = numbersToMap.get((int) i).split(" ");
            equivalent = Long.parseLong(lineNeedingMapping[0]);
            base = Long.parseLong(lineNeedingMapping[1]);
            length = Long.parseLong(lineNeedingMapping[2]);

            System.out.println(equivalent + " " + base + " " + length);

            if(seed >= equivalent && seed <= base)   {

                seed = seed + length;
                System.out.println("Found relationshpi");
                break;
            }






        }
        //System.out.prlongln(map);




    }

    private static List<List<String>> removeSeeds(List<List<String>> input)   {

        input.remove(0);

        return input;
    }





    private static List<List<String>> findNumbersToMap(List<String> input)   {


        long currentLine = 0;
        long size = input.size();


        List<String> blocksOfNumbers = new ArrayList<>();
        List<List<String>> allBlocks = new ArrayList<>();

        for(long i = 0; i < NUMBER_OF_LEVELS + 1 ; i++) {
            blocksOfNumbers = new ArrayList<>();

            while (input.get((int) currentLine) != null  ) {

                if(input.get((int) currentLine).equals(""))   {
                    currentLine += 2;
                    break;
                }

                blocksOfNumbers.add(input.get((int) currentLine));
                //System.out.prlongln("Adding line " + currentLine);

                currentLine++;

                if(currentLine == input.size())   {
                    break;
                }

            }
            //System.out.prlongln("Creating next box");


            allBlocks.add(blocksOfNumbers);

        }

        //System.out.prlongln("Finished created boxes");

        //debugPrlongBoxes(allBlocks);

        return allBlocks;

    }

    private static long findClosestSeed(long seed, HashMap<Long, Long> map)   {

        long currentNumber;
        long newNumber;
        long oldNumber;



            currentNumber = seed;
           //System.out.prlongln("STARTING: " + currentNumber);
           //System.out.prlongln();



                //System.out.prlongln("LOOKING FOR: " + currentNumber);
               //System.out.prlongln("LOOKING AT THIS MAP: " + maps.get(j));

                //System.out.prlongln("KEY AT CURRENT NUMBER: " + maps.get(j).get(currentNumber));


                if(map.containsKey(currentNumber))   {
                  //  System.out.println("FOUND RELATIONSHIP");
                    currentNumber = map.get(currentNumber);
                }
                else   {
                   // System.out.println("NOT PRESENT");

                }




            //System.out.println("SEED: is at location " + currentNumber);






        return currentNumber;
    }


    private static void debugPrintBoxes(List<List<String>> boxes)   {
        for(long i = 0; i< NUMBER_OF_LEVELS; i++)   {
            for(long j = 0; j < boxes.get((int) i).size(); j++)   {
                System.out.println("Box: " + i + " " + boxes.get((int) i).get((int) j));
            }
            System.out.println();
        }

    }



}
