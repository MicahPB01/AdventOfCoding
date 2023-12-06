package com.AdventCode.DaySix;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class PartOne {
    private static final long NUMBER_OF_SEEDS = 25;
    private static final long NUMBER_OF_LEVELS =  7;



    public static void main(String args[]) throws IOException, InterruptedException {




        List<String> input = getInput("src/main/java/com/AdventCode/DaySix/DaySixInput.txt");
        long[] seedNumbers = getSeedNumbers(input.get(0));


        List<List<String>> blocks = findNumbersToMap(input);
        removeSeeds(blocks);


        Long finalResult = findResult(blocks, seedNumbers);
        printResult(finalResult);


    }

    private static List<String> getInput(String filePath) throws IOException {

        return Files.readAllLines(Path.of(filePath));

    }
    private static void printResult(long result)   {
        System.out.println(result);
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

    private static long findResult(List<List<String>> numbersToMap, long[] seeds) throws IOException, InterruptedException {
        List<HashMap<Long, Long>> maps = new ArrayList<>();
        long lowestLocation = 100000000000000000L;


        for(int j = 0; j < seeds.length; j++) {
           long seed = seeds[j];
           Thread.sleep(800);


            for (long i = 0; i < NUMBER_OF_LEVELS; i++) {

                seed = runMapTree(numbersToMap.get((int) i), (int) i, seed);
                //System.out.println("MAP NUMBER: " + i);
                //System.out.println("CURRENT MAP: " + map);
                //System.out.println("Looking for seed: " + seed);


                //seed = findClosestSeed(seed, map);


                // maps.add(map);


            }
            if(seed < lowestLocation)   {
                lowestLocation = seed;
            }
           // System.out.println("Seed is at:" + seed);

        }

        //System.out.println(lowestLocation);

        return lowestLocation;

    }

    private static long runMapTree(List<String> numbersToMap, int mapNumber, long seed) throws IOException {
        long length;
        long base;
        long equivalent;
        long offset;



        String[] lineNeedingMapping;


       // System.out.println("NEW SEED: " + seed);
        for(long i = 0; i < numbersToMap.size(); i++)   {



            lineNeedingMapping = numbersToMap.get((int) i).split(" ");
            equivalent = Long.parseLong(lineNeedingMapping[0]);
            base = Long.parseLong(lineNeedingMapping[1]);
            length = Long.parseLong(lineNeedingMapping[2]);
            offset =  (equivalent - base);
            //System.out.println("offset: " + offset);
           // System.out.println(equivalent + " " + base + " " + length);

            //System.out.println("Low: " + equivalent + " SEED: " + seed + " High: " + (base + length));

            if(seed <= base + length && seed >= base)   {
                //System.out.println("Found new seed");
                seed = seed + offset;
                return seed;

            }


        }

        //System.out.println("did not find, returning as is");

        return seed;


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


    private static void debugPrintBoxes(List<List<String>> boxes)   {
        for(long i = 0; i< NUMBER_OF_LEVELS; i++)   {
            for(long j = 0; j < boxes.get((int) i).size(); j++)   {
                System.out.println("Box: " + i + " " + boxes.get((int) i).get((int) j));
            }
            System.out.println();
        }

    }



}
