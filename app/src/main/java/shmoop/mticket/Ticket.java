package shmoop.mticket;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Michael on 12/16/2017.
 */

public class Ticket implements Serializable {
    public String ticketNumber;
    public String fareType;
    public double price;
    public String origin;
    public String destination;
    public Date activatedAt;
    public Date usedAt;
    public Date purchasedAt;
    public Date validUntil;
    public String paymentMethod;
    public String zone;
    private Boolean active;

    public Ticket() {
        this.ticketNumber = "";
        this.fareType = "";
        this.price = 0.0;
        this.origin = "";
        this.destination = "";
        this.active = false;
    }

    public Ticket(String ticketNumber, String fareType, double price, String origin, String destination, String paymentMethod, String zone) {
        Calendar c = Calendar.getInstance();
        this.ticketNumber = ticketNumber;
        this.fareType = fareType;
        this.price = price;
        this.origin = origin;
        this.destination = destination;
        this.purchasedAt = c.getTime();
        this.activatedAt = null;
        this.usedAt = null;
        c.add(Calendar.MONTH, 3);
        this.validUntil = c.getTime();
        this.paymentMethod = paymentMethod;
        this.active = false;
        this.zone = zone;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }
    public String getFareType() { return fareType; }
    public Boolean getActive() { return active; }
    public Date getActivatedAt() { return activatedAt; }
    public Date getValidUntil() { return validUntil; }
    public void activate() {
        Calendar c = Calendar.getInstance();
        activatedAt = c.getTime();
        active = true;
        c.add(Calendar.HOUR, 3);
        usedAt = c.getTime();
    }
}
