package com.mrm.typer.model;

import java.util.Objects;
import javafx.beans.property.SimpleStringProperty;


/**
 * POJO class for database, to score a result
 */
public class Result {

    private final String name;
    private final String score;
//    private final Integer score;



    public Result(String name, String score) {
        this.name = name;
        this.score = score;
    }
    
    

    public String getName() {
        return name;
    }
    

    public String getScore() {
        return score;
    }


    
    @Override
    public String toString() {
        return "Result{" + "name=" + name + ", score=" + score + '}';
    }

}
