package com.example.ecebooking.Controllers.Admin;

import com.example.ecebooking.Controllers.Client.Client;
import com.example.ecebooking.Controllers.Hebergements.Hebergement;

public class Admin extends Client {

    public Admin (String nom,int numero,String id,String mdp) {
        this.nom = nom;
        this.id = id;
        this.mdp = mdp;
        this.numero = numero;
        reduction = 0.9;
    }

    public void creerHebergement()
    {
        Hebergement nouveau = new Hebergement();
    }
}
