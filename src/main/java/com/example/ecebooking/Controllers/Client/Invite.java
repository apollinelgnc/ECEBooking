package com.example.ecebooking.Controllers.Client;

import com.example.ecebooking.Models.DataCo;
import com.example.ecebooking.Controllers.Hebergements.Hebergement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Invite {

    /** ATTRIBUTS */
    protected ArrayList<Hebergement> hebergementListe;

    /** CONSTRUCTEURS */
    public Invite(ArrayList<Hebergement> hebergementListe) {
        this.hebergementListe = hebergementListe;
    }

    /** METHODES */
    public void menu() throws SQLException, ClassNotFoundException {


        String choix;
        Scanner clavier = new Scanner(System.in);

        do {
            System.out.println("\n======= Menu Invite ======\n");
            System.out.println("0. Quitter");
            System.out.println("1. Faire une reservation");
            System.out.print("\nsaisir menu : ");
            choix = clavier.next();

            switch (choix) {
                case "0" -> System.out.println("Merci");
                case "1" -> reserver();
                default -> {
                }
            }
        } while (!choix.equals("0"));
    }

    public void reserver() throws SQLException, ClassNotFoundException {

        String nom_etablissement_filtre = "";
        String ville_filtre = "";
        int nombre_chambres_filtre = 0;
        int nombre_places_filtre = 0;
        int prix_filtre = 0;
        int distanceCentre_filtre = 0;

        ArrayList<Hebergement> resultat;
        String request = "SELECT * FROM `etablissement`";
        ArrayList<String> filtre = new ArrayList<>();

        DataCo dataco = new DataCo();

        Scanner clavier = new Scanner(System.in);
        String choix;

        do {
            System.out.println("\n======= Menu Reservation ======\n");
            System.out.println("0. Quitter");
            System.out.println("1. nom etablissement : " + nom_etablissement_filtre);
            System.out.println("2. ville : " + ville_filtre);
            System.out.println("3. nb chambre : " + nombre_chambres_filtre);
            System.out.println("4. nb place : " + nombre_places_filtre);
            System.out.println("5. prix : " + prix_filtre);
            System.out.println("6. distance centre : " + distanceCentre_filtre);
            System.out.println(request);
            System.out.println("7. Valider le filtre");
            System.out.print("\nsaisir choix: ");
            choix = clavier.next();

            switch (choix) {
                //quitter
                case "0" -> System.out.println("Merci");
                // nom hebergement
                case "1" -> {
                    System.out.print("Veuillez saisir le nom : ");
                    nom_etablissement_filtre = clavier.next();
                    filtre.add(" nom = '" + nom_etablissement_filtre +"'");
/*
                    for(int i=0; i<Filtre.size();i++)
                    {
                        if(!Filtre.get(i).getNom_etablissement().equals(nom_etablissement_filtre))
                        {
                            Filtre.remove(Filtre.get(i));
                            i--;
                        }
                    }*/
                }
                // ville
                case "2" -> {
                    System.out.print("Veuillez saisir la ville : ");
                    ville_filtre = clavier.next();
/*
                    for(int i=0; i<Filtre.size();i++)
                    {
                        if(!Filtre.get(i).getVille().equals(ville_filtre))
                        {
                            Filtre.remove(Filtre.get(i));
                            i--;
                        }
                    }*/
                }
                // chambre
                case "3" -> {
                    System.out.print("Veuillez saisir le nombre de chambre : ");
                    nombre_chambres_filtre = clavier.nextInt();
                    filtre.add(" nbChambre  >= '" + nombre_chambres_filtre + "'");
/*
                    for(int i=0; i<Filtre.size();i++)
                    {
                        if(Filtre.get(i).getNombre_chambres() < nombre_chambres_filtre)
                        {
                            Filtre.remove(Filtre.get(i));
                            i--;
                        }
                    }*/
                }
                // place
                case "4" -> {
                    System.out.print("Veuillez saisir le nombre de place : ");
                    nombre_places_filtre = clavier.nextInt();
/*
                    for(int i=0; i<Filtre.size();i++)
                    {
                        if(Filtre.get(i).getNombre_places() < nombre_places_filtre)
                        {
                            Filtre.remove(Filtre.get(i));
                            i--;
                        }
                    }*/
                }
                // prix
                case "5" -> {
                    System.out.print("Veuillez saisir le prix : ");
                    prix_filtre = clavier.nextInt();
/*
                    for(int i=0; i<Filtre.size();i++)
                    {
                        if(Filtre.get(i).getPrix() > prix_filtre)
                        {
                            Filtre.remove(Filtre.get(i));
                            i--;
                        }
                    }*/
                }
                case "6" -> {
                    System.out.print("Veuillez saisir la distance au centre : ");
                    distanceCentre_filtre = clavier.nextInt();
/*
                    for(int i=0; i<Filtre.size();i++)
                    {
                        if(Filtre.get(i).getDistanceCentre() > distanceCentre_filtre)
                        {
                            Filtre.remove(Filtre.get(i));
                            i--;
                        }
                    }*/
                }
                case "7" -> System.out.println("Filtre valide");
                default -> {
                }
            }

        } while (!choix.equals("0") && !choix.equals("7"));

        /*Fin Filtre */

        if(choix.equals("7"))
        {
            if(filtre.size() > 0)
            {
                request = request + " WHERE" + filtre.get(0);
                for (int i=1; i<filtre.size(); i++)
                {
                    request = request + " &&" + filtre.get(i);
                }
            }
            System.out.println(request);

            System.out.println("==== RESULTATS ====\n");
            System.out.println("Filtre");

            System.out.println("Liste");

        }
        resultat = dataco.SQL_Data_Hebergements(request);

        for(Hebergement h : resultat)
        {
            System.out.println(h.getNom_etablissement());
        }
    }

}
