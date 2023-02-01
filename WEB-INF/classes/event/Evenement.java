package event;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import connection.BddObject;
import payement.Total;
import place.Place;
import zone.Zone;

public class Evenement extends BddObject<Evenement> {

/// FIELD
    String idEvenement;
    String nom;
    int nombre;
    Timestamp date;
    double prix;
    Total total;
    Zone[] zones;

/// SETTERS
    public void setTotal(Total total) {
        this.total = total;
    }
    public void setPrix(double prix) throws IllegalArgumentException {
        if (prix < 0) throw new IllegalArgumentException("Prix est invalide");
        this.prix = prix;
    }
    public void setIdEvenement(String idEvenement) {
        this.idEvenement = idEvenement;
    }
    public void setNom(String nom) throws IllegalArgumentException {
        // Regular expression to match valid names
        String nameRegex = "^[a-zA-Z\\s]*$";
        if (!nom.matches(nameRegex)) throw new IllegalArgumentException("Nom de l'evenement invalide");
        this.nom = nom;
    }
    public void setZones(Zone[] zones) {
        this.zones = zones;
    }
    public void setNombre(int nombre) throws IllegalArgumentException {
        if (nombre < 0) throw new IllegalArgumentException("Nombre invalide");
        this.nombre = nombre;
    }
    public void setNombre(String nombre) throws Exception {
        setNombre(Integer.parseInt(nombre));
    }
    public void setDate(Timestamp date) {
        this.date = date;
    }

/// GETTERS
    public double getPrix() {
        return prix;
    }
    public String getIdEvenement() {
        return idEvenement;
    }
    public String getNom() {
        return nom;
    }
    public Zone[] getZones() throws Exception {
        if (zones == null) this.charger();
        return zones;
    }
    public int getNombre() {
        return nombre;
    }
    public Timestamp getDate() {
        return date;
    }
    public int getDifference() throws Exception {
        return getNombre() - getPaymentTotal();
    }

/// CONSTRUCTORS
    public Evenement() {
        setTable("evenement");
        setCountPK(7);
        setPrefix("EVE");
        setFunctionPK("nextval('seqevenement')");
    }

    public Evenement(String nom, int nombre) throws Exception {
        this();
        setIdEvenement(buildPrimaryKey(getPostgreSQL()));
        setNom(nom);
        setNombre(nombre);
    }
    
    public Evenement(String nom, String nombre,String date) throws Exception {
        this();
        setIdEvenement(buildPrimaryKey(getPostgreSQL()));
        setNom(nom);
        setNombre(nombre);
        setDate(Timestamp.valueOf(date));
    }

/// FUNCTION
    public static Evenement[] getAllEvenements() throws Exception {
        return new Evenement().getData(getPostgreSQL(), null);
    }

    public static Evenement getEventById(String id) throws Exception {
        Evenement event = new Evenement();
        event.setIdEvenement(id);
        return event.getData(getPostgreSQL(), null, "idEvenement")[0];
    }

    public void charger() throws Exception {
        Zone zone = new Zone();
        zone.setTable("zone_event");
        zone.setEvenement(this);
        Zone[] zones = zone.getData(getPostgreSQL(), null, "evenement");
        for (int i = 0; i < zones.length; i++) {
            zones[i].setEvenement(this);
        }
        setZones(zones);
    }

    public Place[] convertToPlace(String value) throws Exception {
        String[] numeros = splitNumero(value);
        ArrayList<Place> places = new ArrayList<>();
        for (Zone zone : getZones()) {
            for (Place place : zone.getPlaces()) {
                for (String numero : numeros) {
                    if (place.getNumero().equals(numero)) {
                        if (!place.isLibre()) throw new Exception("La place "+ place.getNumero() + " n'est pas disponible");
                        if (place.isConfirme()) throw new Exception("La place "+ place.getNumero() + " est déjà confirmée");
                        places.add(place);
                    }
                }
            }
        }
        return places.toArray(new Place[places.size()]);
    }

    public String[] splitNumero(String value) throws Exception {
        ArrayList<String> numeros = new ArrayList<>();
        ArrayList<String> allNumeros = getAllPlaceOrder();
        for (String numero : value.split(";")) {
            if (numero.contains(":")) {
                for (String place : this.untilPlace(numero, allNumeros)) {
                    add(numeros, allNumeros, place);
                }
            } else {
                add(numeros, allNumeros, numero);
            }
        }
        return numeros.toArray(new String[numeros.size()]);
    }

    void add(ArrayList<String> numeros, ArrayList<String> allNumeros, String place) throws Exception {
        if (!allNumeros.contains(place)) throw new Exception("La place "+ place + " n'existe pas");
        if (numeros.contains(place)) throw new Exception("La place " + place + " est déjà selectionnée");
        numeros.add(place);
    }

/// Fonction pour avoir tous les numeros des places
    public ArrayList<String> getAllPlaceOrder() throws Exception {
        ArrayList<String> numeros = new ArrayList<>();
        for (Zone zone : getZones()) {
            for (Place place : zone.getPlaces()) {
                numeros.add(place.getNumero());
            }
        }
        return numeros;
    }

/// Fonction pour avoir les places entre deux intervalles de places
    public String[] untilPlace(String numero, List<String> list) throws Exception {
        String[] numeros = numero.split(":");
        int first = list.indexOf(numeros[0]); // Debut de l'intervalle
        int end = list.indexOf(numeros[1]); // Fin de l'intervalle
        if (first < 0 || end < 0) throw new Exception("Cette intervalle " + numeros[0] + " vers " + numeros[1] + " n'est pas valable");
        list = list.subList(first, end + 1);
        return list.toArray(new String[list.size()]);
    }

    public int getPaymentTotal() throws Exception {
        Total total = new Total();
        total.setTable("total");
        total.setEvenement(this);
        Total[] totals = total.getData(getPostgreSQL(), null, "evenement");
        return (totals.length <= 0) ? 0 : totals[0].getTotal();
    }
}