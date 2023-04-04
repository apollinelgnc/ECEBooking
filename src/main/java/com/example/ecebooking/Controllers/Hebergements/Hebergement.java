package com.example.ecebooking.Controllers.Hebergements;

import java.util.ArrayList;

public class Hebergement {

    final String nom;
    final String pays;
    final String ville;
    final String adresse;
    final int nbPlace;
    final int nbChambre;
    final int tarif;
    final int distanceCentre;
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
