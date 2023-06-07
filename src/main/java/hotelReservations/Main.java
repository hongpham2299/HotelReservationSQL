package hotelReservations;

import hotelReservations.daos.GuestsDAO;
import hotelReservations.daos.ReservationsDAO;
import hotelReservations.models.BookingReservationMethods;
import hotelReservations.models.Guests;
import hotelReservations.models.Reservations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Logger logger = LogManager.getLogger(Main.class.getName());

        GuestsDAO guestsDAO = new GuestsDAO();

        Guests guestsByID = guestsDAO.getByID(2);
        logger.info(guestsByID);

        Guests guestDelete = guestsDAO.getByID(16);
        guestsDAO.delete(guestDelete);

        Guests guests = new Guests("Young", "Kim", "ykim@gmail.com", "(813) 258-0158");
        guestsDAO.create(guests);

        Guests updateGuestInfo = guestsDAO.getByID(7);
        updateGuestInfo.setFirstName("Alice");
        updateGuestInfo.setLastName("Jeane");
        updateGuestInfo.setEmail("Alice@gmail.com");
        updateGuestInfo.setPhone("(850) 456-2316");
        guestsDAO.update(updateGuestInfo);

        List<Guests> allGuests = guestsDAO.getAll();
        logger.info(allGuests);

        ReservationsDAO reservationsDAO = new ReservationsDAO();

        Reservations reservation = new Reservations();
        BookingReservationMethods bookingMethods = new BookingReservationMethods();
        bookingMethods.setBookingMethodID(100);
        reservation.setBookingReservationMethods(bookingMethods);

        reservation.setConfirmationNumber("TMWS-4678450");
        reservation.setArrivalDate(Date.valueOf("2023-10-12"));
        reservation.setDepartureDate(Date.valueOf("2023-10-15"));
        reservation.setGuestID(1);
        reservation.setRoomTypeID("SK");
        reservation.setHotelID("TMWS");
        reservationsDAO.create(reservation);



    }
}

