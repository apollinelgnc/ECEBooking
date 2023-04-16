package com.example.ecebooking.Controllers.Client;

import com.example.ecebooking.Controllers.Hebergements.Hebergement;
import com.example.ecebooking.Controllers.Hebergements.HebergementsController;
import com.example.ecebooking.Models.DataCo;
import com.example.ecebooking.Models.Model;
import com.example.ecebooking.Views.ViewFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class MenuControllerClient {

    public TextField destination = new TextField();
    public ChoiceBox<Integer> nb_persons = new ChoiceBox<>();
    public Button go = new Button();
    public TextField prix = new TextField();
    public ChoiceBox<String> wifi = new ChoiceBox<>();
    public ChoiceBox<String> menage = new ChoiceBox<>();
    public ChoiceBox<String> fumeur = new ChoiceBox<>();
    public ChoiceBox<Integer> nombre_chambres = new ChoiceBox<>();
    public TextField distance = new TextField();
    public TextField nom_hebergement;
    @FXML
    public Pane conteneur;
    public Button menu_button = new Button();
    public Button stats_button = new Button();
    public Button profile_button = new Button();
    public Button reservation_button = new Button();
    public Button log_out_button = new Button();
    Client c;
    @FXML
    private Label label;
    @FXML
    private BorderPane borderPane; // Reference to the BorderPane in Menu.fxml
    private ViewFactory viewFactory; // Reference to the ViewFactory
    private DataCo data = new DataCo();
    @FXML
    private VBox vbox;

    public MenuControllerClient(Client client) {
        c = client;
    }

    public MenuControllerClient() {
    }

    public void initialize() throws IOException, SQLException, ClassNotFoundException {

        System.out.println(c.getId());
        reservation_button.setOnAction(actionEvent -> Model.getInstance().getViewFactory().ClientViewresa(c));
        menu_button.setOnAction(actionEvent -> Model.getInstance().getViewFactory().ClientView(c));
        log_out_button.setOnAction(actionEvent -> Model.getInstance().getViewFactory().LoginView());
        nb_persons.getItems().addAll(1, 2, 3, 4, 5, 6);
        nombre_chambres.getItems().addAll(1, 2, 3, 4, 5, 6);
        wifi.getItems().addAll("Oui", "Non");
        fumeur.getItems().addAll("Oui", "Non");
        menage.getItems().addAll("Oui", "Non");
        int affichage;
        VBox container = new VBox(); // Utiliser VBox à la place de Pane pour la disposition verticale
        container.setSpacing(50); // Définir un espacement entre les éléments de 50 pixels
        container.setPadding(new Insets(30, 0, 0, 0)); // Définir une marge pour la première ligne
        // Charger les données d'hôtels depuis une source de données
        AtomicReference<ArrayList<Hebergement>> hotels = new AtomicReference<>(filtrer());
        ArrayList<Hebergement> referenceListe = hotels.get();
        // Récupérer la taille de l'objet ArrayList
        int taille = referenceListe.size();
        if (taille > 6) taille = 6;
        HBox row = new HBox(); // Utiliser HBox pour représenter chaque ligne d'hébergements
        row.setAlignment(Pos.CENTER); // Centrer les éléments de la ligne horizontalement
        row.setSpacing(100); // Définir un espacement entre les hébergements de chaque ligne
        for (int i = 0; i < taille; i++) {
            Hebergement hotel = hotels.get().get(i);
            hotel.setPrix(c.getReduction());
            FXMLLoader loader = new FXMLLoader(); // Créer une nouvelle instance de FXMLLoader
            loader.setLocation(getClass().getResource("/Fxml/Hebergement/Hebergements.fxml")); // Définir l'emplacement pour FXMLLoader
            HebergementsController hebergementsView = new HebergementsController();
            loader.setController(hebergementsView);
            hotel.setReducClient();
            Pane view = loader.load(); // Charger Hebergements.fxml
            // Utiliser les données de l'hôtel pour configurer la vue
            hebergementsView.setHotel(hotel);
            row.getChildren().add(view); // Ajouter la vue à la ligne courante
            container.getChildren().add(row); // Ajouter la ligne au conteneur principal
            row = new HBox(); // Créer une nouvelle ligne pour les hébergements suivants
            row.setAlignment(Pos.CENTER); // Centrer les éléments de la ligne horizontalement
            loader = new FXMLLoader(); // Créer une nouvelle instance de FXMLLoader pour la vue suivante
        }

        ScrollPane scrollPane = new ScrollPane(container); // Envelopper VBox dans un ScrollPane
        scrollPane.setFitToWidth(true); // Lier fitToWidth à true
        scrollPane.prefViewportWidthProperty().bind(conteneur.widthProperty());
        scrollPane.setPrefHeight(600); // Définir une hauteur préférée pour le ScrollPane (par exemple 600 pixels)
        conteneur.getChildren().add(scrollPane); // Ajouter le ScrollPane au conteneur principal
        go.setOnAction(event -> {
            try {
                hotels.set(filtrer());
                initialize();
            } catch (SQLException | ClassNotFoundException | IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public ArrayList<Hebergement> filtrer() throws SQLException, ClassNotFoundException {

        StringBuilder request = new StringBuilder("SELECT * FROM `etablissement`");
        ArrayList<String> filtre = new ArrayList<>();
        String Wifi, Menage, Fumeur;
        if (!nom_hebergement.getText().equals("")) filtre.add(" nom = '" + nom_hebergement.getText() + "'");

        if (!destination.getText().equals("")) filtre.add(" ville = '" + destination.getText() + "'");

        if (nombre_chambres.getSelectionModel().getSelectedItem() != null)
            filtre.add(" nbChambre  >= '" + nombre_chambres.getSelectionModel().getSelectedItem() + "'");

        if (nb_persons.getSelectionModel().getSelectedItem() != null)
            filtre.add(" nbPlace  >= '" + nb_persons.getSelectionModel().getSelectedItem() + "'");

        if (!prix.getText().equals("")) filtre.add(" prix  <= '" + Integer.parseInt(prix.getText()) + "'");

        if (!distance.getText().equals(""))
            filtre.add(" distanceCentre <= '" + Integer.parseInt(distance.getText()) + "'");

        if (wifi.getSelectionModel().getSelectedItem() != null) {
            if (wifi.getSelectionModel().getSelectedItem() == "Oui")
                Wifi = "1";
            else Wifi = "0";
            filtre.add(" wifi  = '" + Wifi + "'");
        }
        if (menage.getSelectionModel().getSelectedItem() != null) {
            if (menage.getSelectionModel().getSelectedItem() == "Oui")
                Menage = "1";
            else Menage = "0";
            filtre.add(" menage  = '" + Menage + "'");
        }
        if (fumeur.getSelectionModel().getSelectedItem() != null) {
            if (fumeur.getSelectionModel().getSelectedItem() == "Oui")
                Fumeur = "1";
            else Fumeur = "0";
            filtre.add(" fumeur  = '" + Fumeur + "'");
        }
        if (filtre.size() > 0) {
            request.append(" WHERE").append(filtre.get(0));
            for (int i = 1; i < filtre.size(); i++) {
                request.append(" &&").append(filtre.get(i));
            }
        }
        return data.SQL_Data_Hebergements(request.toString());
    }
}
