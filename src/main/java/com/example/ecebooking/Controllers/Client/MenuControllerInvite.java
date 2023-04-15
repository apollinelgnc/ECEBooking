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
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class MenuControllerInvite {

    public TextField destination = new TextField();
    public ChoiceBox<Integer> nb_persons = new ChoiceBox<>();
    public DatePicker check_in_date = new DatePicker();
    public DatePicker check_out_date = new DatePicker();
    public Button go = new Button();
    public TextField prix = new TextField();
    public ChoiceBox<Integer> nombre_chambres = new ChoiceBox<>();
    public TextField distance = new TextField();
    public TextField nom_hebergement;
    @FXML
    public Pane conteneur;
    @FXML
    private Label label;
    @FXML
    private BorderPane borderPane; // Reference to the BorderPane in Menu.fxml

    private ViewFactory viewFactory; // Reference to the ViewFactory
    private DataCo data = new DataCo();
    @FXML
    private VBox vbox;
    public Button menu_button=new Button();
    public Button log_in_button=new Button();
    private final int id=-1;

    public void initialize() throws IOException, SQLException, ClassNotFoundException {

        log_in_button.setOnAction(actionEvent -> Model.getInstance().getViewFactory().SignInView());
        menu_button.setOnAction(actionEvent -> Model.getInstance().getViewFactory().InviteView());
        nb_persons.getItems().addAll(1, 2, 3, 4, 5, 6);
        nombre_chambres.getItems().addAll(1, 2, 3, 4, 5, 6);
        int affichage;
        VBox container = new VBox(); // Utiliser VBox à la place de Pane pour la disposition verticale
        container.setSpacing(50); // Définir un espacement entre les éléments de 100 pixels
        container.setPadding(new Insets(30, 0, 0, 0)); // Définir une marge pour la première ligne
        // Charger les données d'hôtels depuis une source de données
        AtomicReference<ArrayList<Hebergement>> hotels = new AtomicReference<>(filtrer());
        ArrayList<Hebergement> referenceListe = hotels.get();
        // Récupérer la taille de l'objet ArrayList
        int taille = referenceListe.size();
        if (taille > 4) taille = 4;
        HBox row = new HBox(); // Utiliser HBox pour représenter chaque ligne d'hébergements
        row.setAlignment(Pos.CENTER); // Centrer les éléments de la ligne horizontalement
        row.setSpacing(100); // Définir un espacement entre les hébergements de chaque ligne
        for (int i = 0; i < taille; i++) {
            Hebergement hotel = hotels.get().get(i);
            FXMLLoader loader = new FXMLLoader(); // Créer une nouvelle instance de FXMLLoader
            loader.setLocation(getClass().getResource("/Fxml/Hebergement/Hebergements.fxml")); // Définir l'emplacement pour FXMLLoader
            HebergementsController hebergementsView = new HebergementsController();
            loader.setController(hebergementsView);
            Pane view = loader.load(); // Charger Hebergements.fxml
            // Utiliser les données de l'hôtel pour configurer la vue
            hebergementsView.setHotel(hotel);
            row.getChildren().add(view); // Ajouter la vue à la ligne courante
            if ((i + 1) % 2 == 0 || i == hotels.get().size() - 1) {
                container.getChildren().add(row); // Ajouter la ligne au conteneur principal
                row = new HBox(); // Créer une nouvelle ligne pour les hébergements suivants
                row.setAlignment(Pos.CENTER); // Centrer les éléments de la ligne horizontalement
                row.setSpacing(100); // Définir un espacement entre les hébergements de chaque ligne
            }
            loader = new FXMLLoader(); // Créer une nouvelle instance de FXMLLoader pour la vue suivante
        }

        ScrollPane scrollPane = new ScrollPane(container); // Envelopper VBox dans un ScrollPane
        scrollPane.setPrefViewportWidth(1024); // Activer le défilement horizontal si nécessaire
        scrollPane.setPrefViewportHeight(500); // Définir la hauteur préférée du ScrollPane pour activer le défilement vertical
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

        if (!nom_hebergement.getText().equals("")) filtre.add(" nom = '" + nom_hebergement.getText() + "'");

        if (!destination.getText().equals("")) filtre.add(" ville = '" + destination.getText() + "'");

        if (nombre_chambres.getSelectionModel().getSelectedItem() != null)
            filtre.add(" nbChambre  >= '" + nombre_chambres.getSelectionModel().getSelectedItem() + "'");

        if (nb_persons.getSelectionModel().getSelectedItem() != null)
            filtre.add(" nbPlace  >= '" + nb_persons.getSelectionModel().getSelectedItem() + "'");

        if (!prix.getText().equals("")) filtre.add(" prix  <= '" + Integer.parseInt(prix.getText()) + "'");

        if (!distance.getText().equals(""))
            filtre.add(" distanceCentre <= '" + Integer.parseInt(distance.getText()) + "'");
        if (filtre.size() > 0) {
            request.append(" WHERE").append(filtre.get(0));
            for (int i = 1; i < filtre.size(); i++) {
                request.append(" &&").append(filtre.get(i));
            }
        }
        return data.SQL_Data_Hebergements(request.toString());
    }
}
