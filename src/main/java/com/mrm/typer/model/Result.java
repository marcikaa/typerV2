package com.mrm.typer.model;

import javafx.beans.property.SimpleStringProperty;


/**
 * POJO class for database, to score a result
 */
public class Result {

    private final SimpleStringProperty name;
    private final SimpleStringProperty score;
//    private final SimpleIntegerProperty score;
    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }
//String
    public String getScore() {
        return score.get();
    }

    public SimpleStringProperty scoreProperty() {
        return score;
    }

    public void setScore(String score) {
        this.score.set(score);
    }

//
//    public int getScore() {
//        return score.get();
//    }
//    public String getScoreStringa() {
//        return score.toString();
//    }
//
//    public SimpleIntegerProperty scoreProperty() {
//        return score;
//    }
//
//    public void setScore(int score) {
//        this.score.set(score);
//    }

    public Result(String name, String score) {
        this.name = new SimpleStringProperty(name);
        this.score = new SimpleStringProperty(score);
    }

    public String getScoreString(){
        return score.toString();
    }

}
