package com.example.ecebooking;

import com.example.ecebooking.Controllers.Client.Client;
import com.example.ecebooking.Controllers.Client.Invite;
import com.example.ecebooking.Controllers.Hebergements.Hebergement;
import com.example.ecebooking.Controllers.LoginController;
import com.example.ecebooking.Models.Model;
import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class App extends Application {

    @Override
    public void start(Stage stage) throws SQLException, ClassNotFoundException {
        Model.getInstance().getViewFactory().LoginView();
        ArrayList<Hebergement> hebergementListe = new ArrayList<>();
        //ArrayList<Hebergement> COListe = new ArrayList<>();
        /*LoginController loginControl = new LoginController();
        loginControl.SQL_Data_Hebergements(hebergementListe);
        loginControl.afficherListeHebergements(hebergementListe);*/

        String choix;
        Scanner clavier = new Scanner(System.in);

        do{
            System.out.println("\n=======Menu======\n");
            System.out.println("0. Quitter");
            System.out.println("1. Inviter");
            System.out.println("2. Se connecter (pas fonctionnel)");
            System.out.print("\nsaisir menu : ");
            choix = clavier.next();

            switch (choix) {
                case "0" -> System.out.println("Merci");
                case "1" -> {
                    Invite testI = new Invite(hebergementListe);
                    testI.menu();
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

