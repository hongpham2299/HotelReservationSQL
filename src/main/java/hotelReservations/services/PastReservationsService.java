package hotelReservations.services;

import hotelReservations.daos.PastReservationsDAO;
import hotelReservations.models.PastReservations;

public class PastReservationsService {

    private PastReservationsDAO pastReservationsDAO;

    public void getAll(){
        pastReservationsDAO = PastReservationsDAO.getInstance();
        pastReservationsDAO.getAll();
    }

    public void getByID(PastReservations pastReservations){
        pastReservationsDAO = PastReservationsDAO.getInstance();
        pastReservationsDAO.getByID(pastReservations);
    }

    public void create(PastReservations pastReservations){
        pastReservationsDAO = PastReservationsDAO.getInstance();
        pastReservationsDAO.create(pastReservations);
    }

    public void update(PastReservations pastReservations){
        pastReservationsDAO = PastReservationsDAO.getInstance();
        pastReservationsDAO.update(pastReservations);
    }
}
