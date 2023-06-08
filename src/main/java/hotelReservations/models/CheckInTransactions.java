package hotelReservations.models;

import java.sql.Timestamp;

public class CheckInTransactions {

    private int checkInTransID; //auto
    private Timestamp checkInDateTime;
    private Reservations reservations;
    private PaymentDetails paymentDetails;
    private FrontDeskAgents frontDeskAgents;

    public CheckInTransactions(){}

    public CheckInTransactions(Timestamp checkInDateTime, Reservations reservations,
                               PaymentDetails paymentDetails, FrontDeskAgents frontDeskAgents) {
        this.checkInDateTime = checkInDateTime;
        this.reservations = reservations;
        this.paymentDetails = paymentDetails;
        this.frontDeskAgents = frontDeskAgents;
    }

    public int getCheckInTransID() {
        return checkInTransID;
    }

    public void setCheckInTransID(int checkInTransID) {
        this.checkInTransID = checkInTransID;
    }

    public Timestamp getCheckInDateTime() {
        return checkInDateTime;
    }

    public void setCheckInDateTime(Timestamp checkInDateTime) {
        this.checkInDateTime = checkInDateTime;
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

    public FrontDeskAgents getFrontDeskAgents() {
        return frontDeskAgents;
    }

    public void setFrontDeskAgents(FrontDeskAgents frontDeskAgents) {
        this.frontDeskAgents = frontDeskAgents;
    }
}
