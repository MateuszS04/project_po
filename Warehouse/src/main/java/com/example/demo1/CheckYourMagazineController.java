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
    private Button deleate;
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

        String Login = SessionData.getCurrentLogin();
        if (Login.isEmpty()) {
            magazine_message.setText("Please enter your login");
            return;
        }

        String check = "SELECT id, date_of_beginning, days_in_storage, storage_size FROM storage WHERE login = ?";

        try (PreparedStatement pstm = connection.prepareStatement(check)) {
            pstm.setString(1, Login);
            ResultSet rs = pstm.executeQuery();

            Magazine_list.getItems().clear();
            boolean hasData = false;

            while (rs.next()) {
                hasData = true;
                int id = rs.getInt("id");
                String dateOfBeginning = rs.getString("date_of_beginning");
                String daysInStorage = rs.getString("days_in_storage");
                String storageSize = rs.getString("storage_size");

                String item = "ID: " + id + " - Date: " + dateOfBeginning + " Days: " + daysInStorage + " Size: " + storageSize;
                Magazine_list.getItems().add(item);
            }

            if (!hasData) {
                magazine_message.setText("No records for this login");
            } else {
                magazine_message.setText("Your magazine has been checked for " + Login + " and displayed below");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            magazine_message.setText("Something went wrong with checking your login");
        }
    }

    public void deleteButtonOnAction(ActionEvent event) {
        deleteSelectedMagazine();
    }

    public void deleteSelectedMagazine() {

        String selectedItem = Magazine_list.getSelectionModel().getSelectedItem();

        if (selectedItem == null) {
            magazine_message.setText("Please select an item to delete");
            return;
        }

        try {
            String[] parts = selectedItem.split(" ");
            int id = Integer.parseInt(parts[1]);
            String storageSize = parts[parts.length - 1].toLowerCase();
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection connection = databaseConnection.getConnection();


            String deleteQuery = "DELETE FROM storage WHERE id = ?";

            try (PreparedStatement pstm = connection.prepareStatement(deleteQuery)) {
                pstm.setInt(1, id);

                int rowsAffected = pstm.executeUpdate();

                if (rowsAffected > 0) {
                    magazine_message.setText("Item deleted successfully");
                    updateFreeSpace(connection, storageSize);
                    Magazine_list.getItems().remove(selectedItem);
                } else {
                    magazine_message.setText("Failed to delete the item. Please try again.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                magazine_message.setText("An error occurred while deleting the item.");
            }
        } catch (Exception e) {
            magazine_message.setText("Invalid item format or unexpected error.");
        }
    }
    private void updateFreeSpace(Connection connection, String size) {
        String updateFreeSpaceQuery = "UPDATE magasine_free SET " + size.toLowerCase() + " = " + size.toLowerCase() + " +1";


        try (PreparedStatement updateStmt = connection.prepareStatement(updateFreeSpaceQuery)) {
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
