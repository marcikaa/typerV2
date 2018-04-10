package com.mrm.typer.model;

import javafx.scene.Node;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.MotionBlur;

import java.util.ArrayList;
import java.util.List;

public class ListOfEnemies {

    public List<Node> generatedCmps = new ArrayList<>();
    public List<String> generatedLetterToCmps = new ArrayList<>();

    public ListOfEnemies() {

    }

    public void remove() {
        this.generatedCmps.remove(0);
        this.generatedLetterToCmps.remove(0);
    }

    public String getFirst() {
        return this.generatedLetterToCmps.get(0);
    }

    public boolean isRightKey(){
return false;
    }

    MotionBlur motionBlur = new MotionBlur();
    ColorAdjust colorAdjust = new ColorAdjust();
    MotionBlur motionBlurFirst = new MotionBlur();
    ColorAdjust colorAdjustFirst = new ColorAdjust();


    public void setFirstEffects() {
        motionBlurFirst.setRadius(0);
        motionBlurFirst.setAngle(0);
        colorAdjustFirst.setSaturation(1);
        this.generatedCmps.get(0).setEffect(motionBlurFirst);
        motionBlurFirst.setInput(colorAdjustFirst);
        this.generatedCmps.get(0).setEffect(motionBlurFirst);
    }

    public void setEffects(int i){
        motionBlur.setRadius(0.1 * i);
        motionBlur.setAngle(-2.0);
        colorAdjust.setSaturation(-0.3 * i);
        this.generatedCmps.get(i).setEffect(motionBlur);
        motionBlur.setInput(colorAdjust);
        this.generatedCmps.get(i).setEffect(motionBlur);
    }

}
