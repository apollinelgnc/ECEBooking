package com.example.ecebooking.Views;

import com.example.ecebooking.Controllers.Admin.AdminController;
import com.example.ecebooking.Controllers.Client.*;
import com.example.ecebooking.Controllers.Hebergements.Hebergement;
import com.example.ecebooking.Controllers.Hebergements.Un_HebergementController;
import com.example.ecebooking.Controllers.SignInController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewFactory {
    Invite invite;
    private Stage stage;
    public ViewFactory(){}

    public void LoginView(){
        FXMLLoader load=new FXMLLoader(getClass().getResource("/Fxml/Login.fxml"));
        createStage(load);
    }
    public void PayementView(){
        FXMLLoader load=new FXMLLoader(getClass().getResource("/Fxml/Hebergement/PayementPage.fxml"));
        createStage(load);
    }
    public void SignInView(){
        FXMLLoader load=new FXMLLoader(getClass().getResource("/Fxml/SignIn.fxml"));
        SignInController signInController=new SignInController();
        load.setController(signInController);
        closeStage();
        createStage(load);
    }

    public void ClientView(int i){
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/Fxml/Client/MenuClient.Fxml"));
        MenuControllerClient menuControllerClient=new MenuControllerClient(i);
        loader.setController(menuControllerClient);
        closeStage();
        createStage(loader);
    }
    public void ResaView(Hebergement hotel){
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/Fxml/Hebergement/Hebergement.Fxml"));
        Un_HebergementController unHebergementController=new Un_HebergementController(hotel);
        loader.setController(unHebergementController);
        createStage(loader);
        unHebergementController.setHotel();
    }
    public void AdminView(){
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/Fxml/Admin/Admin.Fxml"));
        AdminController adminController=new AdminController();
        loader.setController(adminController);
        closeStage();
        createStage(loader);
    }
    public void InviteView(){
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/Fxml/Client/MenuInvite.Fxml"));
        MenuControllerInvite menuControllerInvite=new MenuControllerInvite();
        loader.setController(menuControllerInvite);
        closeStage();
        createStage(loader);
    }
    public Object load(String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent root = loader.load();
        return loader.getController();
    }

    private void createStage(FXMLLoader loader) {
        Scene scene=null;
        try{
            scene=new Scene(loader.load());
        }catch (Exception e){
            e.printStackTrace();
        }
        stage=new Stage();
        stage.setFullScreen(true);
        stage.setScene(scene);
        stage.setTitle("ECEBooking");
        stage.show();
    }
    public void closeStage(){
        System.out.println("close");
        stage.close();
    }
}
