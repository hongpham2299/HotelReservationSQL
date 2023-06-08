package hotelReservations.daos;

import hotelReservations.connection.DatabaseConnection;
import hotelReservations.models.BookingReservationMethods;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingReservationMethodsDAO implements GenericInterfaceDAO<BookingReservationMethods> {

    private static final String GET_BY_ID_SQL_QUERY = "SELECT Booking_Method_Channel FROM bookingReservationMethods " +
                                                        "WHERE Booking_Method_ID=?";
    private static final String GET_ALL_SQL_QUERY = "SELECT * FROM bookingReservationMethods";
    private static final String CREATE_SQL_QUERY = "INSERT INTO bookingReservationMethods (Booking_Method_Channel) VALUES (?)";
    private static final String UPDATE_SQL_QUERY = "UPDATE bookingReservationMethods SET Booking_Method_Channel = ? " +
                                                        "WHERE Booking_Method_ID=?";
    private static final String DELETE_SQL_QUERY = "DELETE FROM bookingReservationMethods WHERE Booking_Method_ID = ?";

    private static BookingReservationMethodsDAO instance;

    Logger logger = LogManager.getLogger(BookingReservationMethodsDAO.class.getName());

    @Override
    public void getByID(BookingReservationMethods bookingReservationMethods) {
        try(PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(GET_BY_ID_SQL_QUERY)) {
            preparedStatement.setInt(1, bookingReservationMethods.getBookingMethodID());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String bookingChannel = resultSet.getString("Booking_Method_Channel");

                System.out.println("Booking Method ID: " + bookingReservationMethods.getBookingMethodID()
                                    + ", Booking Channel: " + bookingChannel);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void getAll() {
        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(GET_ALL_SQL_QUERY)){
           ResultSet resultSet = preparedStatement.executeQuery();

           while (resultSet.next()){
               int bookingMethodID = resultSet.getInt("Booking_Method_ID");
               String bookingChannel = resultSet.getString("Booking_Method_Channel");

               System.out.println("Booking Method ID: " + bookingMethodID + "\nBooking Channel: " + bookingChannel
                                   + "\n--------");
           }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(BookingReservationMethods bookingReservationMethods) {

        try (PreparedStatement prepareStatement = DatabaseConnection.getConnection().prepareStatement(CREATE_SQL_QUERY))
        {
            prepareStatement.setString(1, bookingReservationMethods.getBookingMethodChannel());

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
    public void update(BookingReservationMethods bookingReservationMethods) {

        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(UPDATE_SQL_QUERY)) {
            preparedStatement.setString(1, bookingReservationMethods.getBookingMethodChannel());
            preparedStatement.setInt(2, bookingReservationMethods.getBookingMethodID());

            int rowUpdated = preparedStatement.executeUpdate();
            if(rowUpdated > 0) {
                logger.info("Updated successfully!");
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            logger.error(e.getMessage());
        }
    }

    @Override
    public void delete(BookingReservationMethods bookingReservationMethods) {
        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(DELETE_SQL_QUERY)) {
            preparedStatement.setInt(1, bookingReservationMethods.getBookingMethodID());

            int rowDeleted = preparedStatement.executeUpdate();
            if(rowDeleted > 0){
                logger.info("Delete Successfully!");
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            logger.error(e.getMessage());
        }
    }

    public static BookingReservationMethodsDAO getInstance(){
        if(instance == null){
            instance = new BookingReservationMethodsDAO();
        }
        return instance;
    }
}
