package com.mrm.typer.model;


/**
 * POJO osztály az adatbázisnak.
 */
public class Result {
    
    /**
     * Játékos nevét tartalmazó változó.
     */
    private  String name;
    
    /**
     * Játékos pontját tartalmazó változó.
     */
    private  String score;
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
     * A pontot állíthatjuk vele be.
     * @param s a pont amit beállítunk
     */
    public void setScore(String s){
        
        this.score = s;
        
    }

    /**
     * A nevet állíthatjuk vele be.
     * @param s név amit beállítunk
     */
    public void setName(String s){
        
        this.name = s;
        
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
