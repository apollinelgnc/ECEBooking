package com.example.ecebooking.Views;

import com.example.ecebooking.Controllers.Admin.Admin;
import com.example.ecebooking.Controllers.Admin.AdminController;
import com.example.ecebooking.Controllers.Admin.AdminControllerClient;
import com.example.ecebooking.Controllers.Admin.AdminControllerHebergements;
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
        closeStage();
        createStage(load);
    }

    public void ClientView(Client client){
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/Fxml/Client/MenuClient.Fxml"));
        MenuControllerClient menuControllerClient=new MenuControllerClient(client);
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
    public void AdminView(Admin ad) throws Exception {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/Fxml/Admin/AdminInterfaceMenu.Fxml"));
        AdminController adminController=new AdminController(ad);
        loader.setController(adminController);
        closeStage();
        createStage(loader);
    }
    public void AdminViewClient(Admin ad) throws Exception {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/Fxml/Admin/AdminInterfaceMenuClient.Fxml"));
        AdminControllerClient adminControllerClient=new AdminControllerClient(ad);
        loader.setController(adminControllerClient);
        closeStage();
        createStage(loader);
    }
    public void AdminViewHebergement(Admin ad) throws Exception {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/Fxml/Admin/AdminInterfaceMenuHebergement.Fxml"));
        AdminControllerHebergements adminControllerHebergements=new AdminControllerHebergements(ad);
        loader.setController(adminControllerHebergements);
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
