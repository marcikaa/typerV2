package com.mrm.typer.model;

public class GameLoop {

    //WINDOWS  difficultyMultiplier = 2, LINUX = 0.5
    public double difficultyMultiplier = 2;

    public void setDifficultyMultiplier(double difficultyMultiplier) {
        this.difficultyMultiplier = difficultyMultiplier;
    }

    private Integer missedKeyPresses = 0;

    private Integer score = 0;

    private Integer livesLeft = 10;

    public String getScoreString() {
        return score.toString();
    }

    public Integer getScore() {
        return score;
    }

    public Integer getLivesLeft() {
        return livesLeft;
    }

    public String getLivesLeftString(){
        return livesLeft.toString();
    }

    public void setLivesLeft(Integer livesLeft) {
        this.livesLeft = livesLeft;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Integer upd = 0;


    public int getMissedKeyPresses() {
        return missedKeyPresses;
    }

    public String getMissedKeyPressesString() {

        return missedKeyPresses.toString();
    }

    public void setMissedKeyPresses(int missedKeyPresses) {
        this.missedKeyPresses = missedKeyPresses;
    }

}
