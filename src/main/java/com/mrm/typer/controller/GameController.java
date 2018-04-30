package com.mrm.typer.controller;

import com.mrm.typer.model.*;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import com.mrm.typer.model.ListOfEnemies;

import java.io.IOException;

import static com.mrm.typer.model.LetterGenerator.generateLetterToPush;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Ez az osztály felelős mindenért, ami a játékban történik.
 * @author marcikaa
 */
public class GameController extends GameLoop {
    
    private static Logger logger = LoggerFactory.getLogger(GameController.class);
    
    /**
     * Visszaadja az adott scene stage-ét.
     * @return az adott scene stage-ével tér vissza
     */
    public Stage rootStage() {
        return (Stage) mainPane1.getScene().getWindow();
    }
    
    /**
     * Kezeli a lenyomott billentyűket.
     * @param mainPane1 Megadhatjuk hogy melyik Pane-n kezelje a billentyűket.
     */
    public void setControl(AnchorPane mainPane1){
        
        rootStage().getScene().setOnKeyPressed((event) -> {
            if ( !le.generatedCmps.isEmpty() &&!checkStateOfGame.isGameOver) {
                switch (event.getCode()) {
                    case W:
                        if (le.getFirst() == "w") {
                            le.remove();
                            mainPane1.getChildren().remove(0);
                            setScore(getScore() + 1);
//                            logger.trace("W pressed");
                        } else setMissedKeyPresses(getMissedKeyPresses() + 1);
                        break;
                    case A:
                        if (le.getFirst() == "a") {
                            le.remove();
                            mainPane1.getChildren().remove(0);
                            setScore(getScore() + 1);
//                            logger.trace("A pressed");
                        } else setMissedKeyPresses(getMissedKeyPresses() + 1);
                        break;
                    case S:
                        if (le.getFirst() == "s") {
                            le.remove();
                            mainPane1.getChildren().remove(0);
                            setScore(getScore() + 1);
//                            logger.trace("S pressed");
                        } else setMissedKeyPresses(getMissedKeyPresses() + 1);
                        break;
                    case D:
                        if (le.getFirst() == "d") {
                            le.remove();
                            mainPane1.getChildren().remove(0);
                            setScore(getScore() + 1);
//                            logger.trace("D pressed");
                        } else setMissedKeyPresses(getMissedKeyPresses() + 1);
                        break;
                }
                logger.trace("{} is pressed", event.getCode());
            }
            
        });
        
    }
    
    ScoreController scoreController = new ScoreController();
    ListOfEnemies le = new ListOfEnemies();
    
    CheckStateOfGame checkStateOfGame = new CheckStateOfGame();
//    DB db = new DB();
    //FXML-begin
    @FXML
            Button button_backtomain;
    
    
    @FXML
            Label label_GameOver;
    
    @FXML
            Label ttc;
    
    @FXML
            AnchorPane rootPane;
    
    
    @FXML
            Button btn_addCmp;
    
    @FXML
            AnchorPane anchorPane_popUp;
    
    @FXML
            AnchorPane mainPane1;
    
    @FXML
            Label label_Missed;
    
    @FXML
            Label label_Enemies;
    
    @FXML
            Label label_Scores;
    
    @FXML
            Label label_livesLeft;
    //FXML-end
   
            
    private String playerName;
    
    /**
     * Hozzáad egy "ellenséget", rajta egy betűvel.
     *
     * @param letterToPush  {@code Lettergenerator} generál egy véletlen betűt a {W,A,S,D} halmazból.
     * @return egy node-al tér vissza, ami tartalmaz egy képet és egy random betűt
     */
    public Node spawnCmp(String letterToPush) {
        int randomMultiplier = (int) (Math.random() * 12) * 50;
        final Canvas canvas = new Canvas(50, 50);
        
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.rgb(100, 100, 100));
        
        Image image = new Image(getClass().getResource("/textures/cmp_alive.png")
                .toString(), false);
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        
        //Adds a letter to the image
        Label letter = new Label(letterToPush);
        letter.setFont(Font.font(30));
        letter.setTranslateY(letter.getTranslateX() - 7);
        letter.setTextFill(Color.PINK);
        
