package application;

import model.entities.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws ParseException {
        /**
         * Regras de validação:
         * 1 - a data de check-out deve ser depois do check-in (no mínimo 1 noite)
         * 2 - na atualização das dadas deve ser verificado se ambas são datas futuras, não pode agendar data que já passou
         *
         */

        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        System.out.print("Room number: ");
        int roomNumber = sc.nextInt();

        System.out.println("Check-in date (dd/MM/yyyy): ");
        Date checkin = sdf.parse(sc.next());

        System.out.println("Check-out date (dd/MM/yyyy): ");
        Date checkout = sdf.parse(sc.next());

        Reservation reservation = new Reservation(roomNumber, checkin, checkout);

        System.out.println(reservation);

        sc.close();
    }
}
