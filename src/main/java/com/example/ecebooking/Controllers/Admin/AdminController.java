package com.example.ecebooking.Controllers.Admin;

import com.example.ecebooking.Controllers.Client.Client;
import com.example.ecebooking.Models.Model;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
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

import com.example.ecebooking.Controllers.Admin.Admin;
import com.example.ecebooking.Controllers.Client.Client;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AdminController {
    private Button menu_button=new Button();
    @FXML
    private TableView<Client> tableView;

    @FXML
    private TableColumn<Client, String> prenomCol;

    @FXML
    private TableColumn<Client, String> idCol;

    private Admin admin;
    public AdminController(Admin ad)
    {
     admin=ad;
    }
    public void initialize() {
        menu_button.setOnAction(event -> Model.getInstance().getViewFactory().AdminView(admin));
        // Get the list of users from the Admin class
        ArrayList<Client> utilisateurs = admin.getListClient();
        // Configure the TableView to use the ArrayList as the data source
        tableView.getItems().addAll(utilisateurs);
        admin.afficherListeClient(utilisateurs);

        // Configure the cell factories for nomCol and prenomCol columns
        prenomCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()));
        idCol.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getId())));
    }
}

