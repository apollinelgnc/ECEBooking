package com.example.ecebooking.Controllers;

import com.example.ecebooking.Controllers.Admin.Admin;
import com.example.ecebooking.Controllers.Client.Client;
import com.example.ecebooking.Controllers.Hebergements.Hebergement;
import com.example.ecebooking.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class LoginController implements Initializable {
    public ChoiceBox acc_selector = new ChoiceBox<>();
    public Label id;
    public TextField id_entree;
    public PasswordField mot_de_passe;
    public Button button_valider;
    ArrayList<Client> membres=new ArrayList<>();
    ArrayList<Hebergement>hebergements=new ArrayList<>();
    private PreparedStatement stmt;

    public void initialize(URL url, ResourceBundle resourceBundle){
    button_valider.setOnAction(actionEvent -> {
        Model.getInstance().getViewFactory().ClientView();

    }
    );
}
private void onLogin(){
    Model.getInstance().getViewFactory().ClientView();
}



}




/*  private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    public ArrayList<Client> getMembres() {
        return membres;
    }

    public void setMembres(Client client) {
        this.membres.add(client);
    }
    public void setHebergements(Hebergement hebergement) {
        this.hebergements.add(hebergement);
    }

    public void Choice( ){
        acc_selector.getItems().add("Client");
        acc_selector.getItems().add("Admin");
    }
    public void Login(ActionEvent event) throws SQLException, ClassNotFoundException {
        Choice();
        SQL_Data_Login();
        SQL_Data_Hebergements();

        Window owner = button_valider.getScene().getWindow();
        System.out.println(id_entree.getText());
        System.out.println(mot_de_passe.getText());
        boolean equal=false;
        for(int i=0; i<membres.size();i++){
            if(id_entree.getText().equals(membres.get(i).getId())){
                equal=true;
                System.out.println("hello " +membres.get(i).getId());
            }

        }
        System.out.println(equal);
        if (!equal) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "mauvais id");
            return;
        }
        if (id_entree.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Veuillez entrer votre id");
            return;
        }
        if (mot_de_passe.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Veuillez entrer votre mdp");
            return;
        }
        String id = id_entree.getText();
        String password = mot_de_passe.getText();
        showAlert(Alert.AlertType.CONFIRMATION, owner, "Login réussi !",
                "Welcome " + id);

    }*/