package com.example.ecebooking.Controllers;

import com.example.ecebooking.Models.Model;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class SignInController {

    public Button go_button;
    public CheckBox offres;
    public PasswordField mdp_signIn;
    public TextArea id_SignIn;
    public TextArea prenom_signIn;
    public TextArea nom_signIn;

    public void initialize() {
        go_button.setOnAction(actionEvent -> Model.getInstance().getViewFactory().ClientView());
    }
}
