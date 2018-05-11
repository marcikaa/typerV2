package com.mrm.typer.model;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Ez az osztály felelős azért, hogy ellenőrízze a Game Over feltételeit.
 * @author marcikaa
 */
public class CheckStateOfGame extends GameLoop {

    /**
     * Amennyiben vége a játéknak ezt a változót változtatjuk {@code True} értékre.
     */
    public boolean isGameOver = false;

    /**
     * Amennyiben elfogy az életünk, vagy túlléptük a megengedett
     * félrenyomásokat, akkor az isGameOver változót igazra állítja,
     * ezzel jelezve a játék végét.
     * @param misses a félrenyomott karakterek száma
     * @param lives hátralévő életeink száma
     */
    public void isGameOver(int misses, int lives) {
        if (misses > 9 || lives == 0) {
            isGameOver = true;
        } else isGameOver = false;
    }

    /**
     * A játék megállításáért felelős metódus.
     * @param timer egy időzítő ami az egész játékmenetet kezeli
     * @param finalScore a játék végén megszerzett pontok száma
     * @param name a játékos
     */
    public void endGame(AnimationTimer timer, String finalScore, String name) {
        if (isGameOver) {
            timer.stop();
        }
    }

    /**
     * Kiírja a game over üzenetet a játékos nevével együtt.
     * @param gameOver egy label amire az üzenetet írjuk
     * @param btm egy gomb ami visszavisz a főmenübe
     * @param name játékos neve
     */
    public void printGameOver(Label gameOver, Button btm, String name) {
        if (isGameOver) {
            btm.setVisible(true);
            gameOver.setText("Game Over " + name);
        }
    }

}


