package hotelReservations.daos;

import hotelReservations.connection.DatabaseConnection;
import hotelReservations.models.Reservations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationsDAO implements GenericInterfaceDAO<Reservations> {

    private static final String GET_RES_BY_ID_SQL_QUERY = "SELECT * FROM reservations " +
            "WHERE Guest_ID = ?";
    private static final String GET_ALL_RES_SQL_QUERY = "SELECT * FROM reservations";
    private static final String CREATE_RES_SQL_QUERY = "INSERT INTO reservations VALUES (?, ?, ?, ?, ?, ?, ?) ";
    private static final String UPDATE_RES_SQL_QUERY = "UPDATE reservations SET Arrival_Date = ?, Departure_Date = ?," +
            "Room_Type_ID = ? WHERE Confirmation_Number=?";
    private static final String DELETE_RES_SQL_QUERY = "DELETE FROM reservations WHERE Confirmation_Number = ?";

    Logger logger = LogManager.getLogger(ReservationsDAO.class.getName());

    @Override
    public Reservations getByID(int id) {

        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(GET_RES_BY_ID_SQL_QUERY))
        {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){

                Reservations reservations = new Reservations();

                reservations.setConfirmationNumber(resultSet.getString("Confirmation_Number"));
                reservations.setArrivalDate(resultSet.getDate("Arrival_Date"));
                reservations.setDepartureDate(resultSet.getDate("Departure_Date"));
                reservations.setGuestID(resultSet.getInt("Guest_ID"));
                reservations.setRoomTypeID(resultSet.getString("Room_Type_ID"));
                reservations.setHotelID(resultSet.getString("Hotel_ID"));

                return reservations;
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Reservations> getAll() {
        List<Reservations> reservationsList = new ArrayList<>();

        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(GET_ALL_RES_SQL_QUERY))
        {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Reservations reservations = new Reservations();

                reservations.setConfirmationNumber(resultSet.getString("Confirmation_Number"));
                reservations.setArrivalDate(resultSet.getDate("Arrival_Date"));
                reservations.setDepartureDate(resultSet.getDate("Departure_Date"));
                reservations.setGuestID(resultSet.getInt("Guest_ID"));
                reservations.setRoomTypeID(resultSet.getString("Room_Type_ID"));
                reservations.setHotelID(resultSet.getString("Hotel_ID"));

                reservationsList.add(reservations);
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            logger.error(e.getMessage());
        }
        return reservationsList;
    }

    @Override
    public void create(Reservations reservations) {
        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(CREATE_RES_SQL_QUERY))
        {
            statement.setString(1, reservations.getConfirmationNumber());
            statement.setDate(2, reservations.getArrivalDate());
            statement.setDate(3, reservations.getDepartureDate());
            statement.setInt(4, reservations.getGuestID());
            statement.setString(5, reservations.getRoomTypeID());
            statement.setString(6, reservations.getHotelID());
            statement.setInt(7, reservations.getBookingReservationMethods().getBookingMethodID());

            int rowInserted = statement.executeUpdate();
            if(rowInserted > 0){
                logger.info("Confirmation#: " + reservations.getConfirmationNumber() + " -> Created successfully!");
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            logger.error(e.getMessage());
        }

    }

    @Override
    public void update(Reservations reservations) {
        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(UPDATE_RES_SQL_QUERY)) {
            statement.setDate(1, reservations.getArrivalDate());
            statement.setDate(2, reservations.getDepartureDate());
            statement.setString(3, reservations.getRoomTypeID());
            statement.setString(4, reservations.getConfirmationNumber());

            int rowUpdated = statement.executeUpdate();
            if(rowUpdated > 0) {
                logger.info("Confirmation#: " + reservations.getConfirmationNumber() + "-> Updated successfully!");
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            logger.error(e.getMessage());
        }

    }

    @Override
    public void delete(Reservations reservations) {
        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(DELETE_RES_SQL_QUERY)) {
            statement.setString(1, reservations.getConfirmationNumber());

            int rowDeleted = statement.executeUpdate();
            if(rowDeleted > 0){
                logger.info("Res Confirmation Number: " + reservations.getConfirmationNumber() + " -> delete successfully");
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            logger.error(e.getMessage());
        }

    }
}

