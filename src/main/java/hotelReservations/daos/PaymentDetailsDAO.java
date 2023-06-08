package hotelReservations.daos;

import hotelReservations.connection.DatabaseConnection;
import hotelReservations.models.PaymentDetails;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentDetailsDAO implements GenericInterfaceDAO<PaymentDetails>{

    private static final String GET_BY_ID_SQL_QUERY = "SELECT Card_Holder_Name, Guest_ID FROM paymentDetails WHERE Payment_ID = ?";
    private static final String GET_ALL_SQL_QUERY = "SELECT * FROM paymentDetails";
    private static final String CREATE_SQL_QUERY = "INSERT INTO (Card_Holder_Name, CC_Numbers, Expiration_Date, CVV, " +
            "Guest_ID, Confirmation_Number, Past_Confirmation_Number ) paymentDetails VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_SQL_QUERY = "UPDATE paymentDetails SET Card_Holder_Name = ?, CC_Numbers = ?," +
            "Expiration_Date = ?, CVV = ?, Guest_ID = ?, Confirmation_Number =?, Past_Confirmation_Number=?  WHERE Payment_ID=?";
    private static final String DELETE_SQL_QUERY = "DELETE FROM paymentDetails WHERE Payment_ID= ?";

    private static PaymentDetailsDAO instance;


    @Override
    public void getByID(PaymentDetails paymentDetails) {
        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(GET_BY_ID_SQL_QUERY)){

            preparedStatement.setInt(1, paymentDetails.getPaymentID());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String cardHolderName = resultSet.getString("Card_Holder_Name");
                int guestID = resultSet.getInt("Guest_ID");

                System.out.println("Payment ID: " + paymentDetails.getPaymentID()
                        + " -> Card Holder Name: " + cardHolderName + ", Guest ID: " + guestID);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void getAll() {
        try(PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(GET_ALL_SQL_QUERY)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int paymentID = resultSet.getInt(1);
                String cardHolderName = resultSet.getString(2);
                String ccNumber = resultSet.getString(3);
                String expiration = resultSet.getString(4);
                int cvv = resultSet.getInt(5);
                int guestID = resultSet.getInt(6);
                String confNum = resultSet.getString(7);
                String pastConfNum = resultSet.getString(8);

                System.out.println("Payment ID: " + paymentID + "\nCard Holder Name: " + cardHolderName
                        + "\nCC Number: " + ccNumber
                        + "\nExpiration: " + expiration
                        + "\nCVV: " + cvv
                        + "\nGuest ID: " + guestID
                        + "\nConf Number: " + confNum
                        + "\nPast Conf Number: " + pastConfNum + "\n------");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(PaymentDetails paymentDetails) {
        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(CREATE_SQL_QUERY)){

            preparedStatement.setString(1, paymentDetails.getCardHolderName());
            preparedStatement.setString(2, paymentDetails.getCcNumbers());
            preparedStatement.setString(3, paymentDetails.getExpiration());
            preparedStatement.setInt(4, paymentDetails.getCvvNum());
            preparedStatement.setInt(5, paymentDetails.getGuests().getGuestID());
            preparedStatement.setString(6, paymentDetails.getReservations().getConfirmationNumber());
            preparedStatement.setString(7, paymentDetails.getPastReservations().getPastConfNumber());

            int rowInserted = preparedStatement.executeUpdate();
            if(rowInserted > 0) {
                System.out.println("Created successfully!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(PaymentDetails paymentDetails) {
        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(UPDATE_SQL_QUERY)){

            preparedStatement.setString(1, paymentDetails.getCardHolderName());
            preparedStatement.setString(2, paymentDetails.getCcNumbers());
            preparedStatement.setString(3, paymentDetails.getExpiration());
            preparedStatement.setInt(4, paymentDetails.getCvvNum());
            preparedStatement.setInt(5, paymentDetails.getGuests().getGuestID());
            preparedStatement.setString(6, paymentDetails.getReservations().getConfirmationNumber());
            preparedStatement.setString(7, paymentDetails.getPastReservations().getPastConfNumber());
            preparedStatement.setInt(8, paymentDetails.getPaymentID());

            int rowInserted = preparedStatement.executeUpdate();
            if(rowInserted > 0) {
                System.out.println("Updated successfully!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(PaymentDetails paymentDetails) {

        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(DELETE_SQL_QUERY)) {
            statement.setInt(1, paymentDetails.getPaymentID());

            int rowDeleted = statement.executeUpdate();
            if(rowDeleted > 0){
                System.out.println("delete successfully");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
            //logger.error(e.getMessage());
        }
    }

    public static PaymentDetailsDAO getInstance(){
        if(instance == null){
            instance = new PaymentDetailsDAO();
        }
        return instance;
    }
}
