package com.example.ecebooking.Controllers.Hebergements;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HebergementsController {

    // ...

    @FXML
    private Label lblDestination;

    @FXML
    private Label lblPrix;

    @FXML
    private Label lblNbChambres;

    @FXML
    private Label lblNbPlaces;

    @FXML
    private Label lblNom;

    @FXML
    private Label lblDistanceCentre;

    // ...

    // Méthode pour afficher les détails de l'hébergement sélectionné
    private void afficherDetailsHebergement(Hebergement hebergement) {
        // Mettre à jour les labels avec les détails de l'hébergement
        lblDestination.setText("Destination : " + hebergement.getVille());
        lblPrix.setText("Prix : " + hebergement.getPrix() + " €");
        lblNbChambres.setText("Nombre de chambres : " + hebergement.getNombre_chambres());
        lblNbPlaces.setText("Nombre de places : " + hebergement.getNombre_places());
        lblNom.setText("Nom : " + hebergement.getNom_etablissement());
        lblDistanceCentre.setText("Distance du centre : " + hebergement.getDistanceCentre() + " km");
    }

    // ...
}
