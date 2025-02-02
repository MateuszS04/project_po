package com.example.demo1;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;

import javafx.event.ActionEvent;

public class WarehousController {
    @FXML
    public Button chage_password;
    @FXML
    public Button change_login;
    @FXML
    private Button place_an_order;
    @FXML
    private Button check_the_supply;
    @FXML
    private Button check_your_orders;
    @FXML
    private ImageView warehouseImageView;

    public void initialize() {
        load_image();
    }

    public void place_an_orderButtonOnAction(ActionEvent event) {
        place_the_order();
        Stage stage = (Stage) place_an_order.getScene().getWindow();

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
    public void check_your_ordersButtonOnAction(ActionEvent event) {
        Check_your_orders();
        Stage stage = (Stage) check_your_orders.getScene().getWindow();


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

    public void change_loginButtonOnAction(ActionEvent actionEvent) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(ProjectApplication.class.getResource("changelogin.fxml"));
            Stage check_your_orders_stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            check_your_orders_stage.setScene(scene);
            check_your_orders_stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void chage_passwordButtonOnAction(ActionEvent actionEvent) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(ProjectApplication.class.getResource("changepassword.fxml"));
            Stage check_your_orders_stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            check_your_orders_stage.setScene(scene);
            check_your_orders_stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void load_image(){
        try{
            URL resources=getClass().getResource("/images/warehouse.jpg");
            if(resources!=null){
                Image image = new Image(resources.toExternalForm());
                warehouseImageView.setImage(image);
            }else{
                System.out.println("resources not found");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
