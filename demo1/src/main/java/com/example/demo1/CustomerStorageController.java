package com.example.demo1;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;

import data_base.DatabaseConnection;

import javafx.event.ActionEvent;
import data_base.DatabaseConnection;

    public class CustomerStorageController {
    @FXML
    private TextField Date_of_beginning;
    @FXML
    private TextField Days_in_storage;
    @FXML
    private Button Save;
    @FXML
    private Label message;
    @FXML
    private ChoiceBox<String> Storage_size;
    @FXML
    private ImageView CustomerStorageImageView;
    private final String[] storage_size = {"Small", "Medium", "Large"};

    public void initialize() {
        Storage_size.getItems().addAll(storage_size);
        Storage_size.setValue("Small");//default size
        load_image();
    }

    public void SaveOnActionButton(ActionEvent event) {
        Store();
    }


        public void Store() {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection connection = databaseConnection.getConnection();

            if (Date_of_beginning.getText().isEmpty() || Days_in_storage.getText().isEmpty()) {
                message.setText("Please fill all the fields");
                return;
            }

            String date_of_start = Date_of_beginning.getText();
            String days_in_storage = Days_in_storage.getText();
            String login=SessionData.getCurrentLogin();
            String storage_size = Storage_size.getValue();

            String checkFreeSpaceQuery = "SELECT " + storage_size.toLowerCase() + " FROM magasine_free";

            try (PreparedStatement checkStmt = connection.prepareStatement(checkFreeSpaceQuery)) {
                ResultSet rs = checkStmt.executeQuery();

                if (rs.next()) {
                    int availableSpace = rs.getInt(1);

                    if (availableSpace <= 0) {
                        message.setText("No space available for " + storage_size + " storage.");
                        return;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                message.setText("Error while checking free space.");
                return;
            }


            String insertCommodityQuery = "INSERT INTO storage(date_of_beginning, days_in_storage, login, storage_size) VALUES (?, ?, ?, ?)";

            try (PreparedStatement insertStmt = connection.prepareStatement(insertCommodityQuery)) {
                insertStmt.setString(1, date_of_start);
                insertStmt.setString(2, days_in_storage);
                insertStmt.setString(3, login);
                insertStmt.setString(4, storage_size);

                int rowsInserted = insertStmt.executeUpdate();
                if (rowsInserted > 0) {

                    String updateFreeSpaceQuery = "UPDATE magasine_free SET " + storage_size.toLowerCase() + " = " + storage_size.toLowerCase() + " - 1";

                    try (PreparedStatement updateStmt = connection.prepareStatement(updateFreeSpaceQuery)) {
                        int rowsUpdated = updateStmt.executeUpdate();

                        if (rowsUpdated > 0) {
                            message.setText("Successfully inserted into storage and updated free space.");
                        } else {
                            message.setText("Failed to update free space.");
                        }
                    }
                } else {
                    message.setText("Failed to insert into storage.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                message.setText("An error occurred while inserting data.");
            }
        }
        public void load_image(){
            try{
                URL resources=getClass().getResource("/images/place_order.png");
                if(resources!=null){
                    Image image = new Image(resources.toExternalForm());
                    CustomerStorageImageView.setImage(image);
                }else{
                    System.out.println("resources not found");
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }

}
