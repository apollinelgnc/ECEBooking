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

    Client client;
    Admin admin;

    public Client getClient() {
        return client;
    }

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
                            Model.getInstance().getViewFactory().ClientView(client.getId());
                    } else {
                        System.out.println("error " + id_entree.getText());

                        // afficher un message d'erreur si les identifiants sont invalides
                    }
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            } else {
                // afficher un message d'erreur si tous les champs ne sont pas remplis
            }
        });

        connection_invite.setOnAction(event -> {
            Model.getInstance().getViewFactory().InviteView();
        });
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
                if (utilisateur.equals(ad.getIdA()) && mot_de_passe.equals(ad.getMdpA())) {
                     admin = new Admin(ad.getNomA(), utilisateur, mot_de_passe, ad.getNumeroA());
                    return true;
                }
            }
        } else if (Objects.equals(choix, "Client")) {
            for (Client cl : COListe) {
                if (utilisateur.equals(cl.getUtilisateur()) && mot_de_passe.equals(cl.getMdp())) {
                    client  = new Client(cl.getNom(), utilisateur, mot_de_passe, cl.getId(), cl.getReduction());
                    System.out.println(client.toString());
                    return true;
                }
            }
        }
        else {
            dataco.Data_Creation_Login(prenom, utilisateur, mot_de_passe,COListe.size()+1);
            return true;
        }
        return false;
    }
}