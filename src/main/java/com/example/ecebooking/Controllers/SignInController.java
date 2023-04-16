package com.example.ecebooking.Controllers;

import com.example.ecebooking.Models.DataCo;
import com.example.ecebooking.Models.Model;
import javafx.fxml.FXML;
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
        go_button.setOnAction(actionEvent -> {
            try {
                dataco.Data_Creation_Login(prenom_signIn.getText(), id_SignIn.getText(), mdp_signIn.getText(), 15);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            if (!prenom_signIn.getText().isEmpty() && !id_SignIn.getText().isEmpty() && !mdp_signIn.getText().isEmpty()) {
                Model.getInstance().getViewFactory().closeStage();
                Model.getInstance().getViewFactory().LoginView();
            }
        });
    }
}
