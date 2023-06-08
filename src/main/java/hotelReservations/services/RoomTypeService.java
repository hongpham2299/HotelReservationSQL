package hotelReservations.services;

import hotelReservations.daos.RoomTypeDAO;
import hotelReservations.models.RoomType;

public class RoomTypeService {

    private RoomTypeDAO roomTypeDAO;

    public void getAll(){
        roomTypeDAO = RoomTypeDAO.getInstance();
        roomTypeDAO.getAll();
    }

    public void getByID(RoomType roomType){
        roomTypeDAO = RoomTypeDAO.getInstance();
        roomTypeDAO.getByID(roomType);
    }

    public void create(RoomType roomType){
        roomTypeDAO = RoomTypeDAO.getInstance();
        roomTypeDAO.create(roomType);
    }

    public void update(RoomType roomType){
        roomTypeDAO = RoomTypeDAO.getInstance();
        roomTypeDAO.update(roomType);
    }
}
