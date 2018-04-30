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
    
    /**
     *Visszaadja a pontjaink számát String-ként.
     * @return a pontjaink száma String-ként
     */
    public String getScoreString() {
        return score.toString();
    }
 
    
    /**
     * Visszadja a pontjaink számát.
     * @return a pontjaink száma Integer-ként
     */
    public Integer getScore() {
        return score;
    }
    
    /**
     * Visszaadja hány életünk maradt még.
     * @return életünk száma Integer-ként
     */
    public Integer getLivesLeft() {
        return livesLeft;
    }
    
    /**
     * Visszaadja hány életünk maradt még String-ként
     * @return életünk száma String-ként
     */
    public String getLivesLeftString(){
        return livesLeft.toString();
    }
    
    /**
     * Megadhatjuk hány életünk legyen még.
     * @param livesLeft Integer ami megadja az életeink számát
     */
    public void setLivesLeft(Integer livesLeft) {
        this.livesLeft = livesLeft;
    }
    
    /**
     * Megadhatjuk a pontjaink számát.
     * @param score megadhatjuk a pontjaink számát int-ként
     */
    public void setScore(int score) {
        this.score = score;
    }
    
    public Integer upd = 0;
    
    /**
     * Visszaadja hány billentyűt tévesztettünk.
     * @return visszaad egy intet a melléütések számáról
     */
    public int getMissedKeyPresses() {
        return missedKeyPresses;
    }
    
    /**
     * Visszaadja hány billentyűt tévesztettünk String-ként.
     * @return visszaad egy Stringet a melléütések számáról
     */
    public String getMissedKeyPressesString() {
        
        return missedKeyPresses.toString();
    }
    
    /**
     * Megadhatjuk hány melléütésünk legyen.
     * @param missedKeyPresses egy int amivel megadjuk hányat hibáztunk.
     */
    public void setMissedKeyPresses(int missedKeyPresses) {
        this.missedKeyPresses = missedKeyPresses;
    }
    
}