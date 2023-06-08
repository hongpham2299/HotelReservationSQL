package hotelReservations.models;

import java.sql.Timestamp;

public class CheckOutTransactions {

    private int checkOutTransID; //auto
    private Timestamp checkOutDateTime;
    private Reservations reservations;
    private PaymentDetails paymentDetails;
    private Billings billings;
    private FrontDeskAgents frontDeskAgents;

    private CheckOutTransactions(){}

    public CheckOutTransactions(Timestamp checkOutDateTime, Reservations reservations,
                                PaymentDetails paymentDetails, Billings billings, FrontDeskAgents frontDeskAgents) {
        this.checkOutDateTime = checkOutDateTime;
        this.reservations = reservations;
        this.paymentDetails = paymentDetails;
        this.billings = billings;
        this.frontDeskAgents = frontDeskAgents;
    }

    public int getCheckOutTransID() {
        return checkOutTransID;
    }

    public void setCheckOutTransID(int checkOutTransID) {
        this.checkOutTransID = checkOutTransID;
    }

    public Timestamp getCheckOutDateTime() {
        return checkOutDateTime;
    }

    public void setCheckOutDateTime(Timestamp checkOutDateTime) {
        this.checkOutDateTime = checkOutDateTime;
    }

    public Reservations getReservations() {
        return reservations;
    }

    public void setReservations(Reservations reservations) {
        this.reservations = reservations;
    }

    public PaymentDetails getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(PaymentDetails paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public Billings getBillings() {
        return billings;
    }

    public void setBillings(Billings billings) {
        this.billings = billings;
    }

    public FrontDeskAgents getFrontDeskAgents() {
        return frontDeskAgents;
    }

    public void setFrontDeskAgents(FrontDeskAgents frontDeskAgents) {
        this.frontDeskAgents = frontDeskAgents;
    }
}
