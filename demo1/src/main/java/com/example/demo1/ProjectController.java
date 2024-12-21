package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.*;

import data_base.DatabaseConnection;

import javafx.event.ActionEvent;


public class ProjectController {
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
        if (login_field.getText().isBlank() == false && password_field.getText().isBlank() == false) {
            validateLogin();
        } else {
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

        String verify = "select count(*) as total from login_data where login=? and password=?";

        try {
            PreparedStatement st = con.prepareStatement(verify);
            st.setString(1, login_field.getText());
            st.setString(2, password_field.getText());
            ResultSet rs = st.executeQuery();
            if (rs.next() && rs.getInt("total") == 1) {
                login_message.setText("Login successful");
                Stage currentstage = (Stage) login_button.getScene().getWindow();
                currentstage.close();
                warehouse();

            } else {
                login_message.setText("Login failed");
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
            login_message.setText("An error occurred while trying to login ");
        } finally {
            Connection.closeConnection();
        }


    }


    public void createAccount() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ProjectApplication.class.getResource("register.fxml"));
            Stage register_stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            register_stage.setScene(scene);
            register_stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void warehouse() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ProjectApplication.class.getResource("warehous.fxml"));
            Stage warehous_stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            warehous_stage.setScene(scene);
            warehous_stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


