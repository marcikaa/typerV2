package com.mrm.typer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainApp extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/com/mrm/typer/view/MainMenu.fxml"));
        primaryStage.setTitle("TypoShooter");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 1000, 800));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
