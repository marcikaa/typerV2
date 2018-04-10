package com.mrm.typer.model;

import java.util.Random;

public class LetterGenerator {

    /**
     * Generates a letter to press from {W,A,S,D}.
     * @return
     */
    public static String generateLetterToPush() {

        Random rand = new Random();
        int n = rand.nextInt(50) + 1;

        if (n % 4 == 0) {
            return "s";
        } else if (n % 4 == 1) {
            return "w";

        } else if (n % 4 == 2) {
            return "a";
        } else if (n % 4 == 3) {
            return "d";
        }
        return "a";
    }

}
