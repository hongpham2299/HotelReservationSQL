package hotelReservations.daos;

import hotelReservations.connection.DatabaseConnection;
import hotelReservations.models.Billings;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BillingsDAO implements GenericInterfaceDAO<Billings> {

    private static final String GET_BY_ID_SQL_QUERY = "SELECT Amount, Guest_ID FROM billings WHERE Account_ID = ?";
    private static final String GET_ALL_SQL_QUERY = "SELECT * FROM billings";
    private static final String CREATE_SQL_QUERY = "INSERT INTO billings VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_SQL_QUERY = "UPDATE billings SET Amount = ?, Transaction_Date = ?," +
            "Payment_ID = ?, Guest_ID = ?, Past_Confirmation_Number = ?, Hotel_ID =? WHERE Account_ID=?";
    private static final String DELETE_SQL_QUERY = "DELETE FROM billings WHERE Account_ID= ?";

    private static BillingsDAO instance;


    @Override
    public void getByID(Billings billings) {
        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(GET_BY_ID_SQL_QUERY)){

            preparedStatement.setString(1, billings.getAccountID());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                BigDecimal amount = resultSet.getBigDecimal("Amount");
                int guestID = resultSet.getInt("Guest_ID");

                System.out.println("Account ID: " + billings.getAccountID()
                                    + " -> Amount: $" + amount + ", Guest ID: " + guestID);
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
                String accountID = resultSet.getString(1);
                BigDecimal amount = resultSet.getBigDecimal(2);
                Date transDate = resultSet.getDate(3);
                int paymentID = resultSet.getInt(4);
                int guestID = resultSet.getInt(5);
                String pastConfNum = resultSet.getString(6);

                System.out.println("Account ID: " + accountID + "\nAmount: $" + amount
                                    + "\nTransaction Date: " + transDate
                                    + "\nPayment ID: " + paymentID
                                    + "\nGuest ID: " + guestID
                                    + "\nPast Conf Number: " + pastConfNum + "\n------");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(Billings billings) {
        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(CREATE_SQL_QUERY)){

            preparedStatement.setString(1, billings.getAccountID());
            preparedStatement.setBigDecimal(2, billings.getAmount());
            preparedStatement.setDate(3, billings.getTransactionDate());
            preparedStatement.setInt(4, billings.getPaymentDetails().getPaymentID());
            preparedStatement.setInt(5, billings.getGuests().getGuestID());
            preparedStatement.setString(6, billings.getPastReservations().getPastConfNumber());

            int rowInserted = preparedStatement.executeUpdate();
            if(rowInserted > 0) {
                System.out.println("Created successfully!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Billings billings) {
        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(UPDATE_SQL_QUERY)){

            preparedStatement.setBigDecimal(1, billings.getAmount());
            preparedStatement.setDate(2, billings.getTransactionDate());
            preparedStatement.setInt(3, billings.getPaymentDetails().getPaymentID());
            preparedStatement.setInt(4, billings.getGuests().getGuestID());
            preparedStatement.setString(5, billings.getPastReservations().getPastConfNumber());
            preparedStatement.setString(6, billings.getAccountID());

            int rowInserted = preparedStatement.executeUpdate();
            if(rowInserted > 0) {
                System.out.println("Updated successfully!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Billings billings) {
        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(DELETE_SQL_QUERY)){

            preparedStatement.setString(1, billings.getAccountID());

            int rowDeleted = preparedStatement.executeUpdate();
            if(rowDeleted > 0){
                System.out.println("Delete Successfully!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static BillingsDAO getInstance(){
        if(instance == null){
            instance = new BillingsDAO();
        }
        return instance;
    }
}
