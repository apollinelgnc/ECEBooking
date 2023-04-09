package com.example.ecebooking.Controllers.Admin;

import com.example.ecebooking.Models.Model;
import javafx.scene.control.Button;

public class AdminController {
    public Button log_out_button;
    public Button menu_button;
    public void initialize(){
        //log_out_button.setOnAction(actionEvent -> Model.getInstance().getViewFactory().SignInView());
       // menu_button.setOnAction(actionEvent -> Model.getInstance().getViewFactory().InviteView());
    }
    private void onLogin(){
        Model.getInstance().getViewFactory().ClientView();
    }
}
