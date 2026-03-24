package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EmployeeMenuController {
    public void place_an_orderButtonOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ProjectApplication.class.getResource("place_order_employee.fxml"));
            Stage register_stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            register_stage.setScene(scene);
            register_stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void check_free_spaceButtonOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ProjectApplication.class.getResource("check_free_space.fxml"));
            Stage register_stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            register_stage.setScene(scene);
            register_stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void check_ordersButtonOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ProjectApplication.class.getResource("check_orders_employee.fxml"));
            Stage register_stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            register_stage.setScene(scene);
            register_stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void change_loginButtonOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ProjectApplication.class.getResource("changelogin.fxml"));
            Stage register_stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            register_stage.setScene(scene);
            register_stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void check_clients_accountsButtonOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ProjectApplication.class.getResource("check_customers_account.fxml"));
            Stage register_stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            register_stage.setScene(scene);
            register_stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void chage_passwordButtonOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ProjectApplication.class.getResource("changepassword.fxml"));
            Stage register_stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            register_stage.setScene(scene);
            register_stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
