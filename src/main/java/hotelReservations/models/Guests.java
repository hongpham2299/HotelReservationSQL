package hotelReservations.models;

import java.util.ArrayList;
import java.util.List;

public class Guests {

    private int guestID;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private List<Reservations> reservationsList = new ArrayList<>();
    private List<CancelledReservations> cancelledReservationsList = new ArrayList<>();

    public Guests(){}

    public Guests (String firstName, String lastName, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    public int getGuestID() {
        return guestID;
    }

    public void setGuestID(int guestID) {
        this.guestID = guestID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Reservations> getReservationsList() {
        return reservationsList;
    }

    public void setReservationsList(List<Reservations> reservationsList) {
        this.reservationsList = reservationsList;
    }

    public List<CancelledReservations> getCancelledReservationsList() {
        return cancelledReservationsList;
    }

    public void setCancelledReservationsList(List<CancelledReservations> cancelledReservationsList) {
        this.cancelledReservationsList = cancelledReservationsList;
    }

    @Override
    public String toString() {
        return "Customers {" +
                "guestID=" + guestID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}' + "\n" ;
    }
}

