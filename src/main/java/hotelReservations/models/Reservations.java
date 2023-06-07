package hotelReservations.models;

import java.sql.Date;

public class Reservations {

    private String confirmationNumber;
    private Date arrivalDate;
    private Date departureDate;
    private int guestID;
    private String roomTypeID;
    private String hotelID;
    private BookingReservationMethods bookingReservationMethods;


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

    public int getGuestID() {
        return guestID;
    }

    public void setGuestID(int guestID) {
        this.guestID = guestID;
    }

    public String getRoomTypeID() {
        return roomTypeID;
    }

    public void setRoomTypeID(String roomTypeID) {
        this.roomTypeID = roomTypeID;
    }

    public String getHotelID() {
        return hotelID;
    }

    public void setHotelID(String hotelID) {
        this.hotelID = hotelID;
    }

    public BookingReservationMethods getBookingReservationMethods() {
        return bookingReservationMethods;
    }

    public void setBookingReservationMethods(BookingReservationMethods bookingReservationMethods) {
        this.bookingReservationMethods = bookingReservationMethods;
    }

    @Override
    public String toString() {
        return "Reservations{" +
                "confirmationNumber='" + confirmationNumber + '\'' +
                ", arrivalDate=" + arrivalDate +
                ", departureDate=" + departureDate +
                ", guestID=" + guestID +
                ", roomTypeID='" + roomTypeID + '\'' +
                ", hotelID='" + hotelID + '\'' +
                ", bookingReservationMethods=" + bookingReservationMethods +
                '}';
    }
}
