package com.example.ecebooking.Controllers.Hebergements;

import java.util.ArrayList;

public class Hebergement {

    private String nom_etablissement;
    private String ville;
    private int nombre_chambres;
    private int nombre_places;
    private int prix;
    private int distanceCentre;
    private int idhebergement;
   /* private ArrayList<String> option;
    private ArrayList<String> activite;
    private ArrayList<String> reservation;*/

    public Hebergement(String nom_etablissement, String ville, int nombre_chambres, int nombre_places, int prix, int distanceCentre, int idhebergement) {
        this.nom_etablissement = nom_etablissement;
        this.ville = ville;
        this.nombre_chambres = nombre_chambres;
        this.nombre_places = nombre_places;
        this.prix = prix;
        this.distanceCentre = distanceCentre;
        this.idhebergement = idhebergement;
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
}
