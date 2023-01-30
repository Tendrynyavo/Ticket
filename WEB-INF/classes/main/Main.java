package main;

import client.Client;
import event.Evenement;
import place.Place;
import place.Placement;
import reservation.Reservation;
import zone.Zone;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class Main {
    
    public static void main(String[] args) throws Exception {
        // Evenement evenement = new Evenement();
        // evenement.setIdEvenement("EVE0001");
        // Client client = new Client();
        // client.setIdClient("CLI0002");
        // client.setEvenement(evenement);
        // client.reserver("B1;B2", "2023-01-19");
        // Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        // Calendar calendar = Calendar.getInstance();
        // calendar.setTime(timestamp);
        // calendar.add(Calendar.HOUR, 3);
        // timestamp = new Timestamp(calendar.getTime().getTime());
        // System.out.println(timestamp);


        // Timestamp timestamp1 = new Timestamp(System.currentTimeMillis());
        // Timestamp timestamp2 = new Timestamp(System.currentTimeMillis());

        // int comparisonResult = timestamp1.compareTo(timestamp2);

        // if (comparisonResult == 0) {
        //     System.out.println("The timestamps are equal.");
        // } else if (comparisonResult < 0) {
        //     System.out.println("Timestamp 1 is less than Timestamp 2.");
        // } else {
        //     System.out.println("Timestamp 1 is greater than Timestamp 2.");
        // }
        Reservation reservation = Reservation.getReservationById("RES0002");
        reservation.confirme();
    }
}