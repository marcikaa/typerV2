package com.mrm.typer.controller;

import com.mrm.typer.model.DB;
import com.mrm.typer.model.Result;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ScoreController implements Initializable {
    @FXML
    AnchorPane anchorPane;
    @FXML
    TableView table;
    DB db = new DB();
    public void backToMain(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("/com/mrm/typer/view/MainMenu.fxml"));
        Parent root = null;
        try {
            root = (Parent) loader.load();
            anchorPane.getChildren().clear();
            anchorPane.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Integer score = 250;
    Result res = new Result("marcikaaaaaaa","3");

    public void addResult(String name, String score){

    }



    private ObservableList<Result> results =
            FXCollections.observableArrayList(
                    new Result("Marcikaa","30")
            );

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TableColumn nameCol  = new TableColumn("Name");
        nameCol.setMinWidth(325);
        nameCol.setMaxWidth(325);
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nameCol.setCellValueFactory(new PropertyValueFactory<Result, String>("name"));

        TableColumn scoreCol  = new TableColumn("Score");
        scoreCol.setMinWidth(325);
        scoreCol.setMaxWidth(325);
        scoreCol.setCellFactory(TextFieldTableCell.forTableColumn());
        scoreCol.setCellValueFactory(new PropertyValueFactory<Result, Integer>("score"));


        for (Result result : db.getAllResults())
        {
            results.add(result);
        }

        table.getColumns().addAll(nameCol,scoreCol);
        table.setItems(results);


    }


}
