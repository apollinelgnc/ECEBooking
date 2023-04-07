package com.example.ecebooking;

import com.example.ecebooking.Controllers.LoginController;
import com.example.ecebooking.Models.Model;
import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.SQLException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws SQLException, ClassNotFoundException {
        Model.getInstance().getViewFactory().LoginView();
        LoginController loginControl = new LoginController();
        loginControl.SQL_Data_Login();
        loginControl.SQL_Data_Hebergements();

    }

}

