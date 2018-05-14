package com.mrm.typer.controller;

import com.mrm.typer.model.*;
import com.mrm.typer.model.DB.DataBase;
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
import com.mrm.typer.model.entity.JPAEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Ez az osztály felelős mindenért, ami a játékban történik.
 *
 * @author marcikaa
 */
public class GameController extends GameLoop {
    //CHECKSTYLE:OFF
    private static final DataBase DB = DataBase.getDbPeldany();
    private static Logger logger = LoggerFactory.getLogger(GameController.class);
    //CHECKSTYLE:ON
    /**
     * Visszaadja az adott scene stage-ét.
     *
     * @return az adott scene stage-ével tér vissza
     */
    public Stage rootStage() {
        return (Stage) mainPane1.getScene().getWindow();
    }
    
    /**
     * Kezeli a lenyomott billentyűket.
     *
     * @param mainPane1 Megadhatjuk hogy melyik Pane-n kezelje a billentyűket.
     */
    public void setControl(AnchorPane mainPane1) {
        
        rootStage().getScene().setOnKeyPressed((event) -> {
            if (!le.generatedCmps.isEmpty() && !checkStateOfGame.isGameOver) {
                switch (event.getCode()) {
                    case W:
                        if (le.getFirst() == "w") {
                            le.remove();
                            mainPane1.getChildren().remove(0);
                            setScore(getScore() + 1);
                        } else {
                            setMissedKeyPresses(getMissedKeyPresses() + 1);
                        }
                        break;
                    case A:
                        if (le.getFirst() == "a") {
                            le.remove();
                            mainPane1.getChildren().remove(0);
                            setScore(getScore() + 1);
                        } else {
                            setMissedKeyPresses(getMissedKeyPresses() + 1);
                        }
                        break;
                    case S:
                        if (le.getFirst() == "s") {
                            le.remove();
                            mainPane1.getChildren().remove(0);
                            setScore(getScore() + 1);
                        } else {
                            setMissedKeyPresses(getMissedKeyPresses() + 1);
                        }
                        break;
                    case D:
                        if (le.getFirst() == "d") {
                            le.remove();
                            mainPane1.getChildren().remove(0);
                            setScore(getScore() + 1);
                        } else {
                            setMissedKeyPresses(getMissedKeyPresses() + 1);
                        }
                        break;
                }
                logger.trace("{} is pressed", event.getCode());
            }
            
        });
        
    }
    //CHECKSTYLE:OFF
    ScoreController scoreController = new ScoreController();
    public ListOfEnemies le = new ListOfEnemies();
    
    CheckStateOfGame checkStateOfGame = new CheckStateOfGame();
    
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
    //CHECKSTYLE:ON
    
    /**
     *Hozzáadja a listához az ellenfeleket.
     * @param al betű ami az adott ellenfelen lesz
     */
    public void addToListst(String al){
        if (al != null) {
            le.generatedCmps.add(spawnCmp(al));
            le.generatedLetterToCmps.add(al);
            logger.info("Sikeresen hozzáadva a listához!");
        }
    }
    
