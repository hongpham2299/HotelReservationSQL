package hotelReservations;

import hotelReservations.daos.GuestsDAO;
import hotelReservations.models.Guests;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        Logger logger = LogManager.getLogger(Main.class.getName());

        GuestsDAO guestsDAO = new GuestsDAO();

        Guests guestsByID = guestsDAO.getByID(2);
        logger.info(guestsByID);

        Guests guestDelete = guestsDAO.getByID(14);
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



    }
}

