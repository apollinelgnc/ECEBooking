package com.example.ecebooking.Controllers.Admin;

import com.example.ecebooking.Controllers.Client.Client;
import com.example.ecebooking.Controllers.Hebergements.Hebergement;

import java.util.ArrayList;

public class Admin extends Client {

    //static int compteur_client; // nombre de client total
    protected String nomA;
    protected String idA;
    protected String mdpA;
    protected int numeroA; // numero specific du client
    protected double reductionA;


    public Admin (String nom, String id, String mdp, int numero, ArrayList<Hebergement> hebergementListe) {
        super(hebergementListe);
        this.nomA = nom;
        this.idA = id;
        this.mdpA = mdp;
        this.numeroA = numero;
        reductionA = 0.9;
    }

    public String getIdA() {
        return idA;
    }

    public void setIdA(String id) {
        this.idA = id;
    }

    public String getMdpA() {
        return mdpA;
    }

    public void setMdpA(String mdp) {
        this.mdpA = mdp;
    }

    public int getNumeroA() {
        return numeroA;
    }

    public void setNumeroA(int numero) {
        this.numeroA = numero;
    }



    public String getNomA() {
        return nomA;
    }
    public void setNomA(String nom) {
        this.nomA = nom;
    }

    public void creerHebergement()
    {
        hebergementListe.add(new Hebergement());
    }
}
