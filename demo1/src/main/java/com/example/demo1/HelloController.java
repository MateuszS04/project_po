package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.sql.*;
import java.util.ResourceBundle;
import java.net.URL;
import data_base.DatabaseConnection;
import javafx.fxml.FXML;

import javafx.event.ActionEvent;


public class HelloController {
    @FXML
    private Button login_button;
    @FXML
    private TextField login_field;
    @FXML
    private TextField password_field;
    @FXML
    private Label login_message;
    @FXML
    private Button signup_button;

//    public void initialize(){
//
//    }
    @FXML
    public void login_buttonButtonOnAction(ActionEvent event) {
        if(login_field.getText().isBlank()==false && password_field.getText().isBlank()==false){
            validateLogin();
        }else{
            login_message.setText("Please fill out all fields");
        }
    }
    @FXML
    public void signup_buttonButtonOnAction(ActionEvent event) {
        createAccount();
    }
    public void validateLogin() {
        DatabaseConnection Connection = new DatabaseConnection();
        Connection con = Connection.getConnection();

        String verify="select count(*) as total from login_data where login=? and password=?";

        try{
            PreparedStatement st = con.prepareStatement(verify);
            st.setString(1, login_field.getText());
            st.setString(2, password_field.getText());
            ResultSet rs = st.executeQuery();
            if(rs.next()&& rs.getInt("total")==1){
                login_message.setText("Login successful");
            }else {
                login_message.setText("Login failed");
            }
            rs.close();
            st.close();
        }catch(SQLException e){
            e.printStackTrace();
            login_message.setText("An error occurred while trying to login ");
        } finally {
            Connection.closeConnection();
        }


    }


    public void createAccount(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("register.fxml"));
            Stage register_stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            register_stage.setScene(scene);
            register_stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


