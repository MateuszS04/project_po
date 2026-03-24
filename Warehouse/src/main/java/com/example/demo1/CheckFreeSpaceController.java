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

public class CheckFreeSpaceController {
    @FXML
    private Button check;
    @FXML
    private Label magazine_message;
    @FXML
    private ListView<String> Magazine_list;

    public void checkButtonOnAction(ActionEvent event) {
        checkMagazineFreeSpace();
    }

    public void checkMagazineFreeSpace() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();


        String check = "SELECT small, medium, large FROM magasine_free";

        try (PreparedStatement pstm = connection.prepareStatement(check)) {
            ResultSet rs = pstm.executeQuery();

            Magazine_list.getItems().clear();
            boolean hasData = false;

            while (rs.next()) {
                hasData = true;
                int small = rs.getInt("small");
                int medium = rs.getInt("medium");
                int large = rs.getInt("large");

                String item = "Small: " + small + " | Medium: " + medium + " | Large: " + large;
                Magazine_list.getItems().add(item);
            }

            if (!hasData) {
                magazine_message.setText("No records found for free space.");
            } else {
                magazine_message.setText("Free space details have been displayed.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            magazine_message.setText("Something went wrong while checking free space.");
        }
    }
}
