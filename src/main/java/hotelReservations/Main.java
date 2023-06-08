package hotelReservations;

import hotelReservations.daos.*;
import hotelReservations.models.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        GuestsDAO guestsDAO = new GuestsDAO();

        Guests guest = new Guests();
        guest.setGuestID(5);
        guestsDAO.getByID(guest);

        Guests guests = new Guests("Mario", "Diaz", "mario156@gmail.com", "(813) 150-5118");
        guestsDAO.create(guests);

        Guests guestDelete = new Guests();
        guestDelete.setGuestID(20);
        guestsDAO.delete(guestDelete);

        Guests updateGuestInfo = new Guests();
        updateGuestInfo.setGuestID(15);
        updateGuestInfo.setFirstName("Dung");
        updateGuestInfo.setLastName("Phan");
        updateGuestInfo.setEmail("dunphan56@yahoo.com");
        updateGuestInfo.setPhone("(578) 246-8911");
        guestsDAO.update(updateGuestInfo);

        guestsDAO.getAll();

        ReservationsDAO reservationsDAO = new ReservationsDAO();

        Reservations reservation = new Reservations();
        reservation.setConfirmationNumber("TMWS-1587923");
        reservationsDAO.getByID(reservation);

        Guests guest1 = new Guests();
        guest1.setGuestID(2);

        RoomType roomType = new RoomType();
        roomType.setRoomTypeID("SQQ");

        BookingReservationMethods bookingMethod = new BookingReservationMethods();
        bookingMethod.setBookingMethodID(100);

        Reservations updateRes = new Reservations();
        updateRes.setConfirmationNumber("TMWS-7894561");
        updateRes.setArrivalDate(Date.valueOf("2023-09-11"));
        updateRes.setDepartureDate(Date.valueOf("2023-09-15"));
        updateRes.setGuests(guest1);
        updateRes.setRoomType(roomType);
        updateRes.setBookingReservationMethods(bookingMethod);
        reservationsDAO.update(updateRes);

        reservationsDAO.getAll();

    }
}

