package com.mrm.typer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javafx.stage.Stage;

/**
 * Ez az osztály felelős az alkalmazás elindításáért, és annak tartalmának a betöltéséért.
 * @author marcikaa
 */
public class MainApp extends Application {


    private static Logger logger = LoggerFactory.getLogger(MainApp.class);
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/com/mrm/typer/view/MainMenu.fxml"));
        root.setId(STYLESHEET_MODENA);
        primaryStage.setTitle("TypoShooter");
        primaryStage.setResizable(false);
//        primaryStage.setScene(new Scene(root, 1000, 800));
        Scene scene = new Scene(root, 1000, 700);
        scene.getStylesheets().add("/styles/mm.css");
        primaryStage.setScene(scene);
        primaryStage.show();
        logger.info("The program is running");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
