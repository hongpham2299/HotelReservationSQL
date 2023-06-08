package hotelReservations.services;

import hotelReservations.daos.CheckInTransactionsDAO;
import hotelReservations.models.CheckInTransactions;

public class CheckInTransService {

    private CheckInTransactionsDAO checkInTransactionsDAO;

    public void getAll(){
        checkInTransactionsDAO = CheckInTransactionsDAO.getInstance();
        checkInTransactionsDAO.getAll();
    }

    public void getByID(CheckInTransactions trans){
        checkInTransactionsDAO = CheckInTransactionsDAO.getInstance();
        checkInTransactionsDAO.getByID(trans);
    }

    public void create(CheckInTransactions trans){
        checkInTransactionsDAO = CheckInTransactionsDAO.getInstance();
        checkInTransactionsDAO.create(trans);
    }

    public void update(CheckInTransactions trans){
        checkInTransactionsDAO = CheckInTransactionsDAO.getInstance();
        checkInTransactionsDAO.update(trans);
    }
}
