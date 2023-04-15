package com.example.ecebooking.Controllers.Hebergements;

import java.sql.SQLException;

public class Hebergement {

   // static int compte_hebergement;
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

        public Hebergement(String nom_etablissement, String ville, int nombre_chambres, int nombre_places, int prix, int distanceCentre, int wifi, int menage, int fumeur, int id) throws SQLException, ClassNotFoundException {
        this.nom_etablissement = nom_etablissement;
        this.ville = ville;
        this.nombre_chambres = nombre_chambres;
        this.nombre_places = nombre_places;
        this.prix = prix;
        this.distanceCentre = distanceCentre;
        this.wifi = wifi;
        this.menage = menage;
        this.fumeur = fumeur;
        this.idhebergement = id;
        }

    public String getNom_etablissement() {
        return nom_etablissement;
    }

    public String getVille() {

        return ville;
    }

    public int getPrix() {
        return prix;
    }
    public String getPrixStrg(){
            return prix + "€";
    }
    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getIdhebergement() {
        return idhebergement;
    }


    public int getFumeur() {
        return fumeur;
    }

}
