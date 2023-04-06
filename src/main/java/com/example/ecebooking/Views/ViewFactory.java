package com.example.ecebooking.Views;

import com.example.ecebooking.Controllers.Client.ClientController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewFactory {
    // Client Views
    private AnchorPane MenuView;
    public ViewFactory(){}

    public AnchorPane getMenuView() {
        if(MenuView == null){
            try{
                MenuView=new FXMLLoader(getClass().getResource("/Fxml/Client/Menu.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return MenuView;
    }
    public void LoginView(){
        FXMLLoader load=new FXMLLoader(getClass().getResource("/Fxml/Login.fxml"));
        createStage(load);
    }
    public void ClientView(){
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/Fxml/Client/Client.Fxml"));
        ClientController clientController=new ClientController();
        loader.setController(clientController);
        createStage(loader);
    }

    private void createStage(FXMLLoader loader) {
        Scene scene=null;
        try{
            scene=new Scene(loader.load());
        }catch (Exception e){
            e.printStackTrace();
        }
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.setTitle("ECEBooking");
        stage.show();
    }
}
