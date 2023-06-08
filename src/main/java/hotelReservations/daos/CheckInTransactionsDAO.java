package hotelReservations.daos;

import hotelReservations.connection.DatabaseConnection;
import hotelReservations.models.CheckInTransactions;

import java.math.BigDecimal;
import java.sql.*;

public class CheckInTransactionsDAO implements GenericInterfaceDAO<CheckInTransactions>{

    private static final String GET_BY_ID_SQL_QUERY = "SELECT Confirmation_Number, FrontDeskAgent_ID " +
            "FROM checkInTransactions WHERE CheckIn_Trans_ID = ?";
    private static final String GET_ALL_SQL_QUERY = "SELECT * FROM checkInTransactions";
    private static final String CREATE_SQL_QUERY = "INSERT INTO (CheckIn_DateTime, Confirmation_Number, Payment_ID, FrontDeskAgent_ID) " +
            "checkInTransactions VALUES (?, ?, ?, ?)";
    private static final String UPDATE_SQL_QUERY = "UPDATE checkInTransactions SET CheckIn_DateTime = ?, Confirmation_Number = ?," +
            "Payment_ID = ?, FrontDeskAgent_ID = ? WHERE CheckIn_Trans_ID=?";
    private static final String DELETE_SQL_QUERY = "DELETE FROM checkInTransactions CheckIn_Trans_ID= ?";
    private static CheckInTransactionsDAO instance;


    @Override
    public void getByID(CheckInTransactions checkInTransactions) {
        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(GET_BY_ID_SQL_QUERY)){

            preparedStatement.setInt(1, checkInTransactions.getCheckInTransID());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String confNumber = resultSet.getString(3);
                int frontDeskAgentID = resultSet.getInt(5);

                System.out.println("CheckIn Trans ID: " + checkInTransactions.getCheckInTransID()
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
                int checkInTransID = resultSet.getInt(1);
                Timestamp checkInDateTime = resultSet.getTimestamp(2);
                String confNumb = resultSet.getString(3);
                int paymentID = resultSet.getInt(4);
                int frontDeskAgentID = resultSet.getInt(5);

                System.out.println("CheckIn Trans ID: " + checkInTransID + "\nCheckIn Date Time: $" + checkInDateTime
                        + "\nConf Number: " + confNumb
                        + "\nPayment ID: " + paymentID
                        + "\nFront Desk Agent ID: " + frontDeskAgentID
                        + "\n------");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void create(CheckInTransactions checkInTransactions) {
        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(CREATE_SQL_QUERY)){

            preparedStatement.setTimestamp(1, checkInTransactions.getCheckInDateTime());
            preparedStatement.setString(2, checkInTransactions.getReservations().getConfirmationNumber());
            preparedStatement.setInt(3, checkInTransactions.getPaymentDetails().getPaymentID());
            preparedStatement.setInt(4, checkInTransactions.getFrontDeskAgents().getFrontDeskAgentID());

            int rowInserted = preparedStatement.executeUpdate();
            if(rowInserted > 0) {
                System.out.println("Created successfully!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(CheckInTransactions checkInTransactions) {
        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(UPDATE_SQL_QUERY)){

            preparedStatement.setTimestamp(1, checkInTransactions.getCheckInDateTime());
            preparedStatement.setString(2, checkInTransactions.getReservations().getConfirmationNumber());
            preparedStatement.setInt(3, checkInTransactions.getPaymentDetails().getPaymentID());
            preparedStatement.setInt(4, checkInTransactions.getFrontDeskAgents().getFrontDeskAgentID());
            preparedStatement.setInt(5, checkInTransactions.getCheckInTransID());

            int rowInserted = preparedStatement.executeUpdate();
            if(rowInserted > 0) {
                System.out.println("Updated successfully!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(CheckInTransactions checkInTransactions) {
        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(DELETE_SQL_QUERY)){

            preparedStatement.setInt(1, checkInTransactions.getCheckInTransID());

            int rowDeleted = preparedStatement.executeUpdate();
            if(rowDeleted > 0){
                System.out.println("Delete Successfully!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static CheckInTransactionsDAO getInstance(){
        if(instance == null){
            instance = new CheckInTransactionsDAO();
        }
        return instance;
    }

}
