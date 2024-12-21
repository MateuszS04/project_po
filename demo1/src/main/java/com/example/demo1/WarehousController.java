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

public class WarehousController {
    @FXML
    private Button place_an_order;
    @FXML
    private Button check_the_supply;
    @FXML
    private Button check_your_orders;

    public void place_an_orderButtonOnAction(ActionEvent event) {
        place_the_order();
        Stage stage = (Stage) place_an_order.getScene().getWindow();
        stage.close();
    }
    public void check_the_supplyButtonOnAction(ActionEvent event) {
        Check_the_supply();
        Stage stage = (Stage) check_the_supply.getScene().getWindow();
        stage.close();
    }
    public void check_your_ordersButtonOnAction(ActionEvent event) {
        Check_your_orders();
        Stage stage = (Stage) check_your_orders.getScene().getWindow();
        stage.close();

    }
    public void place_the_order(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ProjectApplication.class.getResource("place_order.fxml"));
            Stage place_order_stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            place_order_stage.setScene(scene);
            place_order_stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void  Check_the_supply(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(ProjectApplication.class.getResource("check_the_supply.fxml"));
            Stage check_the_supply_stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            check_the_supply_stage.setScene(scene);
            check_the_supply_stage.show();
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
    public void Check_your_orders(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(ProjectApplication.class.getResource("check_your_orders.fxml"));
            Stage check_your_orders_stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            check_your_orders_stage.setScene(scene);
            check_your_orders_stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