        //Adds everything to a pane
        StackPane stackPane = new StackPane();
        stackPane.setPrefSize(50, 50);
        stackPane.getChildren().addAll(canvas, imageView, letter);
        stackPane.setTranslateY(randomMultiplier);
        
        //Adds the stackpane to our mainpane
        mainPane1.getChildren().add(stackPane);
        
        return stackPane;
    }
    
    /**
     * Egy időzítő ami minden képkockában meghívódik.
     */
    public AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            onUpdate();
        }
    };
    
    
    /**
     * Minden olyan dolgot tartalmaz, amit képkockánként frissíteni kell.
     * A timer-ben hívjuk meg.
     */
    public void onUpdate() {
        for (Node generatedCmp : le.generatedCmps) {
            generatedCmp.setTranslateX(generatedCmp.getTranslateX() + difficultyMultiplier);
        }
        
        for (int i = 0; i < le.generatedCmps.size(); i++) {
            if (i == 0) {
                if (le.generatedCmps.get(0).getTranslateX() > 980) {
                    setLivesLeft(getLivesLeft() - 1);
                    le.remove();
                    mainPane1.getChildren().remove(0);
                }
                le.setFirstEffects();
            }
            if (i != 0) {
                le.setEffects(i);
            }
        }
        //WINDOWS - upd > 30, LINUX = 150
        if (upd > 30) {
            if (Math.random() < 0.23) {
                String actualLetter = generateLetterToPush();
                le.generatedCmps.add(spawnCmp(actualLetter));
                logger.trace("Enemy added with letter {}", actualLetter);
                le.generatedLetterToCmps.add(actualLetter);
                upd = 0;
            }
        }
        //Number Of enemies
        Integer numberOfEnemies = le.generatedCmps.size();
//        String noEnemies = numberOfEnemies.toString();
        
        upd++;
        initalizeLabels(numberOfEnemies);
        checkStateOfGame.isGameOver(getMissedKeyPresses(), getLivesLeft());
        
        if (checkStateOfGame.isGameOver == true) {
            Result myRes = new Result(playerName,getScore().toString());
//            db.addResult(myRes);
            
            
            
            checkStateOfGame.endGame(timer, getScoreString(), playerName);
            checkStateOfGame.printGameOver(label_GameOver, button_backtomain, playerName);
            mainPane1.getChildren().clear();
        }
        
        if (getScore() % 5 == 0 && getScore() != 0)
            //WINDOWS - difficultyMultiplier = 0.01, LINUX =0.001
            setDifficultyMultiplier(difficultyMultiplier + 0.01);
    }
    
    /**
     * Kattintásra elindítja a játékot.
     * @param actionEvent 
     */
    public void clickToStart(ActionEvent actionEvent) {
        mainPane1.getChildren().clear();
        timer.start();
        setControl(mainPane1);
        logger.info("Game started");
        
    }
    

    /**
     * Ez a metódus betölti a főmenüt.
     * @param actionEvent
     * @throws IOException amennyiben nem létezik a megadott fájl.
     */
    public void backToMain(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mrm/typer/view/MainMenu.fxml"));
        Parent root = null;
        try {
            root = (Parent) loader.load();
            rootPane.getChildren().clear();
            rootPane.getChildren().add(root);
            logger.info("MainMenu.fxml loaded");
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Can't load MainMenu.fxml");
        }
    }
    
    /**
     * Ez a metódus felel a játékos nevének beállításáért.
     * @param nm 
     */
    public void setPlayerName(String nm) {
        playerName = nm;
    }
    
    /**
     * Kiírja pillanatnyi életünket, képernyőn megjelent ellenséget, pontunkat és a 
     * mellényomott karakterek számát.
     * @param enemy 
     */
    private void initalizeLabels(Integer enemy) {
        label_livesLeft.setText(getLivesLeftString() + "/10");
        label_Enemies.setText(enemy.toString());
        label_Scores.setText(getScoreString()); //TODO
        label_Missed.setText(getMissedKeyPressesString() + "/10");
    }
    
}

