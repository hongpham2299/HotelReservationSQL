package hotelReservations.daos;

import hotelReservations.connection.DatabaseConnection;
import hotelReservations.models.CheckOutTransactions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class CheckOutTransactionsDAO implements GenericInterfaceDAO<CheckOutTransactions>{

    private static final String GET_BY_ID_SQL_QUERY = "SELECT Confirmation_Number, FrontDeskAgent_ID " +
            "FROM checkOutTransactions WHERE CheckOut_Trans_ID = ?";
    private static final String GET_ALL_SQL_QUERY = "SELECT * FROM checkOutTransactions";
    private static final String CREATE_SQL_QUERY = "INSERT INTO (CheckOut_DateTime, Confirmation_Number, Payment_ID, Account_ID, FrontDeskAgent_ID) " +
            "checkOutTransactions VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_SQL_QUERY = "UPDATE checkOutTransactions SET CheckOut_DateTime = ?, Confirmation_Number = ?," +
            "Payment_ID = ?, Account_ID = ?, FrontDeskAgent_ID = ? WHERE CheckOut_Trans_ID=?";
    private static final String DELETE_SQL_QUERY = "DELETE FROM checkOutTransactions CheckOut_Trans_ID= ?";
    private static CheckOutTransactionsDAO instance;

    @Override
    public void getByID(CheckOutTransactions checkOutTransactions) {
        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(GET_BY_ID_SQL_QUERY)){

            preparedStatement.setInt(1, checkOutTransactions.getCheckOutTransID());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String confNumber = resultSet.getString(3);
                int frontDeskAgentID = resultSet.getInt(6);

                System.out.println("CheckOut Trans ID: " + checkOutTransactions.getCheckOutTransID()
                        + "-> Conf Number: " + confNumber + ", Front Desk Agent ID: " + frontDeskAgentID);
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
                int checkOutTransID = resultSet.getInt(1);
                Timestamp checkOutDateTime = resultSet.getTimestamp(2);
                String confNumb = resultSet.getString(3);
                int paymentID = resultSet.getInt(4);
                String accountID = resultSet.getString(5);
                int frontDeskAgentID = resultSet.getInt(6);

                System.out.println("CheckIn Trans ID: " + checkOutTransID + "\nCheckIn Date Time: $" + checkOutDateTime
                        + "\nConf Number: " + confNumb
                        + "\nPayment ID: " + paymentID
                        + "\nPayment ID: " + accountID
                        + "\nFront Desk Agent ID: " + frontDeskAgentID
                        + "\n------");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(CheckOutTransactions checkOutTransactions) {
        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(CREATE_SQL_QUERY)){

            preparedStatement.setTimestamp(1, checkOutTransactions.getCheckOutDateTime());
            preparedStatement.setString(2, checkOutTransactions.getReservations().getConfirmationNumber());
            preparedStatement.setInt(3, checkOutTransactions.getPaymentDetails().getPaymentID());
            preparedStatement.setString(4, checkOutTransactions.getBillings().getAccountID());
            preparedStatement.setInt(5, checkOutTransactions.getFrontDeskAgents().getFrontDeskAgentID());

            int rowInserted = preparedStatement.executeUpdate();
            if(rowInserted > 0) {
                System.out.println("Created successfully!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(CheckOutTransactions checkOutTransactions) {
        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(UPDATE_SQL_QUERY)){

            preparedStatement.setTimestamp(1, checkOutTransactions.getCheckOutDateTime());
            preparedStatement.setString(2, checkOutTransactions.getReservations().getConfirmationNumber());
            preparedStatement.setInt(3, checkOutTransactions.getPaymentDetails().getPaymentID());
            preparedStatement.setString(4, checkOutTransactions.getBillings().getAccountID());
            preparedStatement.setInt(5, checkOutTransactions.getFrontDeskAgents().getFrontDeskAgentID());
            preparedStatement.setInt(6, checkOutTransactions.getCheckOutTransID());

            int rowInserted = preparedStatement.executeUpdate();
            if(rowInserted > 0) {
                System.out.println("Updated successfully!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(CheckOutTransactions checkOutTransactions) {
        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(DELETE_SQL_QUERY)){

            preparedStatement.setInt(1, checkOutTransactions.getCheckOutTransID());

            int rowDeleted = preparedStatement.executeUpdate();
            if(rowDeleted > 0){
                System.out.println("Delete Successfully!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static CheckOutTransactionsDAO getInstance(){
        if(instance == null){
            instance = new CheckOutTransactionsDAO();
        }
        return instance;
    }
}
