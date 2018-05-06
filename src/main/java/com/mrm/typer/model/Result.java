package com.mrm.typer.model;

import java.util.Objects;
import javafx.beans.property.SimpleStringProperty;


/**
 * POJO class for database, to score a result
 */
public class Result {

    private final String name;
    private final String score;

    public Result(String name, String score) {
        this.name = name;
//        this.score = score;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public String getScore() {
        return score;
    }

//    public String getScoreString(){
//    return score.toString();
//    }


    @Override
    public String toString() {
        return "Result{" + "name=" + name + ", score=" + score + '}';
    }

}
