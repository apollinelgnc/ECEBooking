package com.example.ecebooking.Controllers;

import com.example.ecebooking.Models.DataCo;
import com.example.ecebooking.Models.Model;
import javafx.scene.control.*;

import java.sql.SQLException;

public class SignInController {

    public Button go_button;
    public CheckBox offres;
    public PasswordField mdp_signIn;
    public TextField id_SignIn;
    public TextField prenom_signIn;

    public void initialize() throws SQLException, ClassNotFoundException {
        DataCo dataco = new DataCo();
        dataco.Data_Creation_Login(prenom_signIn.getText(), id_SignIn.getText(),mdp_signIn.getText(),10);
        go_button.setOnAction(actionEvent -> Model.getInstance().getViewFactory().LoginView());
    }
}
