package com.example.ecebooking;

import com.example.ecebooking.Controllers.Admin.Admin;
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


        ArrayList<Client> COListe = new ArrayList<>();
        ArrayList<Admin> ADListe = new ArrayList<>();
        ArrayList<Hebergement> hebergementListe = new ArrayList<>();

        LoginController loginControl = new LoginController();

        loginControl.SQL_Data_Login(COListe);
        //loginControl.afficherListeClient(COListe);

        loginControl.SQL_Data_Admin(ADListe);
        //loginControl.afficherListeAdmin(ADListe);

        /*loginControl.SQL_Data_Hebergements(hebergementListe);
        loginControl.afficherListeHebergements(hebergementListe);*/

        String choix;
        Scanner clavier = new Scanner(System.in);

        do{
            System.out.println("\n=======Menu======\n");
            System.out.println("0. Quitter");
            System.out.println("1. Inviter");
            System.out.println("2. Se connecter Client");
            System.out.println("3. Se creer un compte Client");
            System.out.println("4. Se connecter Admin");
            System.out.print("\nsaisir menu : ");
            choix = clavier.next();

            switch (choix) {
                case "0" -> System.out.println("Merci");
                case "1" -> {
                    Invite testI = new Invite(hebergementListe);
                    testI.menu();
                }
                case "2" -> {
                    Scanner clavier2 = new Scanner(System.in);
                    int buff=0;
                    do {

                        System.out.print("Veuillez saisir votre pseudo : ");
                        String id = clavier2.nextLine();

                        System.out.print("Veuillez saisir votre mdp : ");
                        String mdp = clavier2.nextLine();

                        for (Client client : COListe) {
                            if ((id.equals(client.getId())) && (mdp.equals(client.getMdp()))) {

                                Client ConnexionClient = new Client(client.getNom(), id, mdp, client.getNumero(), hebergementListe);
                                ConnexionClient.menu();
                            }
                        }
                    } while (buff == 0);

                }
                case "3" ->{
                    Scanner clavier2 = new Scanner(System.in);

                    System.out.print("Veuillez saisir votre nom : ");
                    String nom = clavier2.nextLine();

                    System.out.print("Veuillez saisir votre pseudo : ");
                    String id = clavier2.nextLine();

                    System.out.print("Veuillez saisir votre mdp : ");
                    String mdp = clavier2.nextLine();

                    int numClient=10;
                    loginControl.Data_Creation_Login(nom, id, mdp, numClient);
                }
                case "4" ->{
                    Scanner clavier3 = new Scanner(System.in);
                    int buff2=0;
                    do {

                        System.out.print("Veuillez saisir votre pseudo ADMIN : ");
                        String idA = clavier3.nextLine();

                        System.out.print("Veuillez saisir votre mdp ADMIN : ");
                        String mdpA = clavier3.nextLine();

                        for (Admin ad : ADListe) {
                            if ((idA.equals(ad.getIdA())) && (mdpA.equals(ad.getMdpA()))) {

                                Admin ConnexionAdmin = new Admin(ad.getNomA(), idA, mdpA, ad.getNumeroA(), hebergementListe);
                                ConnexionAdmin.menu();
                            }
                        }
                    } while (buff2 == 0);

                }
                default -> {
                }
            }
        }while(!choix.equals("0"));

    }

}

