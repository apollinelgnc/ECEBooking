package com.example.ecebooking.Controllers.Hebergements;

import com.example.ecebooking.Models.Model;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class HebergementsController {

    @FXML
    private Label nom,nb_place,nb_chambre,menage,fumeur,wifi,prix,ville,promo,reducs;
    @FXML
    private ImageView image;
    private Hebergement hotel;
    // attributs pour l'affichage de l'hebergement clique



    // Méthode pour définir les informations de l'hôtel dans les composants graphiques

    public void initialize() throws IOException, SQLException, ClassNotFoundException {
        image.setOnMouseClicked(mouseEvent -> {
            Model.getInstance().getViewFactory().ResaView(hotel);
        });
        //reserver.setOnAction(event -> );
    }

    public void setHotel(Hebergement hotel2) {
        this.hotel = hotel2;
        URL imageUrl = getClass().getResource("/Images/" + hotel.getVille() + ".jpeg");
        if (imageUrl != null) {
            Image image_ = new Image(imageUrl.toExternalForm());
            image.setImage(image_);
        } else {
            System.out.println("L'image n'a pas pu être chargée.");
        }
        nom.setText(hotel.getNom_etablissement());
        ville.setText(hotel.getVille());
        prix.setText(Double.toString(hotel.getPrix()));
        /*if(hotel.getNombre_chambres()==null) {
            nb_chambre.setText("0");
        }
        nb_chambre.setText(String.valueOf(hotel.getNombre_chambres()));
        if(hotel.getNombre_places()==0)
            nb_chambre.setText("0");
        nb_place.setText(String.valueOf(hotel.getNombre_places()));*/
        fumeur.setText("Fumeur : " +hotel.getFumeur());
        menage.setText("Ménage : " +hotel.getMenage());
        wifi.setText("Wifi : " +hotel.getWifi());
        if(hotel.getPromo()!=1)
            promo.setText("Promotions !");
        if(hotel.getReducClient()==true)
            reducs.setText("Réductions client");
    }

}



