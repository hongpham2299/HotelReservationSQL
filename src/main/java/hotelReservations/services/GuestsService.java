package hotelReservations.services;

import hotelReservations.daos.GuestsDAO;
import hotelReservations.models.Guests;

import java.util.List;

public class GuestsService {

    private GuestsDAO guestsDAO;

    public List<Guests> getAll(){
        guestsDAO = GuestsDAO.getInstance();
        return guestsDAO.getAll();
    }

    public Guests getByID(int guestID){
        guestsDAO = GuestsDAO.getInstance();
        return guestsDAO.getByID(guestID);
    }

    public void create(Guests guest){
        guestsDAO = GuestsDAO.getInstance();
        guestsDAO.create(guest);
    }

    public void update(Guests guest){
        guestsDAO = GuestsDAO.getInstance();
        guestsDAO.update(guest);
    }
}
