package com.mrm.typer.model;

import com.mrm.typer.controller.MainMenuController;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CheckStateOfGame extends GameLoop {


    public boolean isGameOver = false;

    boolean tooMuchMisses = false;

    public void isGameOver(int misses, int lives) {
        if (misses > 9 || lives == 0) {
            isGameOver = true;
        } else isGameOver = false;
    }

    public void endGame(AnimationTimer timer, String finalScore, String name) {
        if (isGameOver) {
            timer.stop();
        }
    }

//    public void checkStateGame(int MissedKeyPresses, int cmpsOnScreen, AnimationTimer timer, String finalScore, Integer lives) {
//
//        if (MissedKeyPresses == 10 || lives == 0) {
//            isGameOver = true;
//            if (MissedKeyPresses == 10) {
//                tooMuchMisses = true;
//            }
//        }
//
//        if (isGameOver) {
//            timer.stop();
//            System.out.println("Dude You lose");
//            System.out.println("Your score is " + finalScore);
//        }
//    }

    public void printGameOver(Label gameOver, Button btm, String name) {
        if (isGameOver) {
            btm.setVisible(true);
            gameOver.setText("Game Over " + name);
        }
    }

}


