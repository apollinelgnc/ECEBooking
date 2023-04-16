package com.example.ecebooking.Controllers.Admin;

import com.example.ecebooking.Controllers.Client.Client;
import com.example.ecebooking.Models.Model;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class AdminControllerClient {
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

    public AdminControllerClient(Admin ad) throws Exception {
        admin = ad;
    }

    public void initialize() throws Exception {

        prenomCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()));
        idCol.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getUtilisateur())));
        num_client.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getId())));
        reduction.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getReduction())));
        loadData();
        menu_button.setOnAction(event -> {
            try {
                Model.getInstance().getViewFactory().AdminView(admin);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        log_out_button.setOnAction(event -> Model.getInstance().getViewFactory().closeStage());
        supprimer.setOnAction(event -> {
            Tapezid.setVisible(true);
            id.setVisible(true);
            supprimerGo.setVisible(true);
            promo.setVisible(false);
            ajouterGo.setVisible(false);
            supprimerGo.setOnAction(event1 -> {
                try {
                    admin.SuppClient(Tapezid.getText());
                    loadData();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            });
        });
        ajouterPromos.setOnAction(event -> {
            Tapezid.setVisible(true);
            id.setVisible(true);
            Tapezpromo.setVisible(true);
            promo.setVisible(true);
            ajouterGo.setVisible(true);
            ajouterGo.setOnAction(event1 -> {
                try {
                    admin.PromoC(Tapezid.getText(), Tapezpromo.getText());
                    loadData();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        });
    }

    private void loadData() {
        try {
            admin.AdminChargement();
            ArrayList<Client> utilisateurs = admin.getListClient();
            tableView.getItems().clear();
            tableView.getItems().addAll(utilisateurs);
            admin.afficherListeClient(utilisateurs);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

