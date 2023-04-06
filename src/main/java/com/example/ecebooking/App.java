package com.example.ecebooking;

import com.example.ecebooking.Models.Model;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        Model.getInstance().getViewFactory().LoginView();
    }
}
