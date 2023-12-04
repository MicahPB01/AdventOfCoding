package com.AdventCode.DayThree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class PartTwo {

    private static final String[] symbols = {"*", "#", "+", "$"};
    private static final int length = 140;


    public static void main(String args[]) throws IOException {
        String filePath = "src/main/java/com/AdventCode/DayThree/DayThreeInput.txt";


        String[][] input = getInput(filePath);


        mainLogic(input);


    }

    private static void mainLogic(String[][] input) {
        String[][] filledOutArray = fillOutArray(input);
        ArrayList<int[]> buildBlocks = new ArrayList<>();





        /*
        for(int i = 0; i < filledOutArray.length; i++)   {
            for(int j = 0; j < length; j++)   {

                System.out.println("Location: " + i+ "x"+j + " character: " + filledOutArray[i][j] + " USED: " + usedArray[i][j][0]);
            }
        }


         */

        int total = 0;
        ArrayList<Integer> multiplyBlocks = new ArrayList<>();
        for(int i = 0; i < filledOutArray.length; i++)   {
            int[] block = {0,0,0,
                           0,  0,
                           0,0,0};
            for(int j = 0; j < filledOutArray[i].length; j++)   {





                // System.out.println("CHECKING IF " + filledOutArray[i][j] + " is a number");
                if(filledOutArray[i][j].matches("\\*")) {
                    System.out.println("FOUND *");
                    //need to check all directions for other numbers. each number that is found needs to be added to the mulyiply block

                    //top left
                    if(i - 1 >= 0 && j - 1 >= 0 && filledOutArray[i - 1][j - 1].matches("[0-9]+"))   {
                        System.out.println("FOUND TOP LEFT");
                        block[0] = Integer.parseInt(filledOutArray[i-1][j-1]);

                        int loopControl = filledOutArray[i-1][j-1].length();
                        String specialNumber = filledOutArray[i-1][j-1];

                        for(int k = 0 - loopControl; k < loopControl; k++)   {

                            if(j - 1 + k < 0 || j + k > length || i - 1 + k < 0)   {
                                continue;
                            }
                            if(filledOutArray[i-1 ][j - 1 +k].contains(specialNumber))   {
                                System.out.println("setting . ");
                                filledOutArray[i - 1 ][j - 1 + k] = ".";
                            }
                        }


                    }
                    //top
                    if(i - 1 >= 0 && filledOutArray[i - 1][j].matches("[0-9]+"))   {
                        System.out.println("Found top");
                        block[1] = Integer.parseInt(filledOutArray[i-1][j]);
                        int loopControl = filledOutArray[i-1][j].length();
                        String specialNumber = filledOutArray[i-1][j];

                        for(int k = 0 - loopControl; k < loopControl; k++)   {

                            if(i - 1 < 0 || i - 1 + k < 0 || i - 1 + k > length || j + k >= length )   {
                                continue;
                            }
                            if(filledOutArray[i - 1][j+k].contains(specialNumber))   {

                                filledOutArray[i - 1][j+k] = ".";
                            }
                        }

                    }
                    //top right
                    if(i - 1 >= 0 && j + 1 < length && filledOutArray[i - 1][j + 1].matches("[0-9]+"))   {
                        System.out.println("Found top right");
                        block[2] = Integer.parseInt(filledOutArray[i-1][j+1]);

                        int loopControl = filledOutArray[i-1][j+1].length();
                        String specialNumber = filledOutArray[i-1][j+1];

                        for(int k = 0 - loopControl; k < loopControl; k++)   {

                            if(i - 1 < 0 || i - 1 + k > length || j + 1 + k > length || j + 1 + k < 0)   {
                                continue;
                            }
                            if(filledOutArray[i+1][j+ 1+k].contains(specialNumber))   {
                                filledOutArray[i+1][j+1+k] = ".";
                            }
                        }
                    }
                    //left
                    if(j - 1 >= 0 && filledOutArray[i][j - 1].matches("[0-9]+"))   {
                        System.out.println("found left");
                        block[3] = Integer.parseInt(filledOutArray[i][j-1]);

                        int loopControl = filledOutArray[i][j-1].length();
                        String specialNumber = filledOutArray[i][j-1];

                        for(int k = 0 - loopControl; k < 1; k++)   {

                            if(j -1 + k < 0 || j - 1 + k > length)   {
                                continue;
                            }
                            if(filledOutArray[i][j-1+k].contains(specialNumber))   {
                                System.out.println("Found special number " + specialNumber);
                                filledOutArray[i][j-1+k] = ".";
                            }
                        }
                    }
                    //right
                    if(j + 1 < length && filledOutArray[i][j + 1].matches("[0-9]+"))   {
                        System.out.println("found right");
                        block[4] = Integer.parseInt(filledOutArray[i][j+1]);

                        int loopControl = filledOutArray[i][j+1].length();
                        String specialNumber = filledOutArray[i][j+1];

                        for(int k = 0 ; k < loopControl; k++)   {

                            if(j + 1 + k < 0 || j + 1 + k >= length)   {
                                continue;
                            }
                            if(filledOutArray[i][j+k].contains(specialNumber))   {
                                System.out.println("Found special number " + specialNumber);
                                filledOutArray[i][j+1+k] = ".";
                            }
                        }
                    }
                    //bottom left
                    if(i + 1 < filledOutArray.length && j - 1 >= 0 && filledOutArray[i+1][j-1].matches("[0-9]+"))   {
                        System.out.println("found bottom left");
                        block[5] = Integer.parseInt(filledOutArray[i+1][j-1]);
                        int loopControl = filledOutArray[i+1][j-1].length();
                        String specialNumber = filledOutArray[i+1][j-1];
                        System.out.println("Special number is " + specialNumber);

                        for(int k = 0 - loopControl; k < loopControl; k++)   {
                            System.out.println("Check loop");

                            if(i + 1 > filledOutArray.length || j - 1 + k < 0 || j - 1 + k > length)   {
                                continue;
                            }
                            System.out.println(filledOutArray[i+1][j-1+k]);
                            if(filledOutArray[i+1][j-1+k].contains(specialNumber))   {
                                System.out.println("Found special number");
                                filledOutArray[i+1][j-1+k] = ".";
                            }
                        }
                    }
                    //bottom
                    if(i + 1 < filledOutArray.length && filledOutArray[i + 1][j].matches("[0-9]+"))   {
                        System.out.println("found bottom");
                        block[6] = Integer.parseInt(filledOutArray[i+1][j]);
                        int loopControl = filledOutArray[i+1][j].length();
                        String specialNumber = filledOutArray[i+1][j];

                        for(int k = 0 - loopControl; k < loopControl; k++)   {

                            if(i + 1 > filledOutArray.length || j - k < 0 || j - k > length)   {
                                continue;
                            }
                            if(filledOutArray[i+1][j+k].contains(specialNumber))   {
                                filledOutArray[i+1][j+k] = ".";
                            }
                        }
                    }
                    //bottom right
                    if(i + 1 < filledOutArray.length && j + 1 < length && filledOutArray[i+1][j+1].matches("[0-9]+")) {
                        System.out.println("found bottom right");


                        block[7] = Integer.parseInt(filledOutArray[i + 1][j + 1]);
                        int loopControl = filledOutArray[i+1][j+1].length();
                        String specialNumber = filledOutArray[i+1][j+1];

                        for(int k = 0 - loopControl; k < loopControl; k++)   {

                            if(i + 1 > filledOutArray.length || j + 1 + k < 0 || j + 1 + k > length)   {
                                continue;
                            }
                            if(filledOutArray[i+1][j+1+k].contains(specialNumber))   {
                                filledOutArray[i+1][j+1+k] = ".";
                            }
                        }
                    }
                    //

                    System.out.println("After Assignments - i: " + i + ", j: " + j);
                    for (int k = 0; k < block.length; k++) {
                        System.out.println("block[" + k + "]: " + block[k]);
                        buildBlocks.add(block);
                    }

                    block = new int[]{0, 0, 0, 0, 0, 0, 0, 0};


                }


            }






        }
        int tempMultiply = 1;
        int numNumbers = 0;



        for(int i = 0; i < buildBlocks.size(); i +=8)   {
            int firstNumber = 0;
            int secondNumber = 0;
            int numberCount = 0;
            for(int j = 0; j < buildBlocks.get(i).length; j++)   {




                if(numberCount > 2)   {
                    firstNumber = 0;
                    break;
                }



                if(buildBlocks.get(i)[j] != 0)   {

                    if(numberCount == 0)   {
                        firstNumber = buildBlocks.get(i)[j];
                    }
                    if(numberCount == 1)   {
                        secondNumber = buildBlocks.get(i)[j];
                    }

                    numberCount++;
                }



            }
            tempMultiply = firstNumber * secondNumber;



            System.out.println("Total from block " + i + " is " + tempMultiply);
            total += tempMultiply;


        }







        System.out.println("TOTAL IS: " + total);


    }


    private static String[][] getInput(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(filePath));

        String[][] ranksAndFiles = new String[lines.size()][length];


        for (int j = 0; j < lines.size(); j++) {
            for (int k = 0; k < lines.get(j).length(); k++) {

                ranksAndFiles[j][k] = String.valueOf(lines.get(j).charAt(k));


            }


        }


        return ranksAndFiles;
    }

    private static String[][] fillOutArray(String[][] input) {
        String concatNumber;

        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length; j++) {

                //System.out.print(input[i][j]);
                //find out if this location is a number

                if (input[i][j].matches("[0-9]")) {
                    concatNumber = input[i][j];
                    //make sure we dont go past the arraysize
                    if (j + 1 < length) {

                        for (int k = j + 1; k < length; k++) {
                            //checking to see if the location to the right of the found number is also a number
                            if (input[i][k].matches("[0-9]")) {
                                concatNumber = concatNumber + input[i][k];
                            } else {
                                //as soon as we get something that isnt a number, we need to break
                                break;
                            }

                        }

                        int fillerLength = concatNumber.length();

                        for (int k = 0; k < fillerLength; k++) {
                            input[i][j + k] = concatNumber;
                        }


                    }


                }

                System.out.print(input[i][j]);


            }
            System.out.println();


        }
        return input;
    }
}

