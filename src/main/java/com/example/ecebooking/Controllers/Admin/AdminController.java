package com.example.ecebooking.Controllers.Admin;

import com.example.ecebooking.Models.Model;
import javafx.scene.control.Button;

public class AdminController {
    public Button log_out_button;
    public Button menu_button;
    public Button hotels;
    public Button reservations;
    public Button stats;
    public Button promo;

    public void initialize(){
        log_out_button.setOnAction(actionEvent -> Model.getInstance().getViewFactory().closeStage());
        menu_button.setOnAction(actionEvent -> Model.getInstance().getViewFactory().AdminView());
    }
}
