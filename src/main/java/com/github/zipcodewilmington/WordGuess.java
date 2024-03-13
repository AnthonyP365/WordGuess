package com.github.zipcodewilmington;


import java.util.Random;
import java.util.Scanner;

/**
 * @author xt0fer
 * @version 1.0.0
 * @date 5/27/21 11:02 AM
 */
public class WordGuess {
    private static String[] wordsArray = {"dog", "cat", "bog", "cut"};
    private static String randomWord;
    private static char[] guessedWord;
    private static int maxTry = 0;
    private static int attemptsLeft;

    public WordGuess() {}

    public static void main(String[] args) {
        selectedWord();
        initializeGameState();
        runGame();
    }

    private static void selectedWord() {        // Selects a random word from the word array
        int randomIndex = (int) (Math.random()* wordsArray.length);
        randomWord = wordsArray[randomIndex];
    }

    private static void initializeGameState() {     // Sets up a char array based on the selected words' length.
        guessedWord = new char[randomWord.length()];
        maxTry = wordsArray.length-1;

        for (int i = 0; i < randomWord.length(); i++) {
            guessedWord[i] = '_';
        }
    }

    private static void runGame() {     // Starts the game and loops through prompting player for char input
        announceGame();
        Scanner scanner = new Scanner(System.in);
        attemptsLeft = maxTry;

        while ((attemptsLeft > 0) && !isWordGuessed()) {
            printCurrentState();
            System.out.print("Enter a single character: ");
            char guess = scanner.next().charAt(0);
            System.out.println(guess);

            if (guess == '-') {
                attemptsLeft = 0;
                gameOver();
            } else {
                process(guess);
            }
        }

        if (isWordGuessed()) {
            playerWon();
            playAgain();
        } else {
            playerLost();
            playAgain();
        }
    }

    private static void printCurrentState() {
        System.out.println("Current Guesses: " + attemptsLeft);
        System.out.println(new String(guessedWord));
    }

    private static void process(char guess) {
        boolean isCorrect = false;

        for (int i = 0; i < randomWord.length(); i++) {
            if (randomWord.charAt(i) == guess) {
                guessedWord[i] = guess;
                isCorrect = true;
            }
        }

        if (!isCorrect) {
            attemptsLeft--;
            System.out.println("Incorrect guess. Try again!");
        }
    }

    private static boolean isWordGuessed() {
        return new String(guessedWord).equals(randomWord);
    }

    private static void playerWon() {
        System.out.println("Congratulations, You Won!");
    }

    private static void playerLost() {
        System.out.println("You Lost! You ran out of guesses.");
    }

    private static void announceGame() {
        System.out.println("Let's Play Wordguess version 1.0");
    }

    private static void gameOver() {
        System.out.println("Game Over");
    }

    private static void playAgain() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to play again? (y/n)");

        String play = scanner.nextLine();
        System.out.println(play);


        if (play.equalsIgnoreCase("y")) {
            selectedWord();
            initializeGameState();
            runGame();
        } else {
            gameOver();
        }
    }

}
