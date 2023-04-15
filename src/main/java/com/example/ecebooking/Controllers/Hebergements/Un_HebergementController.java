package com.example.ecebooking.Controllers.Hebergements;

import com.example.ecebooking.Controllers.Reservation;
import com.example.ecebooking.Models.DataCo;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class Un_HebergementController {

    Hebergement hotel;
    @FXML
    private Button reserver;
    @FXML
    private DatePicker date_debut;
    @FXML
    private DatePicker date_fin;
    @FXML
    private Label Ville, Prix, Nom, Option, erreur;
    @FXML
    private ImageView Image;

    public Un_HebergementController(Hebergement hotel) {
        this.hotel = hotel;
    }
    public void initialize(){
        reserver.setOnAction(event -> {
            try {
                reserver();
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }
    public void setHotel() {
        System.out.println(hotel.getNom_etablissement());
        URL imageUrl = getClass().getResource("/Images/" + hotel.getVille() + ".jpeg");
        if (imageUrl != null) {
            Image image_ = new Image(((URL) imageUrl).toExternalForm());
            Image.setImage(image_);
        } else {
            System.out.println("L'image n'a pas pu être chargée.");
        }
        Nom.setText(hotel.getNom_etablissement());
        Ville.setText(hotel.getVille());
        Prix.setText(Double.toString(hotel.getPrix()));
    }

    public void reserver() throws SQLException, ClassNotFoundException {
        int jour_debut, mois_debut, annee_debut;
        int jour_fin, mois_fin, annee_fin;
        double prix_tt;
        boolean valide;

        // Choix des dates
        LocalDate debut = date_debut.getValue();
        LocalDate fin = date_fin.getValue();

        if (debut == null || fin == null || debut.isAfter(fin)) {
            erreur.setText("Impossible de valider la résa, les dates ne sont pas valides");
            return;
        }

        //Calcul du prix de la reservation = nb jours * prix/jour de l'hebergement
        prix_tt = ChronoUnit.DAYS.between(debut, fin) * hotel.getPrix();

        Reservation nouveau = creerReservation(hotel.getIdhebergement(), debut, fin, prix_tt);//new Reservation(ListeHebergement.get(choix-1).getIdhebergement(), -1, debut, fin);

        // Verification disponibilité date
            valide = nouveau.verification();

        if (valide) {
        DataCo dataco = new DataCo();
          dataco.Data_Creation_Reservation(nouveau);
        System.out.println("Validée");
          nouveau.afficher();
          } else System.out.println("Refusée");
    }
    public Reservation creerReservation(int i, LocalDate debut, LocalDate fin, double prix)
    {
        return new Reservation(i,-1,debut,fin,prix);
    }


}
