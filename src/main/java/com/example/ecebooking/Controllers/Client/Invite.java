package com.example.ecebooking.Controllers.Client;

import java.util.Scanner;

public class Invite {

    public Invite() {
    }

    public void menu() {
        String choix;
        Scanner clavier = new Scanner(System.in);

        do {
            System.out.println("\n======= Menu Invite ======\n");
            System.out.println("0. Quitter");
            System.out.println("1. Faire une reservation");
            System.out.println("2. Se connecter");
            System.out.print("\nsaisir menu : ");
            choix = clavier.next();

            switch (choix) {
                case "0" -> System.out.println("Merci");
                case "1" -> this.reserver();
                case "2" -> {
                    Client testC = new Client();
                    testC.menu();
                }
                default -> {
                }
            }
        } while (!choix.equals("0"));
    }

    public void reserver() {

        String nom_etablissement_filtre = "";
        String ville_filtre = "";
        int nombre_chambres_filtre = 0;
        int nombre_places_filtre = 0;
        int prix_filtre = 0;
        int distanceCentre_filtre = 0;

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
            System.out.println("7. Valider le filtre");
            System.out.print("\nsaisir choix: ");
            choix = clavier.next();

            switch (choix) {
                case "0" -> System.out.println("Merci");
                case "1" -> {
                    System.out.print("Veuillez saisir le nom : ");
                    nom_etablissement_filtre = clavier.next();
                }
                case "2" -> {
                    System.out.print("Veuillez saisir la ville : ");
                    ville_filtre = clavier.nextLine();
                }
                case "3" -> {
                    System.out.print("Veuillez saisir le nombre de chambre : ");
                    nombre_chambres_filtre = clavier.nextInt();
                }
                case "4" -> {
                    System.out.print("Veuillez saisir le nombre de place : ");
                    nombre_places_filtre = clavier.nextInt();
                }
                case "5" -> {
                    System.out.print("Veuillez saisir le prix : ");
                    prix_filtre = clavier.nextInt();
                }
                case "6" -> {
                    System.out.print("Veuillez saisir la distance au centre : ");
                    distanceCentre_filtre = clavier.nextInt();
                }
                case "7" -> System.out.print("Filtre valide");
                default -> {
                }
            }
        } while (!choix.equals("0") && !choix.equals("7"));
    }

}
