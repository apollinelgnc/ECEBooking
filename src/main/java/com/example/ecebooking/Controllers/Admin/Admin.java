package com.example.ecebooking.Controllers.Admin;

import com.example.ecebooking.Controllers.Client.Client;
import com.example.ecebooking.Controllers.Hebergements.Hebergement;

import java.util.ArrayList;

public class Admin extends Client {

    public Admin (String nom, int numero, String id, String mdp, ArrayList<Hebergement> hebergementListe) {
        super(hebergementListe);
        this.nom = nom;
        this.id = id;
        this.mdp = mdp;
        this.numero = numero;
        reduction = 0.9;
    }

    public void creerHebergement()
    {
        hebergementListe.add(new Hebergement());
    }
}
