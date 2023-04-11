package com.example.ecebooking.Controllers.Client;

import com.example.ecebooking.Controllers.Hebergements.Hebergement;

import java.util.ArrayList;
import java.util.Scanner;

public class Client extends Invite{

    static int compteur_client; // nombre de client total
    protected String nom;
    protected String id;
    protected String mdp;
    protected int numero; // numero specific du client
    protected double reduction;

    /** CONSTRUCTEURS */

    // création par l'utilisateur
   public Client(ArrayList<Hebergement> hebergementListe){
        super(hebergementListe);
    }

    // constructeur base de donnees
    public Client (String nom,String id,String mdp,int numero, ArrayList<Hebergement> hebergementListe) {
        super(hebergementListe);
        this.nom = nom;
        this.id = id;
        this.mdp = mdp;
        this.numero = numero;
        reduction = 0.9;
        compteur_client++;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id='" + id + '\'' +
                ", mdp='" + mdp + '\'' +
                ", numero=" + numero +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }



    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
}
