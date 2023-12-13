package com.AdventCode.DayEight;

import com.AdventCode.DayFour.LotteryCard;
import com.sun.source.tree.Tree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PartOne {


    public static void main(String[] args) throws IOException {




        List<String> input =  readLines("src/main/java/com/AdventCode/DayEight/DayEightInput.txt");
        String instructions = input.get(0);

        HashMap<String, TreeNode> binaryTree = createBinaryTree(input);
        int steps = countStepsToZZZ(instructions, binaryTree);
        System.out.println(steps);









    }

    private static List<String> readLines(String filePath) throws IOException {

        List<String> input = Files.readAllLines(Path.of(filePath));

        return input;
    }

    private static int countStepsToZZZ(String instructions, HashMap<String, TreeNode> nodes) {
        TreeNode current = nodes.get("AAA"); // Start at AAA
        System.out.println(current.label);
        int steps = 0;
        int i = 0; // Instruction index

        while (!current.label.equals("ZZZ")) { // Continue until ZZZ is reached
            System.out.println(current.label);
            if (instructions.charAt(i) == 'L') {
                current = current.left; // Move to left child
            } else if (instructions.charAt(i) == 'R') {
                current = current.right; // Move to right child
            }

            steps++; // Increment step count
            i++;

            if(i == instructions.length())   {
                i = 0;
            }

            // Optional: Check for null to avoid NullPointerException

        }

        System.out.println(steps);
        return steps;
    }


    private static HashMap<String, TreeNode> createBinaryTree(List<String> input)   {

        HashMap<String, TreeNode> tree = new HashMap<>();

        for (int i = 2; i < input.size(); i++) {
            String[] parts = input.get(i).split(" = \\(|, |\\)");
            String label = parts[0];
            String left = parts[1];
            String right = parts[2];

            tree.putIfAbsent(label, new TreeNode(label));
            tree.putIfAbsent(left, new TreeNode(left));
            tree.putIfAbsent(right, new TreeNode(right));

            tree.get(label).left = tree.get(left);
            tree.get(label).right = tree.get(right);
        }

        return tree;


    }



}
