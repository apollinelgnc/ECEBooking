package com.example.ecebooking.Controllers.Hebergements;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.Objects;

public class HebergementsController {

        @FXML
        private Pane conteneur;

        @FXML
        private Label nom;

        @FXML
        private Label prix;
        @FXML
        private Label titre;
        @FXML
        private ImageView image;

        // Méthode pour définir les informations de l'hôtel dans les composants graphiques

        public void setHotel(Hebergement hotel) {
            URL imageUrl = getClass().getResource("/Images/"+hotel.getNom()+".jpeg");
            if (imageUrl != null) {
                    Image image_ = new Image(((URL) imageUrl).toExternalForm());
                    image.setImage(image_);
            } else {
                    System.out.println("L'image n'a pas pu être chargée.");
            }
            nom.setText(hotel.getAdresse());
            titre.setText(hotel.getNom());
            prix.setText(Double.toString(hotel.getPrix()));
        }
    }

