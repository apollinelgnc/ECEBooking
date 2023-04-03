package com.example.ecebooking.Controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Window;


public class LoginController {
    public ChoiceBox acc_selector;
    public Label id;
    public TextField id_entree;
    public PasswordField mot_de_passe;
    public Button button_valider;

    public void Login(ActionEvent event) throws SQLException {

        Window owner = button_valider.getScene().getWindow();
        System.out.println(id_entree.getText());
        System.out.println(mot_de_passe.getText());
        if (id_entree.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Veuillez entrer votre id");
            return;
        }
        if (mot_de_passe.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Veuillez entrer votre mdp");
            return;
        }
        String id = id_entree.getText();
        String password = mot_de_passe.getText();
        showAlert(Alert.AlertType.CONFIRMATION, owner, "Login réussi !",
                "Welcome " + id);
       }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
    /*
    public void connectButton (ActionEvent event){
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connectDB=connectNow.getConnection();
        String connectQuery="SELECT idUser_Client FROM login.user_client";
        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(connectQuery);
            while(queryOutput.next()){
                id.setText(queryOutput.getString("idUser_Client"));

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }*/
}
