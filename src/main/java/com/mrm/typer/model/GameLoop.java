package com.mrm.typer.model;

import com.mrm.typer.controller.MainMenuController;
/**
 * A játék adatait tároló osztály.
 * @author marcikaa
 */
public class GameLoop extends MainMenuController {
    
    //WINDOWS  difficultyMultiplier = 2, LINUX = 0.5
    
    /**
     * Gyorsaság mértéke.
     */
    private double difficultyMultiplier = 2;
    
    /**
     * Gyorsaság mértékének beállítása.
     * @param difficultyMultiplier mennyire szeretnénk beállítani a gyorsaságot
     */
    public void setDifficultyMultiplier(double difficultyMultiplier) {
        this.difficultyMultiplier = difficultyMultiplier;
    }

    /**
     * Gyorsaság mértékének lekérése.
     * @return {@code double} néhézség mértéke
     */
    public double getDifficultyMultiplier() {
        return difficultyMultiplier;
    }
    
    /**
     * Melléütött karakterek számlálója.
     */
    private Integer missedKeyPresses = 0;
    
    /**
     * Pontok számlálása.
     */
    private Integer score = 0;
    
    /**
     * Mennyi életünk van még hátra.
     */
    private Integer livesLeft = 10;

    private double translateSpeed;

    public double getTranslateSpeed() {
        return translateSpeed;
    }

    public void setTranslateSpeed(double translateSpeed) {
        this.translateSpeed = translateSpeed;
    }

    /**
     *Visszaadja a pontjaink számát {@code String}-ként.
     * @return a pontjaink száma {@code String}-ként
     */
    public String getScoreString() {
        return score.toString();
    }
 
    
    /**
     * Visszadja a pontjaink számát.
     * @return a pontjaink száma {@code Integer}-ként
     */
    public Integer getScore() {
        return score;
    }
    
    /**
     * Visszaadja hány életünk maradt még.
     * @return életünk száma {@code Integer}-ként
     */
    public Integer getLivesLeft() {
        return livesLeft;
    }
    
    /**
     * Visszaadja hány életünk maradt még {@code String}-ként.
     * @return életünk száma {@code String}-ként
     */
    public String getLivesLeftString(){
        return livesLeft.toString();
    }
    
    /**
     * Megadhatjuk hány életünk legyen még.
     * @param livesLeft beállítáni kívánt élet
     */
    public void setLivesLeft(Integer livesLeft) {
        this.livesLeft = livesLeft;
    }
    
    /**
     * Megadhatjuk a pontjaink számát.
     * @param score megadhatjuk a pontjaink számát {@code int}-ként
     */
    public void setScore(int score) {
        this.score = score;
    }
    
    /**
     * Ennek az a feladata, hogy az új ellenfél hozzáadásának gyakoriságát állítja.
     * Bővebben {@link com.mrm.typer.controller.GameController#onUpdate() }
     */
    public Integer upd = 0;
    public Integer os_UPD = 30;

    public Integer getUpd() {
        return upd;
    }

    public void setUpd(Integer upd) {
        this.upd = upd;
    }

    /**
     * Visszaadja hány billentyűt tévesztettünk.
     * @return visszaad egy {@code int}-et a melléütések számáról
     */
    public int getMissedKeyPresses() {
        return missedKeyPresses;
    }
    
    /**
     * Visszaadja hány billentyűt tévesztettünk {@code String}-ként.
     * @return visszaad egy {@code String}-et a melléütések számáról
     */
    public String getMissedKeyPressesString() {
        
        return missedKeyPresses.toString();
    }
    
    /**
     * Megadhatjuk hány melléütésünk legyen.
     * @param missedKeyPresses egy {@code int} amivel megadjuk hányat hibáztunk.
     */
    public void setMissedKeyPresses(int missedKeyPresses) {
        this.missedKeyPresses = missedKeyPresses;
    }
    
}
