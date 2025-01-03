package com.example.demo1;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;

import javafx.event.ActionEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import data_base.DatabaseConnection;
import com.example.demo1.ProjectController;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


import javax.xml.transform.Result;

public class RegisterController {
    @FXML
    private Button btn_Register;
    @FXML
    private Label signup_message;
    @FXML
    private PasswordField passwordField_1;
    @FXML
    private TextField passwordField_2;
    @FXML
    private Label password_message;
    @FXML
    private TextField login_field;
    @FXML
    private Label login_message;
    @FXML
    private ImageView warehouseicon;

    public void initialize() {
        load_image();
    }

    @FXML
    public void btn_RegisterButtonOnAction(ActionEvent actionEvent) {
        if (passwordField_1.getText().equals(passwordField_2.getText())) {
            password_message.setText("password match");
            registerUser();
        } else {
            password_message.setText("password not match");
        }

    }

    public void registerUser() {
        DatabaseConnection connect = new DatabaseConnection();
        Connection con = connect.getConnection();

        if (con == null) {
            login_message.setText("Failed to connect to database");
            return;
        }
        if (login_field.getText().isEmpty() || passwordField_1.getText().isEmpty() || passwordField_2.getText().isEmpty()) {
            login_message.setText("Please fill all fields");
            return;
        }

        String login_text = login_field.getText();
        String password_text = passwordField_1.getText();

        String check_login = "select count(*) as total from login_data where login=?";
        String insertQuery = "Insert into login_data(login,password) values(?,?)";

        PreparedStatement pst = null;
        PreparedStatement prpst = null;
        ResultSet log = null;

        try {
            pst = con.prepareStatement(check_login);
            pst.setString(1, login_text);
            log = pst.executeQuery();

            if (log.next() && log.getInt(1) >= 1) {
                login_message.setText("Login already exists, try again.");
                return;
            }

            prpst = con.prepareStatement(insertQuery);
            prpst.setString(1, login_text);
            prpst.setString(2, password_text);

            int rowsAffected = prpst.executeUpdate();
            if (rowsAffected > 0) {
                login_message.setText("Registration successful!");
                Stage currentStage = (Stage) btn_Register.getScene().getWindow();
                currentStage.close();
            } else {
                login_message.setText("Registration failed.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            login_message.setText("Error occurred during registration.");
        } finally {
            try {
                if (log != null) log.close();
                if (pst != null) pst.close();
                if (prpst != null) prpst.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void load_image(){
        try{
            URL resources=getClass().getResource("/images/warehouse_icon.png");
            if(resources!=null){
                Image image = new Image(resources.toExternalForm());
                warehouseicon.setImage(image);
            }else{
                System.out.println("resources not found");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}



