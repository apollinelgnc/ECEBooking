package com.example.ecebooking.Controllers.Client;

import com.example.ecebooking.Controllers.Hebergements.Hebergement;
import com.example.ecebooking.Models.DataCo;
import com.example.ecebooking.Models.Model;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class MenuControllerClientResa {

    public DatePicker check_in_date = new DatePicker();
    public DatePicker check_out_date = new DatePicker();
    public Button menu_button = new Button();
    public Button stats_button = new Button();
    public Button profile_button = new Button();
    public Button reservation_button = new Button();
    public Button log_out_button = new Button();
    Client c;
    ArrayList<Hebergement> hebergements = new ArrayList<>();
    ArrayList<Reservation> resa = new ArrayList<>();
    ArrayList<Reservation> clientResa = new ArrayList<>();
    private DataCo data = new DataCo();
    @FXML
    private VBox vbox;
    @FXML
    private TableView<Reservation> tableView;
    @FXML
    private TableColumn<Reservation, String> idHebergementCol;
    @FXML
    private TableColumn<Reservation, String> prixCol;
    @FXML
    private TableColumn<Reservation, String> dateDebutCol;
    @FXML
    private TableColumn<Reservation, String> dateFinCol;

    public MenuControllerClientResa(Client client) {
        c = client;
    }

    public MenuControllerClientResa() {
    }

    public void initialize() throws IOException, SQLException, ClassNotFoundException {
        idHebergementCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom_hebergement()));
        prixCol.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPrix())));
        dateFinCol.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getFin())));
        dateDebutCol.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getDebut())));
        menu_button.setOnAction(event -> Model.getInstance().getViewFactory().ClientView(c));
        log_out_button.setOnAction(event -> Model.getInstance().getViewFactory().LoginView());
        loadData();
    }

    public void loadData() throws SQLException, ClassNotFoundException {
        resa = data.SQL_Data_Reservation();
        hebergements = data.SQL_Data_Hebergements("SELECT * FROM `etablissement`");
        for (int i = 0; i < resa.size(); i++) {
            if (resa.get(i).getId_client() == c.getId())
                clientResa.add(resa.get(i));
        }
        for (int i = 0; i < clientResa.size(); i++) {
            for (int j = 0; j < hebergements.size(); j++) {
                if (clientResa.get(i).getId_hebergement() == hebergements.get(j).getIdhebergement())
                    clientResa.get(i).setNom_hebergement(hebergements.get(j).getNom_etablissement());
            }
        }
        tableView.getItems().clear();
        tableView.getItems().addAll(clientResa);
    }

}