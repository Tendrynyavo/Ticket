package payement;

import connection.BddObject;
import connection.ForeignKey;
import event.Evenement;
import reservation.Reservation;

public class Total extends BddObject<Total> {
    
    @ForeignKey(column = "idEvenement", typeColumn = String.class)
    Evenement evenement;
    int total;

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }
    public void setTotal(int total) throws Exception {
        if (total < 0) throw new Exception("Total n'est pas valide");
        this.total = total;
    }

    public Evenement getEvenement() {
        return evenement;
    }
    public int getTotal() {
        return total;
    }

    public Total() {
        setTable("total");
    }
}
