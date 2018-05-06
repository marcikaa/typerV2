package com.mrm.typer.controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Ez az osztály felelős a főmenüben való navigálásért.
 * @author marcikaa
 */
public class MainMenuController implements Initializable {
    
   private static Logger logger = LoggerFactory.getLogger(MainMenuController.class);
    
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
    
    
    protected static String nameOfPlayer = "Unknown Player";
    
    
//    public String getNameOfPlayer() {
//        return nameOfPlayer;
//    }
    
    
    /**
     * Új játék kezdésésének előkészítése.
     * @param mouseEvent Kattintásra betölti az új Scene-t
     */
    public void action_NewGame(MouseEvent mouseEvent) {
        
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mrm/typer/view/Game.fxml")); Ez így nem jó!!!!!!!!!!!!
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/Game.fxml"));
        
        Parent root = null;
        try {
            root = (Parent) loader.load();
            GameController gameController = loader.getController();
            logger.info("Game.fxml loaded");
            
            if (textfield_name.getText().isEmpty())
            {
                textfield_name.setText("Unknown player");
            }
            
            gameController.setPlayerName(textfield_name.getText());
            
            
            rootPane.getChildren().clear();
            rootPane.getChildren().add(root);
        } catch (IOException e) {
            logger.error("Can't load Game.fxml", e);    //TODO: Nézd, marcika! a logolásba baszasd mellé az általad beírt errro message mellé az e-t, mint exceptiont
            //e.printStackTrace();                      //Minden egyes ilyen printStachTrace-t meg vegyél ki, ezt helyettesítjük logolássalé!!!!
        }
    }
    
    /**
     * Betölti a pontlistát.
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
            logger.error("Can't load Score.fxml");
            e.printStackTrace();
        }
    }
    
    /**
     * Kilépés az alkalmazásból
     * @param mouseEvent Kilép az alkalmazásból
     */
    public void action_Exit(MouseEvent mouseEvent) {
        Stage stage = (Stage) btn_Exit.getScene().getWindow();
        logger.info("Exiting game");
        stage.close();
    }
    
    public void initialize(URL location, ResourceBundle resources) {
    }
}
