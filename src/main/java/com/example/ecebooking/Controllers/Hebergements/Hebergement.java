package com.example.ecebooking.Controllers.Hebergements;

import com.example.ecebooking.Models.DataCo;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.ArrayList;

public class Hebergement {

    static int compte_hebergement;
    public VBox vboxHebergement=new VBox();
    private String nom_etablissement;
    private String ville;
    private int nombre_chambres;
    private int nombre_places;
    private int prix;
    private int distanceCentre;
    private int wifi;
    private int menage;
    private int fumeur;
    private int idhebergement;
    private ArrayList<String> option;
    private ArrayList<String> activite;
    private ArrayList<String> reservation;
    @FXML
    private ListView<Hebergement> listViewHebergements;
    private ArrayList<Hebergement> listeHebergements= new ArrayList<>();
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

    /**
     * Constructeurs
     */
    // Récupération Base de données
    public Hebergement(String nom_etablissement, String ville, int nombre_chambres, int nombre_places, int prix, int distanceCentre) throws SQLException, ClassNotFoundException {
        this.nom_etablissement = nom_etablissement;
        this.ville = ville;
        this.nombre_chambres = nombre_chambres;
        this.nombre_places = nombre_places;
        this.prix = prix;
        this.distanceCentre = distanceCentre;
       // this.wifi = Wifi;
       // this.menage = Menage;
       // this.fumeur = Fumeur;
       // this.idhebergement = idhebergement;

        compte_hebergement++;
        initialize();
    }

    @FXML
    private void initialize() throws SQLException, ClassNotFoundException {

        ArrayList<Hebergement> listeHebergements = filtrer(nom_etablissement,ville,nombre_chambres,nombre_places,prix,distanceCentre );
    }

    private ArrayList<Hebergement> filtrer(String nom_etablissement_filtre,String ville_filtre,int nombre_chambres_filtre,int nombre_places_filtre, int prix_filtre, int distanceCentre_filtre )throws SQLException, ClassNotFoundException {
        System.out.println("cocu");
        DataCo dataco = new DataCo();
        System.out.println("cocu2");

        StringBuilder request = new StringBuilder("SELECT * FROM `etablissement`");
        ArrayList<String> filtre = new ArrayList<>();
        ArrayList<Hebergement>tempo=new ArrayList<>();
        filtre.add(" nom = '" + nom_etablissement_filtre + "'");
        filtre.add(" ville = '" + ville_filtre + "'");
        filtre.add(" nbChambre  >= '" + nombre_chambres_filtre + "'");
        filtre.add(" nbPlace  >= '" + nombre_places_filtre + "'");
        filtre.add(" prix  <= '" + prix_filtre + "'");
        filtre.add(" distanceCentre <= '" + distanceCentre_filtre + "'");

        if (filtre.size() > 0) {
            request.append(" WHERE").append(filtre.get(0));
            for (int i = 1; i < filtre.size(); i++) {
                request.append(" &&").append(filtre.get(i));
            }
        }
        tempo=dataco.SQL_Data_Hebergements(request.toString());
        for(Hebergement h : tempo){
            System.out.println("sdsfgfds");
            System.out.println(h.ville);
        }
        return tempo;
    }

    /**
     * METHODES
     */
    @Override
    public String toString() {
        return "Hebergement{" +
                "nom_etablissement='" + nom_etablissement + '\'' +
                ", ville='" + ville + '\'' +
                ", nombre_chambres=" + nombre_chambres +
                ", nombre_places=" + nombre_places +
                ", prix=" + prix +
                ", distanceCentre=" + distanceCentre +
                ", idhebergement=" + idhebergement +
                '}';
    }

    public String getNom_etablissement() {
        return nom_etablissement;
    }

    public void setNom_etablissement(String nom_etablissement) {
        this.nom_etablissement = nom_etablissement;
    }

    public String getVille() {

        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public int getNombre_chambres() {
        return nombre_chambres;
    }

    public void setNombre_chambres(int nombre_chambres) {
        this.nombre_chambres = nombre_chambres;
    }

    public int getNombre_places() {
        return nombre_places;
    }

    public void setNombre_places(int nombre_places) {
        this.nombre_places = nombre_places;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getDistanceCentre() {
        return distanceCentre;
    }

    public void setDistanceCentre(int distanceCentre) {
        this.distanceCentre = distanceCentre;
    }

    public int getIdhebergement() {
        return idhebergement;
    }

    public void setIdhebergement(int idhebergement) {
        this.idhebergement = idhebergement;
    }

    public ArrayList<String> getOption() {
        return option;
    }

    public void setOption(ArrayList<String> option) {
        this.option = option;
    }

    public ArrayList<String> getActivite() {
        return activite;
    }

    public void setActivite(ArrayList<String> activite) {
        this.activite = activite;
    }

    public ArrayList<String> getReservation() {
        return reservation;
    }

    public void setReservation(ArrayList<String> reservation) {
        this.reservation = reservation;
    }

    public int getWifi() {
        return wifi;
    }

    public void setWifi(int wifi) {
        this.wifi = wifi;
    }

    public int getMenage() {
        return menage;
    }

    public void setMenage(int menage) {
        this.menage = menage;
    }

    public int getFumeur() {
        return fumeur;
    }

    public void setFumeur(int fumeur) {
        this.fumeur = fumeur;
    }
}
