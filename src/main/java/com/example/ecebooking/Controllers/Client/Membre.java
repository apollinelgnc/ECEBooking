package com.example.ecebooking.Controllers.Client;

import com.example.ecebooking.Controllers.Hebergements.Hebergement;

import java.util.Scanner;

public class Membre {
    public static void main (String[] args) {

        //Hebergement test = new Hebergement("Pourteau", "Royan", 13, 25, 150, 800, 17600);

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
                    Invite testI = new Invite();
                    testI.menu();
                }
                case "2" -> {
                    Client testC = new Client();
                    testC.menu();
                }
                default -> {
                }
            }
        }while(!choix.equals("0"));
    }
}
