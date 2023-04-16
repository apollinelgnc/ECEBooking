package com.example.ecebooking.Controllers.Hebergements;

import com.example.ecebooking.Controllers.Client.MenuControllerInvite;
import com.example.ecebooking.Controllers.Client.Reservation;
import com.example.ecebooking.Models.DataCo;
import com.example.ecebooking.Models.Model;
import com.example.ecebooking.Views.ViewFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Popup;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Un_HebergementController {

    Hebergement hotel;
    DataCo co = new DataCo();
    @FXML
    private Button reserver;
    @FXML
    private Button annuler;
    @FXML
    private DatePicker date_debut;
    @FXML
    private DatePicker date_fin;
    @FXML
    private Label Ville, Prix, Nom, Option, erreur, nb_chambres, nb_places;
    @FXML
    private ImageView Image;

    public Un_HebergementController(Hebergement hotel) {
        this.hotel = hotel;
    }

    public void initialize() {
        reserver.setOnAction(event -> {
            try {
                reserver();
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        annuler.setOnAction(event -> Model.getInstance().getViewFactory().closeStage());
    }

    public void setHotel() {
        System.out.println(hotel.getNom_etablissement());
        URL imageUrl = getClass().getResource("/Images/" + hotel.getNom_etablissement() + ".jpg");
        URL imageUrl2 = getClass().getResource("/Images/point.jpg");
        if (imageUrl != null) {
            Image image_ = new Image(((URL) imageUrl).toExternalForm());
            Image.setImage(image_);
        } else if (imageUrl2 != null) {
            Image image_ = new Image(((URL) imageUrl).toExternalForm());
            Image.setImage(image_);
        } else
            System.out.println("L'image n'a pas pu être chargée.");
        Nom.setText(hotel.getNom_etablissement());
        Ville.setText(hotel.getVille());
        nb_chambres.setText(Double.toString(hotel.getNombre_chambres()) + " chambres");
        nb_places.setText(Double.toString(hotel.getNombre_places()) + " places");
        Prix.setText(Double.toString(hotel.getPrix()) + " €/nuit");
        Option.setText("Option : Wifi-" + hotel.getWifi() + " Ménage-" + hotel.getMenage() + " Fumeur-" + hotel.getFumeur());
    }

    public void reserver() throws SQLException, ClassNotFoundException {
        int jour_debut, mois_debut, annee_debut;
        int jour_fin, mois_fin, annee_fin;
        double prix_tt;
        boolean valide;
        Popup popup = new Popup();
        Label label = new Label();
        ArrayList<Reservation> resas = co.SQL_Data_Reservation();
        // Choix des dates
        LocalDate debut = date_debut.getValue();
        LocalDate fin = date_fin.getValue();
        for (int i = 0; i < resas.size(); i++) {
            if (hotel.getIdhebergement() == resas.get(i).getId_hebergement())
                erreur.setText("Impossible de valider la résa, l'établissement n'est pas dispo ");
        }
        if (debut == null || fin == null || debut.isAfter(fin)) {
            erreur.setText("Impossible de valider la résa, les dates ne sont pas valides");
            return;
        }

        //Calcul du prix de la reservation = nb jours * prix/jour de l'hebergement
        prix_tt = ChronoUnit.DAYS.between(debut, fin) * hotel.getPrix();
        Reservation nouveau = new Reservation(hotel.getIdhebergement(), -1, debut, fin, prix_tt, 0);//new Reservation(ListeHebergement.get(choix-1).getIdhebergement(), -1, debut, fin);

        // Verification disponibilité date
        valide = nouveau.verification();
        FXMLLoader load = new FXMLLoader(getClass().getResource("/Fxml/Hebergement/PayementPage.fxml"));
        PayementController payementController = new PayementController();
        load.setController(payementController);
        ViewFactory view = new ViewFactory();
        view.createStage(load);
        boolean tempo = payementController.isValidation();
        if (tempo) {
            if (valide) {
                DataCo dataco = new DataCo();
                dataco.Data_Creation_Reservation(nouveau);
                label.setText("Réservation validée, fermer la fenêtre pour revenir au menu !");
                popup.getContent().add(label);
                popup.show(reserver.getScene().getWindow()); // Show popup relative to the current window
                Model.getInstance().getViewFactory().closeStage();
                nouveau.afficher();
            }
        } else {
            label.setText("Réservation non-validée");
            popup.getContent().add(label);
            popup.show(reserver.getScene().getWindow()); // Show popup relative to the current window
            Model.getInstance().getViewFactory().closeStage();
        }


    }
}