package com.example.ecebooking.Controllers.Hebergements;

import java.util.ArrayList;

public class Hebergement {

    private String nom;
    private String pays;
    private String ville;
    private String adresse;
    private int nbPlace;
    private int nbChambre;
    private int tarif;
    private int distanceCentre;
    private ArrayList<String> option;
    private ArrayList<String> activite;
    private ArrayList<String> reservation;

    public Hebergement(String name, String contry, String city, String adress, int place, int nbRoom, int cost, int center)
    {
        nom = name;
        pays = contry;
        ville = city;
        adresse = adress;
        nbPlace = place;
        nbChambre = nbRoom;
        tarif = cost;
        distanceCentre = center;
    }

}
