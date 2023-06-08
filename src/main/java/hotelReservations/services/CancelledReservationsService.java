package hotelReservations.services;

import hotelReservations.daos.CancelledReservationsDAO;
import hotelReservations.models.CancelledReservations;

public class CancelledReservationsService {

    private CancelledReservationsDAO cancelledReservationsDAO;

    public void getAll(){
        cancelledReservationsDAO = CancelledReservationsDAO.getInstance();
        cancelledReservationsDAO.getAll();
    }

    public void getByID(CancelledReservations cancelledReservations){
        cancelledReservationsDAO = CancelledReservationsDAO.getInstance();
        cancelledReservationsDAO.getByID(cancelledReservations);
    }

    public void create(CancelledReservations cancelledReservations){
        cancelledReservationsDAO = CancelledReservationsDAO.getInstance();
        cancelledReservationsDAO.create(cancelledReservations);
    }

    public void update(CancelledReservations cancelledReservations){
        cancelledReservationsDAO = CancelledReservationsDAO.getInstance();
        cancelledReservationsDAO.update(cancelledReservations);
    }
}
