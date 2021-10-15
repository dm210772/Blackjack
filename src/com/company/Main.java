package com.company;

import java.util.Scanner;

public class Main {

    static int comSum;
    static int playSum;
    static boolean userIsBetting;
    static double wallet;
    static double playerBet;

    public static void main(String[] args) {
        // write your code here
        Scanner input = new Scanner(System.in);

        while (true) {

            bettingCall();

            for (int i = 0; i < 2; i++) {
                comSum = numberAdder(comSum);
            }

            for (int i = 0; i < 2; i++) {
                playSum = numberAdder(playSum);
            }

            System.out.println(playSum);

            if (playSum < 21 && comSum < 21) {
                System.out.println("Do you want another number? (Y/N)");
                String choice = input.next();

                if (choice.equalsIgnoreCase("Y")) {
                    playSum += numberAdder(playSum);
                    comSum += numberAdder(comSum);
                    System.out.println(playSum);
                }
            }

            winnerCheck();

            if (userIsBetting && wallet < 0) {
                System.out.println("Wuh oh! You ran out of funds! Get out.");
                System.exit(0);
            }

            System.out.println("Would you like to play again? (Y/N)");
            String playAgain = input.next();

            if (playAgain.equalsIgnoreCase("N")) {
                break;
            }
        }
    }

    static int numberAdder(int x) {
        x += (int) (1 + (Math.random() * 11));
        return x;
    }

    static void winnerCheck() {
        if (playSum < 21 && comSum < 21) {
            if (comSum > playSum) {
                System.out.println("You lose. The computer had " + comSum + ".");
                if (userIsBetting) {
                    System.out.println("£" + wallet);
                }
            } else if (playSum > comSum) {
                System.out.println("The computer had " + comSum + ". You win!");
                if (userIsBetting) {
                    wallet += playerBet * 2;
                    System.out.println("£" + wallet);
                }
            } else if (playSum == comSum) {
                System.out.println("It's a tie.");
                if (userIsBetting) {
                    wallet += playerBet;
                    System.out.println("£" + wallet);
                }
            } else {
                System.out.println("The computer had " + comSum + ". You both lose.");
                if (userIsBetting) {
                    wallet += playerBet;
                    System.out.println("£" + wallet);
                }
            }
        } else if (playSum > 21 && comSum > 21) {
            System.out.println("The computer had " + comSum + ". You both lose.");
            if (userIsBetting) {
                wallet += playerBet;
                System.out.println("£" + wallet);
            }
        } else if (comSum > 21) {
            System.out.println("The computer had " + comSum + ". You win!");
            if (userIsBetting) {
                wallet += playerBet * 2;
                System.out.println("£" + wallet);
            }
        } else if (playSum > 21) {
            System.out.println("You lose. The computer had " + comSum + ".");
            if (userIsBetting) {
                System.out.println("£" + wallet);
            }
        }
    }

    static void bettingCall() {
        Scanner input = new Scanner(System.in);

        System.out.println("Would you like to bet on this game? (Y/N)");
        String betChoice = input.next();

        if (betChoice.equalsIgnoreCase("Y")) {
            System.out.println("How many funds do you have?(for totally real legal reasons the max is £100)");
            wallet = input.nextDouble();

            System.out.println("Place your bet (in GBP£):");
            playerBet = input.nextDouble();
            wallet -= playerBet;

            userIsBetting = true;
        } else {
            userIsBetting = false;
        }
    }
}
