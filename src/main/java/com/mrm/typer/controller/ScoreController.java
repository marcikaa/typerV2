/*
* Copyright 2018 Márton Szabó.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.mrm.typer.controller;

import com.mrm.typer.model.DB.DataBase;
import com.mrm.typer.model.Result;
import com.mrm.typer.model.entity.JPAEntity;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;

public class ScoreController implements Initializable {
    
    @FXML
    private AnchorPane anchorPane1;
    @FXML
    private Button button_back;
    @FXML
    private TableView table;
    
    private static final DataBase DB = DataBase.getDbPeldany();
    private List<JPAEntity> entities = new LinkedList();
    private ObservableList<String> data = FXCollections.observableArrayList();
    
    @FXML
    public void backToMain(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/MainMenu.fxml"));
        Parent root = null;
        try {
            root = (Parent) loader.load();
            anchorPane1.getChildren().clear();
            anchorPane1.getChildren().add(root);
        } catch (IOException e) {
            //TODO: LOG!
            e.printStackTrace();
        }
    }
    
    public void addResult(String name, String score) {
    
    }
    
    private ObservableList<Result> results =
            FXCollections.observableArrayList(
                    
            );
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        /*TableColumn<JPAEntity,String> nameCol = new TableColumn<JPAEntity, String>("Name");
        nameCol.setMinWidth(325);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("playerName"));
        
        TableColumn<JPAEntity,Integer> scoreCol = new TableColumn<JPAEntity, Integer>("Score");
        scoreCol.setMinWidth(325);
        scoreCol.setCellValueFactory(new PropertyValueFactory<>("score"));*/
        
        
        results.add(new Result("MRM","1"));
        
        try {
            entities = DB.getAllOrderedByScore();
        } catch (Exception ex) {
            Logger.getLogger(ScoreController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (entities != null) {
            for (JPAEntity e : entities) {
//                data.add(e.getPlayerName());
//                data.add(e.getScore().toString());
                results.add(new Result(e.getPlayerName(),e.getScore().toString()));
            }
            
        }
        
        TableColumn nameCol  = new TableColumn("Name");
        nameCol.setMinWidth(320);
        nameCol.setMaxWidth(320);
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());        
        nameCol.setCellValueFactory(new PropertyValueFactory<Result, String>("name"));
        
        TableColumn scoreCol  = new TableColumn("Score");
        scoreCol.setMinWidth(320);
        scoreCol.setMaxWidth(320);
        scoreCol.setCellFactory(TextFieldTableCell.forTableColumn());
        scoreCol.setCellValueFactory(new PropertyValueFactory<Result, Integer>("score"));
        table.getColumns().addAll(nameCol,scoreCol);
        table.setItems(results);
    }
    
    
}
