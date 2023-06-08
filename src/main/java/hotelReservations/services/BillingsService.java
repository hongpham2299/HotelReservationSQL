package hotelReservations.services;

import hotelReservations.daos.BillingsDAO;
import hotelReservations.models.Billings;

public class BillingsService {

    private BillingsDAO billingsDAO;

    public void getAll(){
        billingsDAO = BillingsDAO.getInstance();
        billingsDAO.getAll();
    }

    public void getByID(Billings billings){
        billingsDAO = BillingsDAO.getInstance();
        billingsDAO.getByID(billings);
    }

    public void create(Billings billings){
        billingsDAO = BillingsDAO.getInstance();
        billingsDAO.create(billings);
    }

    public void update(Billings billings){
        billingsDAO = BillingsDAO.getInstance();
        billingsDAO.update(billings);
    }
}
