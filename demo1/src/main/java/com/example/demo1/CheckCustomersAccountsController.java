package com.example.demo1;

import data_base.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckCustomersAccountsController {
    @FXML
    private Button delete;
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

        String check = "SELECT login, password FROM login_data";

        try (PreparedStatement pstm = connection.prepareStatement(check)) {
            ResultSet rs = pstm.executeQuery();

            Magazine_list.getItems().clear();
            boolean hasData = false;

            while (rs.next()) {
                hasData = true;
                String login = rs.getString("login");
                String password = rs.getString("password");

                String item = "Login: " + login + " - Password: " + password;
                Magazine_list.getItems().add(item);
            }

            if (!hasData) {
                magazine_message.setText("No customer accounts found.");
            } else {
                magazine_message.setText("Customers list has been displayed.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            magazine_message.setText("Something went wrong while checking the customer accounts.");
        }
    }

    public void deleteButtonOnAction(ActionEvent event) {
        deleteSelectedCustomer();
    }

    public void deleteSelectedCustomer() {

        String selectedItem = Magazine_list.getSelectionModel().getSelectedItem();

        if (selectedItem == null) {
            magazine_message.setText("Please select a customer to delete.");
            return;
        }

        try {

            String[] parts = selectedItem.split(" ");
            String login = parts[1];

            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection connection = databaseConnection.getConnection();

            String deleteQuery = "DELETE FROM login_data WHERE login = ?";

            try (PreparedStatement pstm = connection.prepareStatement(deleteQuery)) {

                pstm.setString(1, login);

                int rowsAffected = pstm.executeUpdate();

                if (rowsAffected > 0) {
                    magazine_message.setText("Customer deleted successfully.");

                    Magazine_list.getItems().remove(selectedItem);
                } else {
                    magazine_message.setText("Failed to delete the customer. Please try again.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                magazine_message.setText("An error occurred while deleting the customer.");
            }
        } catch (Exception e) {
            magazine_message.setText("Invalid item format or unexpected error.");
        }
    }
}
