package com.example.ecebooking.Controllers.Admin;

import com.example.ecebooking.Controllers.Client.Client;
import com.example.ecebooking.Models.Model;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
/*
public class AdminController {
    @FXML
    private TableView<Client> table; // Table pour afficher les clients
    @FXML
    private TableColumn<Client, String> prenomCol; // Colonne pour le prénom
    @FXML
    private TableColumn<Client, String> idCol; // Colonne pour l'identifiant
    @FXML
    private TableColumn<Client, Integer> numCol; // Colonne pour le numéro client
    @FXML
    private TableColumn<Client, Double> reductionCol; // Colonne pour la réduction
    @FXML
    private Button logOutButton; // Bouton de déconnexion
    @FXML
    private Pane conteneur; // Conteneur pour la table

    private Admin ad;

    public AdminController(Admin admin){
        this.ad=admin;
    }

    public AdminController(){}

    public void initialize() {
        // Configuration des cellules de la table
        prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("identifiant"));
        numCol.setCellValueFactory(new PropertyValueFactory<>("idClient"));
        reductionCol.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getReduction()));
        table.setItems((ObservableList<Client>) ad.getListClient()); // Utilisation de la liste des clients de l'Admin pour peupler la table
        logOutButton.setOnAction(actionEvent -> Model.getInstance().getViewFactory().closeStage());
        conteneur.getChildren().add(table); // Ajout de la table dans le conteneur
    }
}
*/

// Example code using ArrayList without ObservableList

import java.util.ArrayList;

public class AdminController {
    @FXML
    private Button menu_button=new Button();
    @FXML
    private Button hebergements=new Button();
    @FXML
    private Button clients = new Button();
    @FXML
    private Button log_out_button=new Button();
    @FXML
    private TableView<Client> tableView;

    @FXML
    private TableColumn<Client, String> prenomCol;

    @FXML
    private TableColumn<Client, String> idCol;
    @FXML
    private TableColumn<Client, String> reduction;

    @FXML
    private TableColumn<Client, String> num_client;

    private Admin admin;
    public AdminController(Admin ad) throws Exception {
     admin=ad;

    }
    public void initialize() throws Exception {
        menu_button.setOnAction(event -> {
            try {
                Model.getInstance().getViewFactory().AdminView(admin);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        log_out_button.setOnAction(event -> Model.getInstance().getViewFactory().closeStage());
        clients.setOnAction(event-> {
            try {
                Model.getInstance().getViewFactory().AdminViewClient(admin);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        hebergements.setOnAction(event-> {
            try {
                Model.getInstance().getViewFactory().AdminViewHebergement(admin);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        });
        // Get the list of users from the Admin class
      }

}

