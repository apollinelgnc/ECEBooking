package com.example.ecebooking.Controllers.Client;

import com.example.ecebooking.Models.Model;
import javafx.scene.control.Button;

public class InviteController {
    public Button menu_button;
    public Button log_in_button;

    public void initialize(){
        log_in_button.setOnAction(actionEvent -> Model.getInstance().getViewFactory().SignInView());
        menu_button.setOnAction(actionEvent -> Model.getInstance().getViewFactory().InviteView());
    }
    private void onLogin(){
        Model.getInstance().getViewFactory().ClientView();
    }
}
