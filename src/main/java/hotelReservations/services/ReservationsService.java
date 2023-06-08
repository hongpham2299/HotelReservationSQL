package hotelReservations.services;

import hotelReservations.daos.ReservationsDAO;
import hotelReservations.models.Reservations;

public class ReservationsService {

    private ReservationsDAO reservationsDAO;

    public void getAll(){
        reservationsDAO = ReservationsDAO.getInstance();
        reservationsDAO.getAll();
    }

    public void getByID(Reservations reservations){
        reservationsDAO = ReservationsDAO.getInstance();
        reservationsDAO.getByID(reservations);
    }

    public void create(Reservations reservations){
        reservationsDAO = ReservationsDAO.getInstance();
        reservationsDAO.create(reservations);
    }

    public void update(Reservations reservations){
        reservationsDAO = ReservationsDAO.getInstance();
        reservationsDAO.update(reservations);
    }
}