    /**
     * Betölti a képet.
     * @return imageview a képpel együtt
     */
    public ImageView initPic(){
        Image image = new Image(getClass().getClassLoader().getResource("textures/cmp_alive.png").toString(), false);
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        return imageView;
    }
    
    
    /**
     * Hozzáad egy ellenfelet, rajta egy betűvel.
     *
     * @param a node-on megjelenő betű
     * @return egy node-al tér vissza, ami tartalmaz egy képet és egy random
     * betűt
     */
    public Node spawnCmp(String letterToPush) {
        int randomMultiplier = (int) (Math.random() * 12) * 50;
        final Canvas canvas = new Canvas(50, 50);
        
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.rgb(0, 100, 0));
        
//Hozzáad egy betűt a képhez
Label letter = new Label(letterToPush);
letter.setFont(Font.font(30));
letter.setTranslateY(letter.getTranslateX() - 7);
letter.setTextFill(Color.rgb(0, 255, 0));

//Hozzáad mindent a Pane-hez
StackPane stackPane = new StackPane();
stackPane.setPrefSize(50, 50);
stackPane.getChildren().addAll(canvas, initPic(), letter);
stackPane.setTranslateY(randomMultiplier);

//Hozzáad mindent a mainPane1-hez
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
     * Minden olyan dolgot tartalmaz, amit képkockánként frissíteni kell. A
     * timer-ben hívjuk meg.
     */
    public void onUpdate() {
        for (Node generatedCmp : le.generatedCmps) {
            generatedCmp.setTranslateX(generatedCmp.getTranslateX() + getDifficultyMultiplier());
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
        if (upd > 160) {
            if (Math.random() < 0.23) {
                String actualLetter = generateLetterToPush();
                addToListst(actualLetter);
                logger.trace("Enemy added with letter {}", actualLetter);
                upd = 0;
            }
        }
        
        
        
        //Number Of enemies
        Integer numberOfEnemies = le.generatedCmps.size();
        
        upd++;
        initalizeLabels(numberOfEnemies);
        checkStateOfGame.isGameOver(getMissedKeyPresses(), getLivesLeft());
        
        if (checkStateOfGame.isGameOver == true) {
            
            checkStateOfGame.endGame(timer, getScoreString(), nameOfPlayer);
            checkStateOfGame.printGameOver(label_GameOver, button_backtomain, nameOfPlayer);
            
            //JPA mentés
            JPAEntity playerEntity = new JPAEntity();
            playerEntity.setPlayerName(nameOfPlayer);
            playerEntity.setScore(getScore());
            
            try {
                DB.save(playerEntity);
                logger.info("Score saved " + nameOfPlayer+ ": " + getScoreString());
            } catch (IllegalArgumentException e) {
                logger.error("Couldn't save score" + e);
            } catch (Exception e) {
                logger.error("Couldn't save score" + e);
            }
            
            mainPane1.getChildren().clear();
        }
        
        if (getScore() % 5 == 0 && getScore() != 0) //WINDOWS - difficultyMultiplier = 0.01, LINUX =0.001
        {
            setDifficultyMultiplier(getDifficultyMultiplier() + 0.0001);
        }
    }
    
    /**
     * Kattintásra elindítja a játékot.
     *
     * @param actionEvent Unused.
     */
    public void clickToStart(ActionEvent actionEvent) {
        mainPane1.getChildren().clear();
        timer.start();
        setControl(mainPane1);
        logger.info("Game started");
        
    }
    
    /**
     * Ez a metódus betölti a főmenüt.
     *
     * @param actionEvent Unused.
     * @throws IOException amennyiben nem létezik a megadott fájl.
     */
    public void backToMain(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/MainMenu.fxml"));
        Parent root = null;
        try {
            root = (Parent) loader.load();
            rootPane.getChildren().clear();
            rootPane.getChildren().add(root);
            logger.info("MainMenu.fxml loaded");
        } catch (IOException e) {
            logger.error("Can't load MainMenu.fxml");
        }
    }
    
    /**
     * Ez a metódus felel a játékos nevének beállításáért.
     *
     * @param nm ez lesz a játékos neve
     */
    public void setPlayerName(String nm) {
        nameOfPlayer = nm;
    }
    
    /**
     * Kiírja pillanatnyi életünket, képernyőn megjelent ellenséget, pontunkat
     * és a mellényomott karakterek számát.
     *
     * @param enemy meg kell adnunk hány ellenfél van ebben a pillanatban
     */
    private void initalizeLabels(Integer enemy) {
        label_livesLeft.setText(getLivesLeftString() + "/10");
        label_Enemies.setText(enemy.toString());
        label_Scores.setText(getScoreString()); //TODO
        label_Missed.setText(getMissedKeyPressesString() + "/10");
    }
    
}
