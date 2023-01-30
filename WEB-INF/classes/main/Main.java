package main;

import client.Client;
import event.Evenement;
import place.Place;
import place.Placement;
import zone.Zone;

public class Main {
    
    public static void main(String[] args) throws Exception {
        Evenement evenement = new Evenement();
        evenement.setIdEvenement("EVE0001");
        Client client = new Client();
        client.setIdClient("CLI0002");
        client.setEvenement(evenement);
        client.reserver("B1;B2", "2023-01-19");
    }
}
