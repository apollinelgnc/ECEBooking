package com.example.ecebooking.Controllers.Client;

import com.example.ecebooking.Controllers.Hebergements.Hebergement;

import java.util.ArrayList;
import java.util.Scanner;

// Class qui ne sert à rien
// j'ai juste fait un main de dans pour faire mes tests

public class Membre {
    public static void main (String[] args) {

        ArrayList<Hebergement> hebergementListe = new ArrayList<>();
        hebergementListe.add(new Hebergement("Pourteau", "Royan", 13, 25, 150, 800, 1));
        hebergementListe.add(new Hebergement("Kerlano", "Quimper", 5, 10, 100, 700,2 ));
        hebergementListe.add(new Hebergement("272", "Paris", 4, 7, 250, 200, 3));

        String choix;
        Scanner clavier = new Scanner(System.in);

        do{
            System.out.println("\n=======Menu======\n");
            System.out.println("0. Quitter");
            System.out.println("1. Inviter");
            System.out.println("2. Se connecter");
            System.out.print("\nsaisir menu : ");
            choix = clavier.next();

            switch (choix) {
                case "0" -> System.out.println("Merci");
                case "1" -> {
                    Invite testI = new Invite(hebergementListe);
                    testI.reserver();
                    //testI.menu();
                }
                case "2" -> {
                    Client testC = new Client(hebergementListe);
                    testC.menu();
                }
                default -> {
                }
            }
        }while(!choix.equals("0"));
    }
}
