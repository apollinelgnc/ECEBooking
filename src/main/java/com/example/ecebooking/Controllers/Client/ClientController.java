package com.example.ecebooking.Controllers.Client;

import com.example.ecebooking.Models.Model;
import javafx.scene.control.Button;

public class ClientController {
    public Button menu_button=new Button();
    public Button stats_button=new Button();
    public Button profile_button=new Button();
    public Button reservation_button=new Button();
    public Button log_out_button=new Button();

    public void initialize(){
        reservation_button.setOnAction(actionEvent -> Model.getInstance().getViewFactory().closeStage());
        menu_button.setOnAction(actionEvent -> Model.getInstance().getViewFactory().ClientView());
        profile_button.setOnAction(actionEvent -> Model.getInstance().getViewFactory().closeStage());
        stats_button.setOnAction(actionEvent -> Model.getInstance().getViewFactory().closeStage());
        log_out_button.setOnAction(actionEvent -> Model.getInstance().getViewFactory().closeStage());

    }
}
