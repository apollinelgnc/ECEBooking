package com.example.ecebooking.Controllers.Hebergements;

import com.example.ecebooking.Controllers.Client.Reservation;
import com.example.ecebooking.Models.DataCo;

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

    private double promo;
    boolean reducClient;


    /**
     * Constructeurs
     */
    // Récupération Base de données
    public Hebergement(String nom_etablissement, String ville, int nombre_chambres, int nombre_places, int prix, int distanceCentre, int Wifi, int Menage, int Fumeur, int idhebergement, double promo) {
        this.nom_etablissement = nom_etablissement;
        this.ville = ville;
        this.nombre_chambres = nombre_chambres;
        this.nombre_places = nombre_places;
        this.prix = prix;
        this.distanceCentre = distanceCentre;
        this.wifi = Wifi;
        this.menage = Menage;
        this.fumeur = Fumeur;
        this.idhebergement = idhebergement;
        this.promo = promo;
        reducClient=false;
        compte_hebergement++;
    }

    /**
     * METHODES
     */

    public void afficherReservation() throws SQLException, ClassNotFoundException {

        DataCo dataCo = new DataCo();
        ArrayList<Reservation> ListeReservation = dataCo.SQL_Data_Reservation();

        // Garde que les reservation dans cet hebergement
        for (int i = 0; i < ListeReservation.size(); i++) {
            if (this.idhebergement != ListeReservation.get(i).getId_hebergement()) {
                ListeReservation.remove(i);
                i--;
            }
        }
        // affiche les resa
        for (int i = 0; i < ListeReservation.size(); i++) {
            System.out.println("     " + (i + 1) + ". " + ListeReservation.get(i).getDebut() + " ---> " + ListeReservation.get(i).getFin());
        }

    }
    public void setReducClient(){
        reducClient=true;
    }
    public boolean getReducClient(){
        return reducClient;
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

    public double getPrix() {
        return prix;
    }

    public void setPrix(double Promo) {
        this.prix *= Promo * promo;
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


    public String getWifi() {
        if (wifi == 1)
            return "oui";
        else
            return "non";
    }

    public void setWifi(int wifi) {
        this.wifi = wifi;
    }

    public String getMenage() {
        if (menage == 1)
            return "oui";
        else
            return "non";
    }

    public void setMenage(int menage) {
        this.menage = menage;
    }

    public String getFumeur() {
        if (fumeur == 1)
            return "oui";
        else
            return "non";
    }

    public void setFumeur(int fumeur) {
        this.fumeur = fumeur;
    }

    public double getPromo() {
        return promo;
    }
}