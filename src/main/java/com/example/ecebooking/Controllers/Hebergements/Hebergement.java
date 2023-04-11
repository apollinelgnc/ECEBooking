package com.example.ecebooking.Controllers.Hebergements;

import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.Scanner;

public class Hebergement {

    static int compte_hebergement;
    public Label activites;
    public Label options;
    public Label distance;
    public Label destination;
    public Label type;
    public Label nom_hebergement;
    private String nom_etablissement;
    private String ville;
    private int nombre_chambres;
    private int nombre_places;
    private int prix;
    private int distanceCentre;
    private int idhebergement;
    private ArrayList<String> option;
    private ArrayList<String> activite;
    private ArrayList<String> reservation;

    /** Constructeurs */
    // Récupération Base de données
    public Hebergement(String nom_etablissement, String ville, int nombre_chambres, int nombre_places, int prix, int distanceCentre, int idhebergement) {
        this.nom_etablissement = nom_etablissement;
        this.ville = ville;
        this.nombre_chambres = nombre_chambres;
        this.nombre_places = nombre_places;
        this.prix = prix;
        this.distanceCentre = distanceCentre;
        this.idhebergement = idhebergement;
        compte_hebergement ++;
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
}
