package com.AdventCode.DaySeven;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


public class PartOne {


    public static void main(String[] args)   {


        List<String> input = getInput("src/main/java/com/AdventCode/DaySeven/DaySevenInput.txt");

        int result = findResult(input);

        printResults(result);




    }

    private static List<String> getInput(String filePath)   {

        try {
            return Files.readAllLines(Path.of(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private static void printResults(int result)   {
        System.out.println(result);
    }



    private static int findResult(List<String> input)   {

        ArrayList<Hand> hands = new ArrayList<>();

        for(int i = 0; i < input.size(); i++)   {
            Hand hand = createHand(input.get(i));
            hands.add(hand);
        }

        for(int i = 0; i < hands.size(); i++)   {
            System.out.println("Next Hand");
            determineHand(hands.get(i));
        }


        // Sorting based on the int value
        // Sorting based on handStrength and then comparing the cards
        Collections.sort(hands, Comparator
                .<Hand, Integer>comparing(Hand::getHandStrength)
                .thenComparing((hand1, hand2) -> compareCards(hand1.getCards(), hand2.getCards())));

        // Displaying the sorted list using traditional for loop
        for (int i = 0; i < hands.size(); i++) {
            Hand hand = hands.get(i);
            System.out.println("Hand " + i + ": " + hand.getCards());
        }

        int total = 0;
        for(int i = 0; i < hands.size(); i++)   {
            total += (i+1) * hands.get(i).getBetValue();
            System.out.println(i + " " + hands.get(i).getBetValue());
        }


        System.out.println(total);




return 0;
    }

    private static int compareCards(String cards1, String cards2) {
        // Implement your logic to compare cards here
        // For simplicity, assuming cards are represented as space-separated values

        String[] cardsArray1 = cards1.split(" ");
        String[] cardsArray2 = cards2.split(" ");

        // Compare each card one by one
        for (int i = 0; i < cardsArray1.length; i++) {
            int result = compareCard(cardsArray1[i], cardsArray2[i]);
            if (result != 0) {
                return result;
            }
        }

        return 0; // Both hands are equal
    }


    private static int compareCard(String card1, String card2) {
        // Define the custom order for card values
        String order = "23456789TJQKA";

        // Compare based on the custom order
        for (int i = card1.length() - 1; i >= 0; i--) {
            char char1 = card1.charAt(i);
            char char2 = card2.charAt(i);

            int index1 = order.indexOf(char1);
            int index2 = order.indexOf(char2);

            if (index1 < index2) {
                return -1; // card1 is higher
            } else if (index1 > index2) {
                return 1; // card2 is higher
            }
        }

        // If all characters checked so far are equal, consider the higher hand
        return 0;
    }








    private static Hand createHand(String handInfo)   {

        String[] givenInfo = handInfo.split(" ");


        return new Hand(givenInfo[0], Integer.parseInt(givenInfo[1]));



    }

    private static Hand determineHand(Hand hand)   {
        ArrayList<String[]> cards = new ArrayList<>();
        int loopControl = 0;

        String currentCards = hand.getCards();

        while(true)   {
            char card = currentCards.charAt(loopControl);

            String[] cardWithCount = new String[2];

            cardWithCount[0] = String.valueOf(card);

            int count = 0;
            for(int i = 0; i < currentCards.length(); i++)   {
                if (card == currentCards.charAt(i)) {
                    count++;
                }

            }

            //System.out.println("Set:" + currentCards);

            currentCards = currentCards.replaceAll(String.valueOf(card), "");

            //System.out.println("After: " + currentCards);

            cardWithCount[1] = String.valueOf(count);





            cards.add(cardWithCount);


            //System.out.println("Card: " + cardWithCount[0] + " Count: " + cardWithCount[1]);

            if(count > 0)   {
                loopControl--;
            }

            if(currentCards.length() == 0 )   {
                break;
            }


            loopControl++;

        }


        for(int i = 0; i < cards.size(); i++)   {
            System.out.println("CARD: " + cards.get(i)[0]);
        }




        int strength = determineStrengthOfHand(cards);
        System.out.println("Sterngth: " + strength);
        hand.setHandStrength(strength);





        return hand;

    }

    private static int determineStrengthOfHand(ArrayList<String[]> cards)   {

        System.out.println("there are " + cards.size() + " cards");

        int strength = 1;

        for(int  i = 0; i < cards.size(); i++)   {
            int currentCardCount = Integer.parseInt(cards.get(i)[1]);

            if(currentCardCount == 5)   {
                strength = 7;
                System.out.println("FIVE");
                return  strength;
            }

            if(currentCardCount == 4)   {
                strength = 6;
                System.out.println("FOUR");
                return strength;
            }




            if(currentCardCount == 3) {
                boolean foundPair = false;
                System.out.println("looking for a pair from a triple");
                System.out.println();

                for (int j = 0; j < cards.size(); j++) {
                    System.out.println("Card: " + cards.get(j)[0] + " Count:" + cards.get(j)[1]);



                    if(Integer.parseInt(cards.get(j)[1]) == 2)   {
                        System.out.println("Found a pair with a triple");
                        foundPair = true;
                        break;
                    }
                }

                if(foundPair)   {
                    System.out.println("FULL");
                    strength = 5;
                }
                else   {
                    System.out.println("THREEKIND");
                    strength = 4;
                }
                return strength;
            }
            else if(currentCardCount == 2)   {
                boolean foundPair = false;
                boolean foundTriple = false;
                for(int j = 0; j < cards.size(); j++)   {
                    if(Integer.parseInt(cards.get(j)[1]) == 2 && !cards.get(j)[0].equals(cards.get(i)[0]))   {
                        foundPair = true;
                        break;
                    }
                    if(Integer.parseInt(cards.get(j)[1]) == 3 && !cards.get(j)[0].equals((cards.get(i)[1])))   {

                        foundTriple = true;
                        break;
                    }
                }

                if(foundTriple)   {
                    strength = 5;
                    return strength;
                }

                if(foundPair)   {
                    System.out.println("TWO PAIR");
                    strength = 3;
                }
                else   {
                    strength = 2;
                    System.out.println("ONE PAIR");
                }
                return strength;

            }









        }
        System.out.println("Strength: " + strength);

    return strength;

    }






}
