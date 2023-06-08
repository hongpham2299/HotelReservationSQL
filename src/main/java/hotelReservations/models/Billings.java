package hotelReservations.models;

import java.math.BigDecimal;
import java.sql.Date;

public class Billings {

    private String accountID;
    private BigDecimal amount;
    private Date transactionDate;
    private PaymentDetails paymentDetails;
    private Guests guests;
    private PastReservations pastReservations;

    public Billings(){}

    public Billings(String accountID, BigDecimal amount, Date transactionDate, PaymentDetails paymentDetails,
                    Guests guests, PastReservations pastReservations) {
        this.accountID = accountID;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.paymentDetails = paymentDetails;
        this.guests = guests;
        this.pastReservations = pastReservations;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public PaymentDetails getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(PaymentDetails paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public Guests getGuests() {
        return guests;
    }

    public void setGuests(Guests guests) {
        this.guests = guests;
    }

    public PastReservations getPastReservations() {
        return pastReservations;
    }

    public void setPastReservations(PastReservations pastReservations) {
        this.pastReservations = pastReservations;
    }
}
