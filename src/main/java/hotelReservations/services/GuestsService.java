package hotelReservations.services;

import hotelReservations.daos.GuestsDAO;
import hotelReservations.models.Guests;

import java.util.List;

public class GuestsService {

    private GuestsDAO guestsDAO;

    public void getAll(){
        guestsDAO = GuestsDAO.getInstance();
        guestsDAO.getAll();
    }

    public void getByID(Guests guests){
        guestsDAO = GuestsDAO.getInstance();
        guestsDAO.getByID(guests);
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
