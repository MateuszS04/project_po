package com.example.demo1;

import data_base.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChangePasswordController {
    public TextField login_field;
    public Button Change;
    public Label password_message;
    public PasswordField passwordField_2;
    public PasswordField passwordField_1;
    public Label signup_message;

    public void ChangeButtonOnAction(ActionEvent actionEvent) {
        if (passwordField_1.getText().equals(passwordField_2.getText())) {
            password_message.setText("Password match");
            if(SessionData.getAdmin()) {
                changepassword_employee();
            } else {
                changepassword();
            }
        } else {
            password_message.setText("Passwords do not match");
        }
    }

    public void changepassword_employee() {
        DatabaseConnection connect = new DatabaseConnection();
        Connection con = connect.getConnection();
        PreparedStatement checkStmt = null;
        PreparedStatement updateStmt = null;
        ResultSet resultSet = null;

        if (con == null) {
            signup_message.setText("Failed to connect to database");
            return;
        }

        String newPassword1 = passwordField_1.getText();
        String newPassword2 = passwordField_2.getText();

        if (newPassword1.isEmpty() || newPassword2.isEmpty()) {
            signup_message.setText("Please fill all fields");
            return;
        }

        if (!newPassword1.equals(newPassword2)) {
            signup_message.setText("Passwords do not match");
            return;
        }

        String currentLogin = SessionData.getCurrentLogin();
        String checkLoginQuery = "SELECT COUNT(*) as total FROM login_data_employee WHERE login_employee=?";
        String updatePasswordQuery = "UPDATE login_data_employee SET password_employee=? WHERE login_employee=?";

        try {
            checkStmt = con.prepareStatement(checkLoginQuery);
            checkStmt.setString(1, currentLogin);
            resultSet = checkStmt.executeQuery();

            if (resultSet.next() && resultSet.getInt("total") == 0) {
                signup_message.setText("Login not found. Please check your login.");
                return;
            }

            updateStmt = con.prepareStatement(updatePasswordQuery);
            updateStmt.setString(1, newPassword1);
            updateStmt.setString(2, SessionData.getCurrentLogin());
            int rowsAffected = updateStmt.executeUpdate();

            if (rowsAffected > 0) {
                signup_message.setText("Password updated successfully");
            } else {
                signup_message.setText("Failed to update password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            signup_message.setText("Database error");
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (checkStmt != null) checkStmt.close();
                if (updateStmt != null) updateStmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void changepassword() {
        DatabaseConnection connect = new DatabaseConnection();
        Connection con = connect.getConnection();
        PreparedStatement checkStmt = null;
        PreparedStatement updateStmt = null;
        ResultSet resultSet = null;

        if (con == null) {
            signup_message.setText("Failed to connect to database");
            return;
        }

        String newPassword1 = passwordField_1.getText();
        String newPassword2 = passwordField_2.getText();

        if (newPassword1.isEmpty() || newPassword2.isEmpty()) {
            signup_message.setText("Please fill all fields");
            return;
        }

        if (!newPassword1.equals(newPassword2)) {
            signup_message.setText("Passwords do not match");
            return;
        }

        String currentLogin = SessionData.getCurrentLogin();
        String checkLoginQuery = "SELECT COUNT(*) as total FROM login_data WHERE login=?";
        String updatePasswordQuery = "UPDATE login_data SET password=? WHERE login=?";

        try {
            checkStmt = con.prepareStatement(checkLoginQuery);
            checkStmt.setString(1, currentLogin);
            resultSet = checkStmt.executeQuery();

            if (resultSet.next() && resultSet.getInt("total") == 0) {
                signup_message.setText("Login not found. Please check your login.");
                return;
            }

            updateStmt = con.prepareStatement(updatePasswordQuery);
            updateStmt.setString(1, newPassword1);
            updateStmt.setString(2, SessionData.getCurrentLogin());
            int rowsAffected = updateStmt.executeUpdate();

            if (rowsAffected > 0) {
                signup_message.setText("Password updated successfully");
            } else {
                signup_message.setText("Failed to update password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            signup_message.setText("Database error");
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (checkStmt != null) checkStmt.close();
                if (updateStmt != null) updateStmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
