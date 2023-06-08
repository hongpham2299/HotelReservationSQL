package hotelReservations.services;

import hotelReservations.daos.HotelDAO;
import hotelReservations.models.Hotel;

public class HotelService {

    private HotelDAO hotelDAO;

    public void getAll(){
        hotelDAO = HotelDAO.getInstance();
        hotelDAO.getAll();
    }

    public void getByID(Hotel hotel){
        hotelDAO = HotelDAO.getInstance();
        hotelDAO.getByID(hotel);
    }

    public void create(Hotel hotel){
        hotelDAO = HotelDAO.getInstance();
        hotelDAO.create(hotel);
    }

    public void update(Hotel hotel){
        hotelDAO = HotelDAO.getInstance();
        hotelDAO.update(hotel);
    }
}
