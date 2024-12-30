package com.example.demo1;
import data_base.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import kotlin.text.UStringsKt;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginEmployeeController {
    public TextField login_employee_field;
    public Button login_employee_button;
    public PasswordField password_employee_field;
    public Label login_message;

    public void login_employee_buttonButtonOnAction(ActionEvent event) {
        if (!login_employee_field.getText().isBlank() && !password_employee_field.getText().isBlank()) {
            validateLogin();
        } else {
            login_message.setText("Please fill out all fields");
        }
    }

    public void validateLogin() {
        DatabaseConnection Connection = new DatabaseConnection();
        java.sql.Connection con = Connection.getConnection();

        String verify = "select count(*) as total from login_data_employee where login_employee=? and password_employee=?";

        try {
            PreparedStatement st = con.prepareStatement(verify);
            st.setString(1, login_employee_field.getText());
            st.setString(2, password_employee_field.getText());
            ResultSet rs = st.executeQuery();
            if (rs.next() && rs.getInt("total") == 1) {
                SessionData.setCurrentLogin(login_employee_field.getText());
                SessionData.setAdmin(true);
                login_message.setText("Login successful");
                Stage currentstage = (Stage) login_employee_button.getScene().getWindow();
                currentstage.close();
                employee_menu();

            } else {
                login_message.setText("Login failed");
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
            login_message.setText("An error occurred while trying to login ");
        } finally {
            Connection.closeConnection();
        }


    }

    private void employee_menu() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ProjectApplication.class.getResource("employee_menu.fxml"));
            Stage warehous_stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            warehous_stage.setScene(scene);
            warehous_stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}