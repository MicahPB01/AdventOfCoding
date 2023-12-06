package com.AdventCode.DaySix;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class PartTwo {
    private static final long NUMBER_OF_SEEDS = 4;
    private static final long NUMBER_OF_LEVELS =  7;



    public static void main(String args[]) throws IOException, InterruptedException {




        List<String> input = getInput("src/main/java/com/AdventCode/DaySix/DaySixTestInput.txt");
        List<ArrayList<Long>> seedNumbers = getSeedNumbers(input.get(0));


        List<List<String>> blocks = findNumbersToMap(input);
        removeSeeds(blocks);

        startWithLocation(blocks, seedNumbers);




    }

    private static List<String> getInput(String filePath) throws IOException {

        return Files.readAllLines(Path.of(filePath));

    }

    private static long startWithLocation(List<List<String>> blocks, List<ArrayList<Long>> seeds)   {

        long location = 0;

        while(true)   {


            long seed = locationToSeed(blocks);


            location++;

            break;
        }

        return 0;
    }

    private static long locationToSeed(List<List<String>> blocks)   {

        long currentPlace = 86;
        long seed = 0;
        for(int i = (int) NUMBER_OF_LEVELS - 1; i > 0; i--)   {



            for (int j = 0; j < blocks.get(i).size(); j++)   {

                String[] lineNeedingMapping = blocks.get(i).get(j).split(" ");


                long equivalent = Long.parseLong(lineNeedingMapping[0]);
                long base = Long.parseLong(lineNeedingMapping[1]);
                long length = Long.parseLong(lineNeedingMapping[2]);
                long offset =  (base - equivalent);

                System.out.println("EQ: " + equivalent + " BASE: " + base + " Length: " + length + " Seed: " + currentPlace + " Offset: " + offset);
                if(currentPlace >= equivalent && currentPlace <= equivalent + length)   {
                    System.out.println("Found Link");
                    currentPlace = currentPlace + offset;
                    break;
                }




            }
            System.out.println("Location: " + currentPlace + " has seed " + seed);
            seed = currentPlace;



        }
        System.out.println("Location: " + currentPlace + " has seed " + seed);

        return seed;



    }


    private static List<ArrayList<Long>> getSeedNumbers(String input)   {


        String[] splitTemp = input.split(": ");
        long[] seedNumbers = new long[(int) NUMBER_OF_SEEDS];
        splitTemp = splitTemp[1].split(" ");
        List<ArrayList<Long>> allSeeds = new ArrayList<>();


        //System.out.println(Arrays.toString(splitTemp));


        for(int i = 0; i < NUMBER_OF_SEEDS; i+=2) {

            ArrayList<Long> currentSeeds = expandSeeds(Long.parseLong(splitTemp[i]), Long.parseLong(splitTemp[i+1]));
            allSeeds.add(currentSeeds);



            //System.out.println(Arrays.asList(splitTemp));

        }

        System.out.println(List.of(allSeeds));

        return allSeeds;

    }

    private static ArrayList<Long> expandSeeds(long seedStart, long seedRange)   {
        ArrayList<Long> seeds = new ArrayList<>();
        for(int i = 0; i < seedRange; i++)   {
            seeds.add(seedStart + i);
        }
        return seeds;

    }

    private static long getlongFromSeedArray(String stringTolong)   {
        long seedNumber;
        stringTolong = stringTolong.trim();

        seedNumber = Long.parseLong(stringTolong);

        return seedNumber;

    }



    private static List<List<String>> removeSeeds(List<List<String>> input)   {

        input.remove(0);

        return input;
    }





    private static List<List<String>> findNumbersToMap(List<String> input)   {


        long currentLine = 0;
        long size = input.size();


        List<String> blocksOfNumbers;
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





}
