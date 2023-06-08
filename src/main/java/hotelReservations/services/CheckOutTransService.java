package hotelReservations.services;

import hotelReservations.daos.CheckOutTransactionsDAO;
import hotelReservations.models.CheckOutTransactions;


public class CheckOutTransService {

    private CheckOutTransactionsDAO checkOutTransactionsDAO;

    public void getAll(){
        checkOutTransactionsDAO = CheckOutTransactionsDAO.getInstance();
        checkOutTransactionsDAO.getAll();
    }

    public void getByID(CheckOutTransactions transactions){
        checkOutTransactionsDAO = CheckOutTransactionsDAO.getInstance();
        checkOutTransactionsDAO.getByID(transactions);
    }

    public void create(CheckOutTransactions transactions){
        checkOutTransactionsDAO = CheckOutTransactionsDAO.getInstance();
        checkOutTransactionsDAO.create(transactions);
    }

    public void update(CheckOutTransactions transactions){
        checkOutTransactionsDAO = CheckOutTransactionsDAO.getInstance();
        checkOutTransactionsDAO.update(transactions);
    }
}
