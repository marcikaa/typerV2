package com.mrm.typer.model;


/**
 * POJO osztály az adatbázisnak.
 */
public class Result {

    /**
     * Játékos nevét tartalmazó változó.
     */
    private final String name;
    
    /**
     * Játékos pontját tartalmazó változó.
     */
    private final String score;
//    private final Integer score;


/**
 * Konstruktor.
 * @param name a játékos neve
 * @param score a játékos pontja 
 */
    public Result(String name, String score) {
        this.name = name;
        this.score = score;
    }
    
    
/**
 * Visszaadja a játékos nevét.
 * @return {@code String} a játékos neve
 */
    public String getName() {
        return name;
    }
    
/**
 * Visszaadja a játékos pontját.
 * @return {@code String} a játékos pontja
 */
    public String getScore() {
        return score;
    }


    /**
     * Tostring metódus.
     * @return {@code String} név és pontszám kiírása.
     */
    @Override
    public String toString() {
        return "Result{" + "name=" + name + ", score=" + score + '}';
    }

}
