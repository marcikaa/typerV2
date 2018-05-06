package com.mrm.typer;

import com.mrm.typer.model.DB.DataBase;
import static java.lang.Math.log;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javafx.stage.Stage;

/**
 * Ez az osztály felelős az alkalmazás elindításáért, és annak tartalmának a
 * betöltéséért.
 * 
 * @author marcikaa
 */
public class MainApp extends Application {

    private static final DataBase DB = DataBase.getDbPeldany();
    private static Logger logger = LoggerFactory.getLogger(MainApp.class);

    
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/MainMenu.fxml"));
        root.setId(STYLESHEET_MODENA);
        primaryStage.setTitle("TypoShooter");
        primaryStage.setResizable(false);
//        primaryStage.setScene(new Scene(root, 1000, 800));
        Scene scene = new Scene(root, 1000, 800);

        scene.getStylesheets().add("/styles/mm.css");
        primaryStage.setScene(scene);

        //enélkül alsó+jobboldali keret
        primaryStage.setWidth(1000);
        primaryStage.setHeight(800);

        primaryStage.show();
        logger.info("The program is running");
    }

    public static void main(String[] args) {
        try {
            DB.connectDB();

            launch(args);

        } catch (Exception e) {
            //LOGOLNI!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        } finally {
            DB.disconnectDB();
        }
    }
}
