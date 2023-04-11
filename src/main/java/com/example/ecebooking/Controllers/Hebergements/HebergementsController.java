package com.example.ecebooking.Controllers.Hebergements;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class HebergementsController implements Initializable {

    public DatePicker check_in_date;
    public DatePicker check_out_date;
    public TextField destination;
    public ChoiceBox nb_persons;
    public Label nom;
    public Label ville;
    public Label prix;
    public HBox hbox;

    private List<Hebergement> hotels;
    @FXML
    public VBox Hebergements;
    @FXML
    public Button randomizeHotelsButton = new Button();

    public void initialize(URL url, ResourceBundle rb) {
        // Initialiser la liste des hébergements
        hotels = Arrays.asList(
                new Hebergement("Hôtel 1", "Paris"),
                new Hebergement("Hôtel 2", "Lyon"),
                new Hebergement("Hôtel 3", "Marseille"),
                new Hebergement("Hôtel 4", "Bordeaux"),
                new Hebergement("Hôtel 5", "Nice")
        );
    }

    @FXML
    public void afficherHotelsAleatoires() {
        // Sélectionner un nombre aléatoire d'hôtels à afficher
        int nombreAleatoire = ThreadLocalRandom.current().nextInt(1, hotels.size() + 1);
        List<Hebergement> hotelsAleatoires = new ArrayList<>(hotels);
        Collections.shuffle(hotelsAleatoires);
        hotelsAleatoires = hotelsAleatoires.subList(0, nombreAleatoire);

        // Effacer le conteneur d'hôtels et ajouter les hôtels sélectionnés avec une marge inférieure de 100
        if (Hebergements.getChildren() != null)
            Hebergements.getChildren().clear();
        for (Hebergement hotel : hotelsAleatoires) {
            nom=new Label(hotel.getNom_etablissement());
            ville=new Label(hotel.getVille());
            hbox=new HBox(nom,ville);
           // VBox.setMargin(hbox, new Insets(0, 0, 100, 0));
            Hebergements.getChildren().add(hbox);
        }
    }

}
