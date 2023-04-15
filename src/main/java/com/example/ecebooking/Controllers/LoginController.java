package com.example.ecebooking.Controllers;

import com.example.ecebooking.Controllers.Admin.Admin;
import com.example.ecebooking.Controllers.Client.Client;
import com.example.ecebooking.Controllers.Hebergements.Hebergement;
import com.example.ecebooking.Models.DataCo;
import com.example.ecebooking.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;


public class LoginController implements Initializable {
    public ChoiceBox<String> acc_selector = new ChoiceBox<>();
    public Label id;
    public TextField id_entree;
    public PasswordField mot_de_passe;
    public Button button_valider;
    public Hyperlink connection_invite;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        acc_selector.getItems().add("Client");
        acc_selector.getItems().add("Admin");

        button_valider.setOnAction(actionEvent -> {
            String choix = acc_selector.getSelectionModel().getSelectedItem();
            if (choix != null && !id_entree.getText().isEmpty() && !mot_de_passe.getText().isEmpty()) {
                try {
                    boolean success = login_data(choix, id_entree.getText(), mot_de_passe.getText(),"");
                    if (success) {
                        if (Objects.equals(choix, "Admin"))
                            Model.getInstance().getViewFactory().AdminView();
                        else if (Objects.equals(choix, "Client"))
                            Model.getInstance().getViewFactory().ClientView();
                    } else {
                        System.out.println("error " + id.getText());

                        // afficher un message d'erreur si les identifiants sont invalides
                    }
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            } else {
                // afficher un message d'erreur si tous les champs ne sont pas remplis
            }
        });

        connection_invite.setOnAction(event -> Model.getInstance().getViewFactory().InviteView());
    }

    public boolean login_data(String choix, String utilisateur, String mot_de_passe, String prenom) throws SQLException, ClassNotFoundException {
        ArrayList<Client> COListe = new ArrayList<>();
        ArrayList<Admin> ADListe = new ArrayList<>();
        ArrayList<Hebergement> hebergementListe = new ArrayList<>();
        DataCo dataco = new DataCo();
        dataco.SQL_Data_Login(COListe);
        dataco.SQL_Data_Admin(ADListe);

        if (Objects.equals(choix, "Admin")) {
            for (Admin ad : ADListe) {
                if (id.equals(ad.getIdA()) && mot_de_passe.equals(ad.getMdpA())) {
                    Admin ConnexionAdmin = new Admin(ad.getNomA(), utilisateur, mot_de_passe, ad.getNumeroA());
                    return true;
                }
            }
        } else if (Objects.equals(choix, "Client")) {
            for (Client client : COListe) {
                if (id.equals(client.getUtilisateur()) && mot_de_passe.equals(client.getMdp())) {
                    Client ConnexionClient = new Client(client.getNom(), utilisateur, mot_de_passe, client.getId(), client.getReduction());
                    return true;
                }
            }
        }
        else {
            dataco.Data_Creation_Login(prenom, utilisateur, mot_de_passe,COListe.size()+1);
        }

        return false;
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