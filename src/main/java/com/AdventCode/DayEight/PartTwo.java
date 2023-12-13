package com.AdventCode.DayEight;

import com.AdventCode.DayFour.LotteryCard;
import com.sun.source.tree.Tree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PartTwo {


    public static void main(String[] args) throws IOException {




        List<String> input =  readLines("src/main/java/com/AdventCode/DayEight/DayEightInput.txt");
        String instructions = input.get(0);

        HashMap<String, TreeNode> binaryTree = createBinaryTree(input);
        long[] steps = countStepsToZZZ(instructions, binaryTree);
        long LCM = findLCM(steps);
        System.out.println(LCM);




    }

    private static List<String> readLines(String filePath) throws IOException {

        List<String> input = Files.readAllLines(Path.of(filePath));

        return input;
    }


    private static long findLCM(long[] values) {
        long lcm = 1;
        for (long value : values) {
            lcm = lcm(lcm, value);
           // System.out.println(lcm);
        }

        long total = lcm;

        //System.out.println(total);
        return total;
    }

    private static long lcm(long a, long b) {
        if (a == 0 || b == 0) {
            return 0;
        }
        long gcdValue = gcd(a, b);
        //System.out.println((a / gcdValue) * b);
        return (a / gcdValue) * b;
    }

    private static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }






    private static long[] countStepsToZZZ(String instructions, HashMap<String, TreeNode> tree) {
        List<TreeNode> currentNodes = new ArrayList<>();
        Map<String, TreeNode> cache = new HashMap<>(); // Cache for memoization

        for(TreeNode node : tree.values()) {
            if(node.label.endsWith("A")) {
                currentNodes.add(node);
            }
        }

        System.out.println("There are " + currentNodes.size() + " nodes.");

        long[] steps = new long[currentNodes.size()];
        int loopControl = 0;
        boolean isZ;

        for(int i = 0; i < currentNodes.size(); i++)   {
            TreeNode currentNode = currentNodes.get(i);
            long currentSteps = 0;
            loopControl = 0;


            while(!currentNode.label.endsWith("Z"))   {

                if(instructions.charAt(loopControl) == 'L')   {
                    currentNode = currentNode.left;
                }
                else if(instructions.charAt(loopControl) == 'R')   {
                    currentNode = currentNode.right;
                }

                currentSteps++;
                loopControl++;

                if(loopControl == instructions.length())   {
                    loopControl = 0;
                }
                //System.out.println(currentNode.label);


            }
            //System.out.println("Found the end of " + i);
            steps[i] = currentSteps;
            System.out.println("Node: " + (i+1) + " takes " + currentSteps + " steps.");




        }





        return steps;
    }




    private static HashMap<String, TreeNode> createBinaryTree(List<String> input)   {

        HashMap<String, TreeNode> tree = new HashMap<>();

        for(int i = 2; i < input.size(); i++)   {
            String[] nodesAndChildren = input.get(i).split(" = \\(|, |\\)");

            String top = nodesAndChildren[0];
            String left = nodesAndChildren[1];
            String right = nodesAndChildren[2];

            tree.putIfAbsent(top, new TreeNode(top));
            tree.putIfAbsent(left, new TreeNode(left));
            tree.putIfAbsent(right, new TreeNode(right));

            tree.get(top).left = tree.get(left);
            tree.get(top).right = tree.get(right);




        }


        return tree;


    }



}
