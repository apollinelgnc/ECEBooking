package com.example.ecebooking;

import com.example.ecebooking.Controllers.DataBaseConnection;
import com.example.ecebooking.Controllers.LoginController;
import com.sun.javafx.stage.StagePeerListener;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("/Fxml/Login.fxml"));
        LoginController log=new LoginController();
        log.Choice();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setFullScreen(true);
        stage.setScene(scene);
        stage.show();


    }
}
