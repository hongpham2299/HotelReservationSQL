package hotelReservations.daos;

import hotelReservations.connection.DatabaseConnection;
import hotelReservations.models.CancelledReservations;
import hotelReservations.models.Guests;
import hotelReservations.models.Hotel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CancelledReservationsDAO implements GenericInterfaceDAO<CancelledReservations> {

    private static final String GET_BY_ID_SQL_QUERY = "SELECT Confirmation_Number, Cancellation_Date, Guest_ID " +
            "FROM cancelledReservations WHERE Cancelation_Number=?";
    private static final String GET_ALL_SQL_QUERY = "SELECT * FROM cancelledReservations";
    private static final String CREATE_SQL_QUERY = "INSERT INTO cancelledReservations VALUES (?, ?, ?, ?, ?) ";
    private static final String UPDATE_SQL_QUERY = "UPDATE cancelledReservations SET Confirmation_Number = ?" +
                                                "Cancellation_Date = ?, Guest_ID = ? WHERE Cancelation_Number=?";
    private static final String DELETE_SQL_QUERY = "DELETE FROM cancelledReservations WHERE Cancelation_Number = ?";
    private static CancelledReservationsDAO instance;
    Logger logger = LogManager.getLogger(CancelledReservationsDAO.class.getName());

    @Override
    public void getByID(CancelledReservations cancelledReservations) {
        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(GET_BY_ID_SQL_QUERY)){
            preparedStatement.setString(1, cancelledReservations.getCancellationNumber());
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                String confNumber = resultSet.getString("Confirmation_Number");
                Timestamp cancelledDateTime = resultSet.getTimestamp("Cancellation_Date");
                int guestID = resultSet.getInt("Guest_ID");

                logger.info("Cancellation Number: " + cancelledReservations.getCancellationNumber()
                            + "\nConfirmation Number: " + confNumber
                            + "\nCancelled Date Time: " + cancelledDateTime
                            + "\nGuest ID: " + guestID);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void getAll() {
        try ( PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(GET_ALL_SQL_QUERY)){
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String cancellationNumber = resultSet.getString("Cancelation_Number");
                String confNumber = resultSet.getString("Confirmation_Number");
                Timestamp cancelledDateTime = resultSet.getTimestamp("Cancellation_Date");
                int guestID = resultSet.getInt("Guest_ID");
                String hotelID = resultSet.getString("Hotel_ID");

                System.out.println("Cancellation Number: " + cancellationNumber + "\nConfirmation Number: " + confNumber
                                    + "\nCancellation Date Time: " + cancelledDateTime
                                    + "\nGuest ID: " + guestID
                                    + "\nHotel ID: " + hotelID + "\n-------");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(CancelledReservations cancelledReservations) {

        try (PreparedStatement prepareStatement = DatabaseConnection.getConnection().prepareStatement(CREATE_SQL_QUERY))
        {
            prepareStatement.setString(1, cancelledReservations.getCancellationNumber());
            prepareStatement.setString(2, cancelledReservations.getReservations().getConfirmationNumber());
            prepareStatement.setTimestamp(3, cancelledReservations.getCancellationDateTime());
            prepareStatement.setInt(4, cancelledReservations.getGuests().getGuestID());
            prepareStatement.setString(5, cancelledReservations.getHotel().getHotelID());

            int rowInserted = prepareStatement.executeUpdate();
            if(rowInserted > 0){
                logger.info("Created successfully!");
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            logger.error(e.getMessage());
        }
    }

    @Override
    public void update(CancelledReservations cancelledReservations) {
        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(UPDATE_SQL_QUERY)) {
            preparedStatement.setString(1, cancelledReservations.getReservations().getConfirmationNumber());
            preparedStatement.setTimestamp(2, cancelledReservations.getCancellationDateTime());
            preparedStatement.setInt(3, cancelledReservations.getGuests().getGuestID());
            preparedStatement.setString(4, cancelledReservations.getCancellationNumber());

            int rowUpdated = preparedStatement.executeUpdate();
            if(rowUpdated > 0) {
                logger.info("Updated successfully!");
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            logger.info(e.getMessage());
        }
    }

    @Override
    public void delete(CancelledReservations cancelledReservations) {
        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(DELETE_SQL_QUERY)) {
            preparedStatement.setString(1, cancelledReservations.getCancellationNumber());

            int rowDeleted = preparedStatement.executeUpdate();
            if(rowDeleted > 0){
                logger.info("Delete successfully!");
            }
        } catch (SQLException e) {
            // throw new RuntimeException(e);
            logger.info(e.getMessage());
        }

    }

    public static CancelledReservationsDAO getInstance(){
        if(instance == null){
            instance = new CancelledReservationsDAO();
        }
        return instance;
    }
}
