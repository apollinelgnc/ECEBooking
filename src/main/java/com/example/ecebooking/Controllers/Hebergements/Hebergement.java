package com.example.ecebooking.Controllers.Hebergements;

import java.util.ArrayList;
import java.util.Scanner;

public class Hebergement {

    static int compte_hebergement;
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

    public Hebergement()
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
