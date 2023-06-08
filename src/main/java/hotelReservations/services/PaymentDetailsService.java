package hotelReservations.services;

import hotelReservations.daos.PaymentDetailsDAO;
import hotelReservations.models.PaymentDetails;

public class PaymentDetailsService {

    private PaymentDetailsDAO paymentDetailsDAO;

    public void getAll(){
        paymentDetailsDAO = PaymentDetailsDAO.getInstance();
        paymentDetailsDAO.getAll();
    }

    public void getByID(PaymentDetails paymentDetails){
        paymentDetailsDAO = PaymentDetailsDAO.getInstance();
        paymentDetailsDAO.getByID(paymentDetails);
    }

    public void create(PaymentDetails paymentDetails){
        paymentDetailsDAO = PaymentDetailsDAO.getInstance();
        paymentDetailsDAO.create(paymentDetails);
    }

    public void update(PaymentDetails paymentDetails){
        paymentDetailsDAO = PaymentDetailsDAO.getInstance();
        paymentDetailsDAO.update(paymentDetails);
    }
}
