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
        Scanner clavier = new Scanner(System.in);

        compteur_client++;
        numero = compteur_client;
        reduction = 0.9;

        System.out.print("Veuillez saisir votre pseudo : ");
        id = clavier.nextLine();
        System.out.print("Veuillez saisir votre mdp : ");
        mdp = clavier.nextLine();
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
}
