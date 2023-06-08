package hotelReservations.daos;

import hotelReservations.connection.DatabaseConnection;
import hotelReservations.models.Reservations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationsDAO implements GenericInterfaceDAO<Reservations> {

    private static final String GET_BY_CONFNUMBER_SQL_QUERY = "SELECT * FROM reservations WHERE Confirmation_Number = ?";
    private static final String GET_ALL_SQL_QUERY = "SELECT * FROM reservations";
    private static final String CREATE_SQL_QUERY = "INSERT INTO reservations VALUES (?, ?, ?, ?, ?, ?, ?) ";
    private static final String UPDATE_SQL_QUERY = "UPDATE reservations SET Arrival_Date = ?, Departure_Date = ?," +
            "Guest_ID =?, Room_Type_ID = ?, Booking_Method_ID = ? WHERE Confirmation_Number=?";
    private static final String DELETE_RES_SQL_QUERY = "DELETE FROM reservations WHERE Confirmation_Number = ?";
    private static ReservationsDAO instance;
    Logger logger = LogManager.getLogger(ReservationsDAO.class.getName());


    public void getByID(Reservations reservations){
        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(GET_BY_CONFNUMBER_SQL_QUERY)){

            preparedStatement.setString(1, reservations.getConfirmationNumber());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Date arrivalDate = resultSet.getDate("Arrival_Date");
                Date departureDate = resultSet.getDate("Departure_Date");
                int guestID = resultSet.getInt("Guest_ID");
                String roomTypeID = resultSet.getString("Room_Type_ID");
                String hotelID = resultSet.getString("Hotel_ID");
                int bookingMethodID = resultSet.getInt("Booking_Method_ID");

                logger.info("Confirmation Number: " + reservations.getConfirmationNumber()
                                    + "\nArrival Date: " + arrivalDate
                                    + "\nDeparture Date: " + departureDate
                                    + "\nGuest ID: " + guestID
                                    + "\nRoom Type ID: " + roomTypeID
                                    + "\nHotel ID: " + hotelID + "\nBooking Method ID: "
                                    + bookingMethodID + "\n------");
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            logger.error(e.getMessage());
        }
    }

    @Override
    public void getAll() {

        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(GET_ALL_SQL_QUERY)){

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String confirmationNumber = resultSet.getString(1);
                Date arrivalDate = resultSet.getDate(2);
                Date departureDate = resultSet.getDate(3);
                int guestID = resultSet.getInt(4);
                String roomTypeID = resultSet.getString(5);
                String hotelID = resultSet.getString(6);
                int bookingMethodID = resultSet.getInt(7);

                logger.info("Confirmation Number: " + confirmationNumber
                        + "\nArrival Date: " + arrivalDate
                        + "\nDeparture Date: " + departureDate
                        + "\nGuest ID: " + guestID
                        + "\nRoom Type ID: " + roomTypeID
                        + "\nHotel ID: " + hotelID + "\nBooking Method ID: "
                        + bookingMethodID + "\n------");
            }
        } catch (SQLException e) {
           // throw new RuntimeException(e);
            logger.error(e.getMessage());
        }

    }

    @Override
    public void create(Reservations reservations) {
        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(CREATE_SQL_QUERY))
        {
            statement.setString(1, reservations.getConfirmationNumber());
            statement.setDate(2, reservations.getArrivalDate());
            statement.setDate(3, reservations.getDepartureDate());
            statement.setInt(4, reservations.getGuests().getGuestID());
            statement.setString(5, reservations.getRoomType().getRoomTypeID());
            statement.setString(6, reservations.getHotel().getHotelID());
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
        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(UPDATE_SQL_QUERY)) {
            statement.setDate(1, reservations.getArrivalDate());
            statement.setDate(2, reservations.getDepartureDate());
            statement.setInt(3, reservations.getGuests().getGuestID());
            statement.setString(4, reservations.getRoomType().getRoomTypeID());
            statement.setInt(5, reservations.getBookingReservationMethods().getBookingMethodID());
            statement.setString(6, reservations.getConfirmationNumber());

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

    public static ReservationsDAO getInstance(){
        if(instance == null){
            instance = new ReservationsDAO();
        }
        return instance;
    }

}

