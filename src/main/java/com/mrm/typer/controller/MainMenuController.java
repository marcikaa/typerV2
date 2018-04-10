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

public class MainMenuController implements Initializable {
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

    public String nameOfPlayer = "Unknown Player";

    public String getNameOfPlayer() {
        return nameOfPlayer;
    }

    public void action_NewGame(MouseEvent mouseEvent) {
        AnchorPane root;
        
        try{
           root = FXMLLoader.load(getClass().getResource("/com/mrm/typer/view/Game.fxml"));
           rootPane.getChildren().clear();
           rootPane.getChildren().addAll(root);
        }catch(IOException e){
        //TODO
        }
        
       // FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("/com/mrm/typer/view/Game.fxml"));
        //Parent root = null;
        /*try {
            root = (Parent) loader.load();
            

            if (textfield_name.getText().isEmpty())
            {
                textfield_name.setText("Unknown player");
            }
            GameController gameController = loader.getController();
            gameController.setPlayerName(textfield_name.getText());


           rootPane.getChildren().clear();
            rootPane.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public void action_Scores(MouseEvent mouseEvent) {

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("/com/mrm/typer/view/Score.fxml"));
        Parent root = null;
        try {
            root = (Parent) loader.load();
            rootPane.getChildren().clear();
            rootPane.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void action_Exit(MouseEvent mouseEvent) {
        Stage stage = (Stage) btn_Exit.getScene().getWindow();
        stage.close();
    }

    public void initialize(URL location, ResourceBundle resources) {
    }
}
