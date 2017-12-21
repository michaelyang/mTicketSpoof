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
    public Date activated;
    public Date used;
    public Date purchased;
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
        this.ticketNumber = ticketNumber;
        this.fareType = fareType;
        this.price = price;
        this.origin = origin;
        this.destination = destination;
        this.purchased = Calendar.getInstance().getTime();
        this.activated = null;
        this.used = null;
        this.paymentMethod = paymentMethod;
        this.active = false;
        this.zone = zone;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }
    public String getFareType() { return fareType; }
    public Boolean getActive() { return active; }
    public Date getActivated() {return activated;}
    public void activate() {
        activated = Calendar.getInstance().getTime();
        active = true;
    }
}
