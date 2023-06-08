package hotelReservations.models;

import java.sql.Date;

public class PastReservations {

    private String pastConfNumber;
    private Date arrivalDate;
    private Date departureDate;
    private Guests guests;
    private RoomType roomType;
    private Hotel hotel;
    private BookingReservationMethods bookingMethod;

    public PastReservations(){}

    public PastReservations(String pastConfNumber, Date arrivalDate, Date departureDate, Guests guests,
                            RoomType roomType, Hotel hotel, BookingReservationMethods bookingMethod) {
        this.pastConfNumber = pastConfNumber;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.guests = guests;
        this.roomType = roomType;
        this.hotel = hotel;
        this.bookingMethod = bookingMethod;
    }

    public String getPastConfNumber() {
        return pastConfNumber;
    }

    public void setPastConfNumber(String pastConfNumber) {
        this.pastConfNumber = pastConfNumber;
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

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public BookingReservationMethods getBookingMethod() {
        return bookingMethod;
    }

    public void setBookingMethod(BookingReservationMethods bookingMethod) {
        this.bookingMethod = bookingMethod;
    }
}
