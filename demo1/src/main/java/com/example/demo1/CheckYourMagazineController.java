package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import data_base.DatabaseConnection;
import com.example.demo1.ProjectController;
import javafx.stage.Stage;


import javax.xml.transform.Result;
public class CheckYourMagazineController {

    @FXML
    private TextField login;
    @FXML
    private Button check;
    @FXML
    private Label magazine_message;
    @FXML
    private ListView<String> Magazine_list;

    public void checkButtonOnAction(ActionEvent event) {
        check_magaznie();
    }

    public void check_magaznie() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();

        String Login = login.getText();
        if(Login.isEmpty()) {
            magazine_message.setText("Please enter your login");
            return;
        }

        String check="SELECT date_of_beginning, days_in_storage, storage_size FROM storage WHERE login = ?";

        try(PreparedStatement pstm=connection.prepareStatement(check)){
            pstm.setString(1,Login);
            ResultSet rs=pstm.executeQuery();

            Magazine_list.getItems().clear();

            boolean has_data=false;
            while(rs.next()){
                has_data=true;
                String DateOfBeginning = rs.getString("date_of_beginning");
                String DaysInStorage = rs.getString("days_in_storage");
                String StorageSize = rs.getString("storage_size");

                String item= "Date: "+DateOfBeginning+"Days last: "+DaysInStorage+"Size: "+StorageSize;

                Magazine_list.getItems().add(item);

            }
            if(!has_data){
                magazine_message.setText("No records for this login");
            }else{
                magazine_message.setText("Your magazine has been checked for "+Login+" and displayed below");
            }

        }catch (SQLException e) {
            e.printStackTrace();
            magazine_message.setText("Something went wrong witch checking your login");
        }

    }
}
