package com.example.ecebooking.Controllers.Client;

public class Client {
    protected String nom;

    public Client(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
