package hotelReservations.models;

import java.sql.Timestamp;

public class CancelledReservations {

    private String cancellationNumber;
    private Timestamp cancellationDateTime;
    private Hotel hotel;
    private Guests guests;
    private Reservations reservations;

    public CancelledReservations(){}

    public CancelledReservations(String cancellationNumber, Timestamp cancellationDateTime, Hotel hotel,
                                 Guests guests, Reservations reservations) {
        this.cancellationNumber = cancellationNumber;
        this.cancellationDateTime = cancellationDateTime;
        this.hotel = hotel;
        this.guests = guests;
        this.reservations = reservations;
    }

    public String getCancellationNumber() {
        return cancellationNumber;
    }

    public void setCancellationNumber(String cancellationNumber) {
        this.cancellationNumber = cancellationNumber;
    }

    public Timestamp getCancellationDateTime() {
        return cancellationDateTime;
    }

    public void setCancellationDateTime(Timestamp cancellationDateTime) {
        this.cancellationDateTime = cancellationDateTime;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
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
}
