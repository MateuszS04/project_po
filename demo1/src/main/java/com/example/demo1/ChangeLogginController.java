package com.example.demo1;

import data_base.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChangeLogginController {

    public TextField login_field;
    public Button Change;
    public Label password_message;
    public PasswordField passwordField_2;
    public PasswordField passwordField_1;
    public Label login_message;

    public void ChangeButtonOnAction(ActionEvent actionEvent) {
        if (passwordField_1.getText().equals(passwordField_2.getText())) {
            password_message.setText("password match");
            if(SessionData.getAdmin()==true){
                changeloggin_employee();
            }else {
                changeloggin();
            }
        } else {
            password_message.setText("logins not match");
        }
    }

    public void changeloggin() {
        DatabaseConnection connect = new DatabaseConnection();
        Connection con = connect.getConnection();

        if (con == null) {
            login_message.setText("Failed to connect to database");
            return;
        }
        if (passwordField_1.getText().isEmpty() || passwordField_2.getText().isEmpty()) {
            login_message.setText("Please fill all fields");
            return;
        }



        String newLogin = passwordField_1.getText();

        String checkLoginQuery = "SELECT COUNT(*) as total FROM login_data WHERE login=?";

        String updateQuery = "UPDATE login_data SET login=? WHERE login=?";

        try {

            PreparedStatement checkStmt = con.prepareStatement(checkLoginQuery);
            checkStmt.setString(1, newLogin);
            ResultSet resultSet = checkStmt.executeQuery();

            if (resultSet.next() && resultSet.getInt("total") >= 1) {
                login_message.setText("Login already exists. Try again.");
                return;
            }

            PreparedStatement updateStmt = con.prepareStatement(updateQuery);
            updateStmt.setString(1, newLogin);
            updateStmt.setString(2, SessionData.getCurrentLogin());
            int rowsAffected = updateStmt.executeUpdate();

            if (rowsAffected > 0) {
                login_message.setText("Update successful");
                SessionData.setCurrentLogin(newLogin);
                Stage currentStage = (Stage) Change.getScene().getWindow();
                currentStage.close();
            } else {
                login_message.setText("Update failed: no user with current login found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            login_message.setText("Database error");
        }
    }

    public void changeloggin_employee() {
        DatabaseConnection connect = new DatabaseConnection();
        Connection con = connect.getConnection();

        if (con == null) {
            login_message.setText("Failed to connect to database");
            return;
        }

        if (passwordField_1.getText().isEmpty() || passwordField_2.getText().isEmpty()) {
            login_message.setText("Please fill all fields");
            return;
        }

        String newLogin = passwordField_1.getText();
        String checkLoginQuery = "SELECT COUNT(*) as total FROM login_data_employee WHERE login_employee=?";
        String updateQuery = "UPDATE login_data_employee SET login_employee=? WHERE login_employee=?";

        try {

            PreparedStatement checkStmt = con.prepareStatement(checkLoginQuery);
            checkStmt.setString(1, newLogin);
            ResultSet resultSet = checkStmt.executeQuery();

            if (resultSet.next() && resultSet.getInt("total") >= 1) {
                login_message.setText("Login already exists. Try again.");
                return;
            }

            PreparedStatement updateStmt = con.prepareStatement(updateQuery);
            updateStmt.setString(1, newLogin);
            updateStmt.setString(2, SessionData.getCurrentLogin());
            int rowsAffected = updateStmt.executeUpdate();

            if (rowsAffected > 0) {
                login_message.setText("Update successful");
                SessionData.setCurrentLogin(newLogin);
                Stage currentStage = (Stage) Change.getScene().getWindow();
                currentStage.close();
            } else {
                login_message.setText("Update failed: no user with current login found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            login_message.setText("Database error");
        }
    }


}



