package com.mrm.typer.controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * Ez az osztály felelős a főmenüben való navigálásért.
 *
 * @author marcikaa
 */
public class MainMenuController implements Initializable {
    /**
     * {@code Logger} objektum.
     */
    private static Logger logger = LoggerFactory.getLogger(MainMenuController.class);
    //CHECKSTYLE:OFF
    @FXML
    AnchorPane rootPane;

    @FXML
    AnchorPane mainPane;

    @FXML
    AnchorPane subPane;

    @FXML
    Button btn_NewGame;

    @FXML
    Button btn_Exit;

    @FXML
    Button btn_Scores;

    @FXML
    TextField textfield_name;

    @FXML
    CheckBox checkbox_os;
    //CHECKSTYLE:ON

    /**
     * Játékos nevének alapértelmezett értéke.
     */
    protected static String nameOfPlayer = "Unknown Player";


    public void newGame(){
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/Game.fxml"));

        Parent root = null;
        try {
            root = (Parent) loader.load();
            GameController gameController = loader.getController();
            logger.info("Game.fxml loaded");

            if (textfield_name.getText().isEmpty()) {
                textfield_name.setText("Unknown player");
            }

            gameController.setPlayerName(textfield_name.getText());
            gameController.setOs(checkbox_os.isSelected());


            rootPane.getChildren().clear();
            rootPane.getChildren().add(root);
        } catch (IOException e) {
            logger.error("Can't load Game.fxml" + e);
        }
    }


    /**
     * Új játék kezdésésének előkészítése.
     *
     * @param mouseEvent Kattintásra betölti az új Scene-t
     */
    public void action_NewGame(MouseEvent mouseEvent) {
        newGame();
    }

    /**
     * Betölti a pontlistát.
     *
     * @param mouseEvent betölti a pontlistát.
     */
    public void action_Scores(MouseEvent mouseEvent) {

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/Score.fxml"));
        Parent root = null;
        try {
            root = (Parent) loader.load();
            rootPane.getChildren().clear();
            rootPane.getChildren().add(root);
            logger.info("Score.fxml loaded");
        } catch (IOException e) {
            logger.error("Can't load Score.fxml" + e);
        }
    }

    /**
     * Kilépés az alkalmazásból.
     *
     * @param mouseEvent Kilép az alkalmazásból
     */
    public void action_Exit(MouseEvent mouseEvent) {
        Stage stage = (Stage) btn_Exit.getScene().getWindow();
        logger.info("Exiting game");
        stage.close();
    }

    //CHECKSTYLE:OFF
    public void initialize(URL location, ResourceBundle resources) {
    }

//CHECKSTYLE:ON


}