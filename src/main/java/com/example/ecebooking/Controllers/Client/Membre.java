package com.example.ecebooking.Controllers.Client;

import com.example.ecebooking.Controllers.Reservation;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

// Class qui ne sert à rien
// j'ai juste fait un main de dans pour faire mes tests

public class Membre {
    public static void main (String[] args) throws SQLException, ClassNotFoundException {

        Invite test = new Invite();

        LocalDate A1 = LocalDate.of(2022, 5, 10);
        LocalDate A2 = LocalDate.of(2023, 12, 29);
        LocalDate B1 = LocalDate.of(2001, 5, 10);
        LocalDate B2 = LocalDate.of(2020, 5, 10);
        LocalDate C1 = LocalDate.of(2002, 7, 10);
        LocalDate C2 = LocalDate.of(2022, 5, 10);

        Reservation A = new Reservation(0,-1, A1,A2);
        Reservation B = new Reservation(0,-1, B1,B2);
        Reservation C = new Reservation(0,-1, C1,C2);

        ArrayList<Reservation> resa = new ArrayList<>();
        resa.add(A);
        resa.add(B);
        resa.add(C);

        test.reserver();
    }
}
