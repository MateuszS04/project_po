package com.example.demo1;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.*;

import data_base.DatabaseConnection;

import javafx.event.ActionEvent;
import data_base.DatabaseConnection;

public class CustomerStorageController {
    @FXML
private TextField Date_of_beginning;
    @FXML
private TextField Days_in_storage;
    @FXML
private TextField Login;
    @FXML
    private Button Save;
    @FXML
    private Label message;
    @FXML
    private ChoiceBox<String> Storage_size;

    private final String[] storage_size = {"Small", "Medium", "Large"};

    public void initialize() {
        Storage_size.getItems().addAll(storage_size);
        Storage_size.setValue("Small");                         //default size
    }

    public void SaveOnActionButton(ActionEvent event) {
        Store();
    }

    public void Store(){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();

        if(Date_of_beginning.getText().isEmpty()||Days_in_storage.getText().isEmpty()||Login.getText().isEmpty()){
            message.setText("Please fill all the fields");
        }



        String date_of_start=Date_of_beginning.getText();
        String days_in_storage=Days_in_storage.getText();
        String login=Login.getText();
        String storage_size=Storage_size.getValue();

        String insert_commodity= "Insert into storage(date_of_beginning,days_in_storage,login,storage_size) values(?,?,?,?)";

        try(PreparedStatement prptstm= connection.prepareStatement(insert_commodity))
        {
          prptstm.setString(1,date_of_start);
          prptstm.setString(2,days_in_storage);
          prptstm.setString(3,login);
          prptstm.setString(4,storage_size);

          int rowsInserted=prptstm.executeUpdate();
          if(rowsInserted>0){
              message.setText("Successfully inserted into storage");
          }else{
              message.setText("Something went wrong");
          }
        } catch (SQLException e) {
            e.printStackTrace();
            message.setText("An error occurred");
        }
    }

}
