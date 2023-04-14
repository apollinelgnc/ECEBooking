package com.example.ecebooking.Controllers.Client;

import com.example.ecebooking.Controllers.Reservation;
import com.example.ecebooking.Models.DataCo;
import com.example.ecebooking.Controllers.Hebergements.Hebergement;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class Invite {

    /** CONSTRUCTEURS */
    public Invite() {}

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

    public void reserver() throws SQLException, ClassNotFoundException
    {
        int jour_debut, mois_debut, annee_debut;
        int jour_fin, mois_fin, annee_fin;
        double prix;
        boolean valide;

        Scanner clavier = new Scanner(System.in);
        int choix;

        // Liste des hébergements de la BDD après le filtre
        ArrayList<Hebergement> ListeHebergement = filtrer();
        if (ListeHebergement == null)
        {
            return;
        }

        // Choix de l'hébergement pour la réservation
        do {
            System.out.println("===== Resultat Filtre =====\n");
            System.out.println("0. Quitter");
            for(int i=1; i<=ListeHebergement.size(); i++)
            {
                System.out.println(i +". " + ListeHebergement.get(i-1).getNom_etablissement());
            }
            System.out.print("Saisir menu : ");
            choix = clavier.nextInt();

        }while(ListeHebergement.size() < choix || choix < 0);

        if(choix == 0)
        {
            return;
        }


        // Choix des dates
        System.out.println("===== Choix Date =====\n");
        System.out.print("jour de debut : ");
        jour_debut = clavier.nextInt();
        System.out.print("mois de debut : ");
        mois_debut = clavier.nextInt();
        System.out.print("annee de debut : ");
        annee_debut = clavier.nextInt();
        //Dates debut = new Dates (jour_debut, mois_debut, annee_debut);
        LocalDate debut = LocalDate.of(annee_debut, mois_debut, jour_debut);

        System.out.print("\njour de fin : ");
        jour_fin = clavier.nextInt();
        System.out.print("mois de fin : ");
        mois_fin = clavier.nextInt();
        System.out.print("annee de fin : ");
        annee_fin = clavier.nextInt();
        //Dates fin = new Dates(jour_fin, mois_fin, annee_fin);
        LocalDate fin = LocalDate.of(annee_fin, mois_fin, jour_fin);
        //Calcul du prix de la reservation = nb jours * prix/jour de l'hebergement
        prix = ChronoUnit.DAYS.between(debut, fin) * ListeHebergement.get(choix-1).getPrix();

        Reservation nouveau = creerReservation(ListeHebergement.get(choix-1).getIdhebergement(), debut, fin, prix);//new Reservation(ListeHebergement.get(choix-1).getIdhebergement(), -1, debut, fin);

        // Verification disponibilité date
        valide = nouveau.verification();

        if(valide)
        {
            DataCo dataco = new DataCo();
            dataco.Data_Creation_Reservation(nouveau);
            System.out.println("Validée");
            nouveau.afficher();
        }
        else System.out.println("Refusée");
    }

    // Permet de creer une reservation avec l'ID du client ou -1 pour les invités
    public Reservation creerReservation(int i, LocalDate debut, LocalDate fin, double prix)
    {
        return new Reservation(i,-1,debut,fin,prix);
    }

    // Récupère tous les hebergement dans la BDD qui correspondent au filtre. Renvoie la liste en ArrayList
    public ArrayList<Hebergement> filtrer() throws SQLException, ClassNotFoundException {

        DataCo dataco = new DataCo();

        StringBuilder request = new StringBuilder("SELECT * FROM `etablissement`");
        ArrayList<String> filtre = new ArrayList<>();

        String nom_etablissement_filtre = "";
        String ville_filtre = "";
        int nombre_chambres_filtre = 0;
        int nombre_places_filtre = 0;
        int prix_filtre = 0;
        int distanceCentre_filtre = 0;
        String wifi_filtre = "non";
        String menage_filtre = "non";
        String  fumeur_filtre = "non";


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
            System.out.println("7. Filtre wifi : " + wifi_filtre);
            System.out.println("8. Filtre menage : " + menage_filtre);
            System.out.println("9. Filtre fumeur : " + fumeur_filtre);
            System.out.println("10. Valider le filtre");
            System.out.print("\nsaisir choix: ");
            choix = clavier.next();

            switch (choix) {
                //quitter
                case "0" -> {
                    System.out.println("Merci");
                    return null;
                }

                // nom hebergement
                case "1" -> {
                    System.out.print("Veuillez saisir le nom : ");
                    nom_etablissement_filtre = clavier.next();
                    filtre.add(" nom = '" + nom_etablissement_filtre +"'");
                }
                // ville
                case "2" -> {
                    System.out.print("Veuillez saisir la ville : ");
                    ville_filtre = clavier.next();
                    filtre.add(" ville = '" + ville_filtre +"'");
                }
                // chambre
                case "3" -> {
                    System.out.print("Veuillez saisir le nombre de chambre : ");
                    nombre_chambres_filtre = clavier.nextInt();
                    filtre.add(" nbChambre  >= '" + nombre_chambres_filtre + "'");
                }
                // place
                case "4" -> {
                    System.out.print("Veuillez saisir le nombre de place : ");
                    nombre_places_filtre = clavier.nextInt();
                    filtre.add(" nbPlace  >= '" + nombre_places_filtre + "'");
                }
                // prix
                case "5" -> {
                    System.out.print("Veuillez saisir le prix : ");
                    prix_filtre = clavier.nextInt();
                    filtre.add(" prix  <= '" + prix_filtre + "'");
                }
                case "6" -> {
                    System.out.print("Veuillez saisir la distance au centre : ");
                    distanceCentre_filtre = clavier.nextInt();
                    filtre.add(" distanceCentre <= '" + distanceCentre_filtre + "'");
                }
                case "7" -> {
                    wifi_filtre = "oui";
                    filtre.add(" wifi = '1'");
                }
                case "8" -> {
                    menage_filtre = "oui";
                    filtre.add(" menage = '1'");
                }
                case "9" -> {
                    fumeur_filtre = "oui";
                    filtre.add(" fumeur = '1'");
                }
                case "10" -> System.out.println("Filtre valide");
                default -> {
                }
            }

        } while (!choix.equals("10"));

        /*Fin Filtre */

        if(filtre.size() > 0)
        {
            request.append(" WHERE").append(filtre.get(0));
            for (int i=1; i<filtre.size(); i++)
            {
                request.append(" &&").append(filtre.get(i));
            }
        }

        return dataco.SQL_Data_Hebergements(request.toString());
    }
}