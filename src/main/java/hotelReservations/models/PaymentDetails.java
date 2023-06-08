package hotelReservations.models;

public class PaymentDetails {

    private int paymentID;//auto
    private String cardHolderName;
    private String ccNumbers;
    private String expiration;
    private int cvvNum;
    private Guests guests;
    private Reservations reservations;
    private PastReservations pastReservations;

    public PaymentDetails(){}

    public PaymentDetails(String cardHolderName, String ccNumbers, String expiration, int cvvNum,
                          Guests guests, Reservations reservations) {
        this.paymentID = paymentID;
        this.cardHolderName = cardHolderName;
        this.ccNumbers = ccNumbers;
        this.expiration = expiration;
        this.cvvNum = cvvNum;
        this.guests = guests;
        this.reservations = reservations;

    }

    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCcNumbers() {
        return ccNumbers;
    }

    public void setCcNumbers(String ccNumbers) {
        this.ccNumbers = ccNumbers;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public int getCvvNum() {
        return cvvNum;
    }

    public void setCvvNum(int cvvNum) {
        this.cvvNum = cvvNum;
    }

    public Guests getGuests() {
        return guests;
    }

    public void setGuests(Guests guests) {
        this.guests = guests;
    }

    public Reservations getReservations() {
        return reservations;
    }

    public void setReservations(Reservations reservations) {
        this.reservations = reservations;
    }

    public PastReservations getPastReservations() {
        return pastReservations;
    }

    public void setPastReservations(PastReservations pastReservations) {
        this.pastReservations = pastReservations;
    }
}
