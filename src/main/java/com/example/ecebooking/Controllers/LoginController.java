package com.example.ecebooking.Controllers;

import com.example.ecebooking.Controllers.Client.Membre;
import com.example.ecebooking.Controllers.Hebergements.Hebergement;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.Window;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class LoginController {
    public ChoiceBox acc_selector = new ChoiceBox<>();
    public Label id;
    public TextField id_entree;
    public PasswordField mot_de_passe;
    public Button button_valider;
    ArrayList<Membre> membres=new ArrayList<>();
    ArrayList<Hebergement>hebergements=new ArrayList<>();


    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    public ArrayList<Membre> getMembres() {
        return membres;
    }

    public void setMembres(Membre membre) {
        this.membres.add(membre);
    }
    public void setHebergements(Hebergement hebergement) {
        this.hebergements.add(hebergement);
    }

    public void Choice( ){
        acc_selector.getItems().add("Client");
        acc_selector.getItems().add("Admin");
    }
    public void Login(ActionEvent event) throws SQLException {
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

    }
    public void SQL_Data_Login()throws SQLException {
        DataBaseConnection connection = new DataBaseConnection();
        Statement stmt = connection.getConnection().createStatement();
        ResultSet res = stmt.executeQuery("SELECT * FROM login.user_client");
        while (res.next()) {
            Membre m = new Membre(res.getString("name_client"), res.getInt("User_client"), res.getString("idUser_client"), res.getString("mdptUse_client"));
            System.out.println(m.toString());
            setMembres(m);
        }
    }
    public void SQL_Data_Hebergements() throws SQLException {
        DataBaseConnection connection = new DataBaseConnection();
        Statement stmt = connection.getConnection().createStatement();
        ResultSet res = stmt.executeQuery("SELECT * FROM hebergement.hebergement");
       while (res.next()) {
            Hebergement h;
            h = new Hebergement(res.getString("nom_etablissement"), res.getString("ville"), res.getInt("nombre_chambres"), res.getInt("nombre_places"),res.getInt("prix"),res.getInt("distance_centre"),res.getInt("idhebergement"));
            System.out.println(h.toString());
            setHebergements(h);
        }
    }
}