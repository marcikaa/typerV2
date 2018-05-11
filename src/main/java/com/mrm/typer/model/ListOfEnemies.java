package com.mrm.typer.model;

import javafx.scene.Node;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.MotionBlur;

import java.util.ArrayList;
import java.util.List;

/**
 * Ez az osztály felelős az ellenfelek tárolásáért, és
 * azok effektjeiért.
 * @author marcikaa
 */
public class ListOfEnemies {
    
    /**
     * Egy lista ami tartalmazza az ellenségeket.
     */
    public List<Node> generatedCmps = new ArrayList<>();
    
    /**
     * Egy lista ami tartalmazza az ellenségeken lévő betűket.
     */
    public List<String> generatedLetterToCmps = new ArrayList<>();
    
    /**
     *Konstruktor.
     */
    public ListOfEnemies() {
        
    }
    
    /**
     * Eltávolítja a 0. elemet.
     */
    public void remove() {
        this.generatedCmps.remove(0);
        this.generatedLetterToCmps.remove(0);
    }
    
    /**
     * Visszaadja az első ellenfélen lévő betűt.
     * @return az első ellenfélen lévő betűvel
     */
    public String getFirst() {
        return this.generatedLetterToCmps.get(0);
    }
    
    /**
     * Effektekért felelős beállítás.
     */
    MotionBlur motionBlur = new MotionBlur();
    
    /**
     * Effektekért felelős beállítás.
     */
    ColorAdjust colorAdjust = new ColorAdjust();
    
    /**
     * Effektekért felelős beállítás a listában lévő első ellenfélre.
     */
    MotionBlur motionBlurFirst = new MotionBlur();
    
    /**
     * Effektekért felelős beállítás a listában lévő első ellenfélre.
     */
    ColorAdjust colorAdjustFirst = new ColorAdjust();
    
    /**
     * Beállítja az első ellenfél színét és annak elmosását.
     */
    public void setFirstEffects() {
        motionBlurFirst.setRadius(0);
        motionBlurFirst.setAngle(0);
        colorAdjustFirst.setSaturation(1);
        this.generatedCmps.get(0).setEffect(motionBlurFirst);
        motionBlurFirst.setInput(colorAdjustFirst);
        this.generatedCmps.get(0).setEffect(motionBlurFirst);
    }
    
    /**
     * Beállítja a többi ellenfél színét és azok elmosását.
     * @param i beállítandó node indexe
     */
    public void setEffects(int i){
        motionBlur.setRadius(0.8);
        motionBlur.setAngle(-2.0);
        colorAdjust.setSaturation(-0.8);
        this.generatedCmps.get(i).setEffect(motionBlur);
        motionBlur.setInput(colorAdjust);
        this.generatedCmps.get(i).setEffect(motionBlur);
    }
    
}
