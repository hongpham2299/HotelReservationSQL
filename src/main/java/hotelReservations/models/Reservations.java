package hotelReservations.models;

import java.sql.Date;

public class Reservations {

    private String confirmationNumber;
    private Date arrivalDate;
    private Date departureDate;
    private RoomType roomType;
    private Hotel hotel;
    private Guests guests;
    private BookingReservationMethods bookingReservationMethods;

    public Reservations(){}

    public Reservations(String confirmationNumber, Date arrivalDate, Date departureDate, RoomType roomType,
                        Hotel hotel, Guests guests, BookingReservationMethods bookingReservationMethods) {
        this.confirmationNumber = confirmationNumber;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.roomType = roomType;
        this.hotel = hotel;
        this.guests = guests;
        this.bookingReservationMethods = bookingReservationMethods;
    }

    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    public void setConfirmationNumber(String confirmationNumber) {
        this.confirmationNumber = confirmationNumber;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Guests getGuests() {
        return guests;
    }

    public void setGuests(Guests guests) {
        this.guests = guests;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public BookingReservationMethods getBookingReservationMethods() {
        return bookingReservationMethods;
    }

    public void setBookingReservationMethods(BookingReservationMethods bookingReservationMethods) {
        this.bookingReservationMethods = bookingReservationMethods;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public String toString() {
        return "Reservations{" +
                "confirmationNumber='" + confirmationNumber + '\'' +
                ", arrivalDate=" + arrivalDate +
                ", departureDate=" + departureDate +
                ", bookingReservationMethods=" + bookingReservationMethods +
                '}';
    }
}
