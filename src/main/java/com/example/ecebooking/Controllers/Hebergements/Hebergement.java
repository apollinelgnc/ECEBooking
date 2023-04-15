package com.example.ecebooking.Controllers.Hebergements;

import com.example.ecebooking.Controllers.Reservation;
import com.example.ecebooking.Models.DataCo;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.ArrayList;

public class Hebergement {

    static int compte_hebergement;
    private String nom_etablissement;
    private String ville;
    private int nombre_chambres;
    private int nombre_places;
    private double prix;
    private int distanceCentre;
    private int wifi;
    private int menage;
    private int fumeur;
    private int idhebergement;
    private ArrayList<String> option;
    private ArrayList<String> activite;
    private ArrayList<String> reservation;


    /** Constructeurs */
    // Récupération Base de données
    public Hebergement(String nom_etablissement, String ville, int nombre_chambres, int nombre_places, int prix, int distanceCentre, int Wifi, int Menage, int Fumeur, int idhebergement) {
        this.nom_etablissement = nom_etablissement;
        this.ville = ville;
        this.nombre_chambres = nombre_chambres;
        this.nombre_places = nombre_places;
        this.prix = prix;
        this.distanceCentre = distanceCentre;
        this.wifi=Wifi;
        this.menage=Menage;
        this.fumeur=Fumeur;
        this.idhebergement = idhebergement;

        compte_hebergement ++;
    }
    /** Constructeurs */
    // Récupération Base de données
    public Hebergement(String nom_etablissement, String ville) {
        this.nom_etablissement = nom_etablissement;
        this.ville = ville;
    }
    public Node getNode() {
        VBox vbox = new VBox();
        Label nomLabel = new Label("Nom : " + nom_etablissement);
        if (!vbox.getChildren().contains(nomLabel)) {
            vbox.getChildren().add(nomLabel);
        }
        Label adresseLabel = new Label("Adresse : " + ville);
        if (!vbox.getChildren().contains(adresseLabel)) {
            vbox.getChildren().add(adresseLabel);
        }
        return vbox;
    }
    // Creation par l'utilisateur
    /*public Hebergement()
    {
        Scanner clavier = new Scanner(System.in);
        String choix = "100";

        System.out.println("nom etablissement : ");
        nom_etablissement = clavier.nextLine();
        System.out.println("ville : ");
        ville = clavier.nextLine();
        System.out.println("nb chambre : ");
        nombre_chambres = clavier.nextInt();
        System.out.println("nb place : ");
        nombre_places = clavier.nextInt();
        System.out.println("prix : ");
        prix = clavier.nextInt();
        System.out.println("distance centre : ");
        distanceCentre = clavier.nextInt();
        compte_hebergement++;
        idhebergement = compte_hebergement;
    }*/


    /** METHODES */

    public void afficherReservation() throws SQLException, ClassNotFoundException {

        DataCo dataCo = new DataCo();
        ArrayList<Reservation> ListeReservation = dataCo.SQL_Data_Reservation();

        // Garde que les reservation dans cet hebergement
        for(int i=0; i<ListeReservation.size(); i++)
        {
            if(this.idhebergement != ListeReservation.get(i).getId_hebergement())
            {
                ListeReservation.remove(i);
                i--;
            }
        }
        // affiche les resa
        for(int i=0; i<ListeReservation.size(); i++)
        {
            System.out.println("     " + (i+1) + ". " + ListeReservation.get(i).getDebut() + " ---> " + ListeReservation.get(i).getFin());
        }

    }
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

    public double getPrix() {return prix;}

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

    public void setMenage(int menage) {
        this.menage = menage;
    }

    public void setFumeur(int fumeur) {
        this.fumeur = fumeur;
    }

    public int getMenage() {
        return menage;
    }

    public int getFumeur() {
        return fumeur;
    }
}
