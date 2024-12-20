package com.example.demo1;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;

import javafx.event.ActionEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import data_base.DatabaseConnection;

public class RegisterController {
    @FXML
    private Button btn_Register;
    @FXML
    private Label signup_message;
    @FXML
    private PasswordField passwordField_1;
    @FXML
    private TextField passwordField_2;
    @FXML
    private Label password_message;
    @FXML
    private TextField login_field;
    @FXML
    private Label login_message;

    @FXML
    public void btn_RegisterButtonOnAction(ActionEvent actionEvent) {
        if (passwordField_1.getText().equals(passwordField_2.getText())) {
            password_message.setText("password match");
            registerUser();
        } else {
            password_message.setText("password not match");
        }

    }
    public void registerUser(){
        DatabaseConnection connect = new DatabaseConnection();
        Connection con = connect.getConnection();

        if(con==null){
            login_message.setText("Failed to connect to database");
            return;
        }

        String login_text=login_field.getText();
        String password_text=passwordField_1.getText();

        String insertQuery= "Insert into login_data(login,password) values(?,?)";


        try{
            PreparedStatement prpst = con.prepareStatement(insertQuery);
            prpst.setString(1, login_text);
            prpst.setString(2, password_text);

            int rowsAffected=prpst.executeUpdate();
            if (rowsAffected>0) {
                login_message.setText("success");
            } else {
                login_message.setText("fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
            login_message.setText("fail");
        }


    }

}
