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
                    deleteMagazine(login);
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
    public void deleteMagazine(String login) {

        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();

        String countQuery = "SELECT storage_size, COUNT(*) AS count FROM storage WHERE login = ? GROUP BY storage_size";
        String deleteQuery = "DELETE FROM storage WHERE login = ?";

        int smallCount = 0;
        int mediumCount = 0;
        int largeCount = 0;

        try (PreparedStatement countStmt = connection.prepareStatement(countQuery);
             PreparedStatement deleteStmt = connection.prepareStatement(deleteQuery)) {

            countStmt.setString(1, login);
            ResultSet rs = countStmt.executeQuery();
            while (rs.next()) {
                String size = rs.getString("storage_size").toLowerCase();
                int count = rs.getInt("count");

                switch (size) {
                    case "small":
                        smallCount = count;
                        break;
                    case "medium":
                        mediumCount = count;
                        break;
                    case "large":
                        largeCount = count;
                        break;
                }
            }

            deleteStmt.setString(1, login);
            int rowsAffected = deleteStmt.executeUpdate();

            if (rowsAffected > 0) {
                Magazine_list.getItems().clear();
                updateFreeSpace(connection, smallCount, mediumCount, largeCount);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            magazine_message.setText("An error occurred while deleting magazines.");
        }
    }
    private void updateFreeSpace(Connection connection, int smallCount, int mediumCount, int largeCount) {
        String updateFreeSpaceQuery = "UPDATE magasine_free SET small = small + ?, medium = medium + ?, large = large + ?";

        try (PreparedStatement updateStmt = connection.prepareStatement(updateFreeSpaceQuery)) {

            updateStmt.setInt(1, smallCount);
            updateStmt.setInt(2, mediumCount);
            updateStmt.setInt(3, largeCount);

            int rowsAffected = updateStmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Free space updated successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            magazine_message.setText("An error occurred while updating free space.");
        }
    }

}
