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
import javafx.scene.layout.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MenuController {

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
    private DataCo data=new DataCo();

    public void initialize() throws IOException, SQLException, ClassNotFoundException {
        nb_persons.getItems().addAll(1, 2, 3, 4, 5, 6);
        nombre_chambres.getItems().addAll(1, 2, 3, 4, 5, 6);

        VBox container = new VBox(); // Utiliser VBox à la place de Pane pour la disposition verticale
        container.setSpacing(50); // Définir un espacement entre les éléments de 100 pixels
        container.setPadding(new Insets(30, 0, 0, 0)); // Définir une marge pour la première ligne
        // Charger les données d'hôtels depuis une source de données
        List<Hebergement> hotels = getHotelsFromDataSource();
        HBox row = new HBox(); // Utiliser HBox pour représenter chaque ligne d'hébergements
        row.setAlignment(Pos.CENTER); // Centrer les éléments de la ligne horizontalement
        row.setSpacing(100); // Définir un espacement entre les hébergements de chaque ligne

        for (int i = 0; i < hotels.size(); i++) {
            Hebergement hotel = hotels.get(i);
            FXMLLoader loader = new FXMLLoader(); // Créer une nouvelle instance de FXMLLoader
            loader.setLocation(getClass().getResource("/Fxml/Client/Hebergements.fxml")); // Définir l'emplacement pour FXMLLoader
            HebergementsController hebergementsView = new HebergementsController();
            loader.setController(hebergementsView);
            Pane view = loader.load(); // Charger Hebergements.fxml
            // Utiliser les données de l'hôtel pour configurer la vue
            hebergementsView.setHotel(hotel);
            row.getChildren().add(view); // Ajouter la vue à la ligne courante
            if ((i + 1) % 2 == 0 || i == hotels.size() - 1) {
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
    }


    private List<Hebergement> getHotelsFromDataSource() throws SQLException, ClassNotFoundException {

        List<Hebergement> hotels = new ArrayList<>();
        hotels=data.SQL_Data_Hebergements();
        return hotels;
    }

}





/* nom_hebergement.getText(),
                        destination.getText(),
                        nombre_chambres.getSelectionModel().getSelectedItem(),
                        nb_persons.getSelectionModel().getSelectedItem(),
                        Integer.parseInt(prix.getText()),
                        Integer.parseInt(distance.getText())*/
