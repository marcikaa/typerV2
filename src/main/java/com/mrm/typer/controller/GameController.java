package com.mrm.typer.controller;

import com.mrm.typer.model.*;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.MotionBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import com.mrm.typer.model.ListOfEnemies;

import java.io.IOException;

import static com.mrm.typer.model.LetterGenerator.generateLetterToPush;

public class GameController extends GameLoop {

    public Stage rootStage() {
        return (Stage) mainPane.getScene().getWindow();
    }

    public void setControl(AnchorPane mainPane) {

        rootStage().getScene().setOnKeyPressed((event) -> {
            if (!le.generatedLetterToCmps.isEmpty() && !checkStateOfGame.isGameOver) {
                switch (event.getCode()) {
                    case W:
                        if (le.getFirst() == "w") {
                            le.remove();
                            mainPane.getChildren().remove(0);
                            setScore(getScore() + 1);
                        } else setMissedKeyPresses(getMissedKeyPresses() + 1);
                        break;
                    case A:
                        if (le.getFirst() == "a") {
                            le.remove();
                            mainPane.getChildren().remove(0);
                            setScore(getScore() + 1);
                        } else setMissedKeyPresses(getMissedKeyPresses() + 1);
                        break;
                    case S:
                        if (le.getFirst() == "s") {
                            le.remove();
                            mainPane.getChildren().remove(0);
                            setScore(getScore() + 1);
                        } else setMissedKeyPresses(getMissedKeyPresses() + 1);
                        break;
                    case D:
                        if (le.getFirst() == "d") {
                            le.remove();
                            mainPane.getChildren().remove(0);
                            setScore(getScore() + 1);
                        } else setMissedKeyPresses(getMissedKeyPresses() + 1);
                        break;
                }

            }
        });

    }

    ScoreController scoreController = new ScoreController();
    ListOfEnemies le = new ListOfEnemies();

    CheckStateOfGame checkStateOfGame = new CheckStateOfGame();
    DB db = new DB();
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
    AnchorPane mainPane;

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
     * Adds an enemy with a key to press.
     *
     * @param letterToPush a randomly generated letter.
     * @return a Node of a StackPane which contains an image and a letter on it
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
        mainPane.getChildren().add(stackPane);

        return stackPane;
    }


    public AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            onUpdate();
        }
    };


    /**
     * A method that contains everything that needs render by the time.
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
                    mainPane.getChildren().remove(0);
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
                le.generatedLetterToCmps.add(actualLetter);
                upd = 0;
            }
        }
        //Number Of enemies
        Integer numberOfEnemies = le.generatedCmps.size();
        String noEnemies = numberOfEnemies.toString();

        upd++;
        initalizeLabels(numberOfEnemies);
        checkStateOfGame.isGameOver(getMissedKeyPresses(), getLivesLeft());

        if (checkStateOfGame.isGameOver == true) {
            Result myRes = new Result(playerName,getScore().toString());
            db.addResult(myRes);



            checkStateOfGame.endGame(timer, getScoreString(), playerName);
            checkStateOfGame.printGameOver(label_GameOver, button_backtomain, playerName);
            mainPane.getChildren().clear();
        }

        if (getScore() % 5 == 0 && getScore() != 0)
            //WINDOWS - difficultyMultiplier = 0.01, LINUX =0.001
            setDifficultyMultiplier(difficultyMultiplier + 0.01);
    }


    public void clickToAdd(ActionEvent actionEvent) {
        mainPane.getChildren().clear();
        timer.start();
        setControl(mainPane);

    }

    private void resetGame() {

    }

    public void backToMain(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mrm/typer/view/MainMenu.fxml"));
        Parent root = null;
        try {
            root = (Parent) loader.load();
            rootPane.getChildren().clear();
            rootPane.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setPlayerName(String nm) {
        playerName = nm;
    }

    private void initalizeLabels(Integer enemy) {
        label_livesLeft.setText(getLivesLeftString() + "/10");
        label_Enemies.setText(enemy.toString());
        label_Scores.setText(getScoreString()); //TODO
        label_Missed.setText(getMissedKeyPressesString() + "/10");
    }

}

