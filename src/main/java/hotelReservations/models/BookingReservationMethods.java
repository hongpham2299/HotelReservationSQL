package hotelReservations.models;

import java.sql.Date;
import java.time.LocalDate;

public class BookingReservationMethods {

    private int bookingMethodID;//auto
    private String bookingMethodChannel;

    public BookingReservationMethods(){}

    public BookingReservationMethods(String bookingMethodChannel) {
        this.bookingMethodChannel = bookingMethodChannel;
    }

    public int getBookingMethodID() {
        return bookingMethodID;
    }

    public void setBookingMethodID(int bookingMethodID) {
        this.bookingMethodID = bookingMethodID;
    }

    public String getBookingMethodChannel() {
        return bookingMethodChannel;
    }

    public void setBookingMethodChannel(String bookingMethodChannel) {
        this.bookingMethodChannel = bookingMethodChannel;
    }

}
