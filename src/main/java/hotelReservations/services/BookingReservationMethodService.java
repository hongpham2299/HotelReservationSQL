package hotelReservations.services;

import hotelReservations.daos.BookingReservationMethodsDAO;
import hotelReservations.models.BookingReservationMethods;

public class BookingReservationMethodService {

    private BookingReservationMethodsDAO bookingReservationMethodsDAO;

    public void getAll(){
        bookingReservationMethodsDAO = BookingReservationMethodsDAO.getInstance();
        bookingReservationMethodsDAO.getAll();
    }

    public void getByID(BookingReservationMethods method){
        bookingReservationMethodsDAO = BookingReservationMethodsDAO.getInstance();
        bookingReservationMethodsDAO.getByID(method);
    }

    public void create(BookingReservationMethods method){
        bookingReservationMethodsDAO = BookingReservationMethodsDAO.getInstance();
        bookingReservationMethodsDAO.create(method);
    }

    public void update(BookingReservationMethods method){
        bookingReservationMethodsDAO = BookingReservationMethodsDAO.getInstance();
        bookingReservationMethodsDAO.update(method);
    }
}
