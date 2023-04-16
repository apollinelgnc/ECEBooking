package com.example.ecebooking.Controllers.Admin;

import com.example.ecebooking.Controllers.Client.Reservation;
import com.example.ecebooking.Models.Model;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;

public class AdminControllerResa {
    @FXML
    private Button menu_button = new Button();
    @FXML
    private Button ajouterPromos = new Button();
    @FXML
    private Button supprimer = new Button();
    @FXML
    private Button log_out_button = new Button();
    @FXML
    private Button supprimerGo = new Button();
    @FXML
    private Button ajouterGo = new Button();
    @FXML
    private Label id, promo;
    @FXML
    private TextField Tapezid, Tapezpromo;
    @FXML
    private TableView<Reservation> tableView;
    @FXML
    private TableColumn<Reservation, String> idHebergementCol;
    @FXML
    private TableColumn<Reservation, String> idClientCol;
    @FXML
    private TableColumn<Reservation, String> dateDebutCol;
    @FXML
    private TableColumn<Reservation, String> dateFinCol;
    @FXML
    private TableColumn<Reservation, String> idCol;
    @FXML
    private TableColumn<Reservation, String> prixCol;
    private Admin admin;

    public AdminControllerResa(Admin ad) throws Exception {
        admin = ad;
    }

    public void initialize() throws Exception {
        idHebergementCol.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getId_hebergement())));
        idClientCol.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getId_client())));
        idCol.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getId())));
        prixCol.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPrix())));
        dateDebutCol.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getDebut())));
        dateFinCol.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getFin())));
        loadData();
        menu_button.setOnAction(event -> {
            try {
                Model.getInstance().getViewFactory().AdminView(admin);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        supprimer.setOnAction(event -> {
            Tapezid.setVisible(true);
            id.setVisible(true);
            supprimerGo.setVisible(true);
            supprimerGo.setOnAction(event1 -> {
                try {
                    admin.SuppReza(Tapezid.getText());
                    loadData();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        });
        log_out_button.setOnAction(event -> Model.getInstance().getViewFactory().LoginView());
    }

    private void loadData() {
        try {
            admin.AdminChargement();
            ArrayList<Reservation> resa = admin.getListResa();
            tableView.getItems().clear();
            tableView.getItems().addAll(resa);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

