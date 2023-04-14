package com.example.ecebooking.Views;

import com.example.ecebooking.Controllers.Admin.AdminController;
import com.example.ecebooking.Controllers.Client.ClientController;
import com.example.ecebooking.Controllers.Hebergements.HebergementsController;
import com.example.ecebooking.Controllers.SignInController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewFactory {
    private Stage stage;
    public ViewFactory(){}

    public void MenuView() {
        FXMLLoader load=new FXMLLoader(getClass().getResource("/Fxml/Client/Menu.fxml"));
        createStage(load);

    }
    public Node HebergementView() throws IOException {
        FXMLLoader load=new FXMLLoader(getClass().getResource("/Fxml/Client/Hebergements.fxml"));
        return load.load();
    }
    public void LoginView(){
        FXMLLoader load=new FXMLLoader(getClass().getResource("/Fxml/Login.fxml"));
        createStage(load);
    }
    public void SignInView(){
        FXMLLoader load=new FXMLLoader(getClass().getResource("/Fxml/SignIn.fxml"));
        SignInController signInController=new SignInController();
        load.setController(signInController);
        closeStage();
        createStage(load);
    }

    public void ClientView(){
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/Fxml/Client/Client.Fxml"));
        ClientController clientController=new ClientController();
        loader.setController(clientController);
        closeStage();
        createStage(loader);
    }
    public void AdminView(){
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/Fxml/Admin/Admin.Fxml"));
        AdminController adminController=new AdminController();
        loader.setController(adminController);
        closeStage();
        createStage(loader);
    }
    public void InviteView(){
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/Fxml/Client/Invite.Fxml"));
        ClientController clientController=new ClientController();
        loader.setController(clientController);
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
        stage.setScene(scene);
        stage.setTitle("ECEBooking");
        stage.show();
    }
    public void closeStage(){
        System.out.println("close");
        stage.close();
    }
}
