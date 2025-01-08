package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;

public class EmployeeMenuController {
    @FXML
    private ImageView employeeImageView;


    public void initialize() {
        load_image();
    }

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
    public void load_image(){
        try{
            URL resources=getClass().getResource("/images/warehouse.jpg");
            if(resources!=null){
                Image image = new Image(resources.toExternalForm());
                employeeImageView.setImage(image);
            }else{
                System.out.println("resources not found");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
