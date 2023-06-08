package hotelReservations.daos;

import hotelReservations.connection.DatabaseConnection;
import hotelReservations.models.PastReservations;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PastReservationsDAO implements GenericInterfaceDAO<PastReservations> {

    private static final String GET_BY_CONFNUMBER_SQL_QUERY = "SELECT Guest_ID FROM pastReservations WHERE Past_Confirmation_Number = ?";
    private static final String GET_ALL_SQL_QUERY = "SELECT * FROM pastReservations";
    private static final String CREATE_SQL_QUERY = "INSERT INTO pastReservations VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_SQL_QUERY = "UPDATE pastReservations SET Arrival_Date = ?, Departure_Date = ?," +
            "Guest_ID = ?, Room_Type_ID = ?, Booking_Method_ID = ?, Hotel_ID =? WHERE Past_Confirmation_Number=?";
    private static final String DELETE_RES_SQL_QUERY = "DELETE FROM pastReservations WHERE Past_Confirmation_Number= ?";

    private static PastReservationsDAO instance;

    @Override
    public void getByID(PastReservations pastReservations) {
        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(GET_BY_CONFNUMBER_SQL_QUERY)){

            preparedStatement.setString(1, pastReservations.getPastConfNumber());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int guestID = resultSet.getInt("Guest_ID");

                System.out.println("Past Res Number: " + pastReservations.getPastConfNumber() + ", Guest ID: " + guestID);
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
                String pastConfNumber = resultSet.getString(1);
                Date arrivalDate = resultSet.getDate(2);
                Date departureDate = resultSet.getDate(3);
                int guestID = resultSet.getInt(4);
                String roomTypeID = resultSet.getString(5);
                String hotelID = resultSet.getString(6);
                int bookingMethodID = resultSet.getInt(7);

                System.out.println("Past Confirmation Number: " + pastConfNumber
                                    + "\nArrival Date: " + arrivalDate
                                    + "\nDeparture Date: " + departureDate
                                    + "\nGuest ID: " + guestID
                                    + "\nRoom Type ID: " + roomTypeID
                                    + "\nHotel ID: " + hotelID
                                    + "\nBooking Method ID: " + bookingMethodID);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void create(PastReservations pastReservations) {
        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(CREATE_SQL_QUERY);){

           preparedStatement.setString(1, pastReservations.getPastConfNumber());
           preparedStatement.setDate(2, pastReservations.getArrivalDate());
           preparedStatement.setDate(3, pastReservations.getDepartureDate());
           preparedStatement.setInt(4, pastReservations.getGuests().getGuestID());
           preparedStatement.setString(5, pastReservations.getRoomType().getRoomTypeID());
           preparedStatement.setString(6, pastReservations.getHotel().getHotelID());
           preparedStatement.setInt(7, pastReservations.getBookingMethod().getBookingMethodID());

            int rowUpdated = preparedStatement.executeUpdate();
            if(rowUpdated > 0) {
                System.out.println("Created successfully!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(PastReservations pastReservations) {
        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(UPDATE_SQL_QUERY);){

            preparedStatement.setDate(1, pastReservations.getArrivalDate());
            preparedStatement.setDate(2, pastReservations.getDepartureDate());
            preparedStatement.setInt(3, pastReservations.getGuests().getGuestID());
            preparedStatement.setString(4, pastReservations.getRoomType().getRoomTypeID());
            preparedStatement.setInt(5, pastReservations.getBookingMethod().getBookingMethodID());
            preparedStatement.setString(6, pastReservations.getHotel().getHotelID());
            preparedStatement.setString(7, pastReservations.getPastConfNumber());

            int rowUpdated = preparedStatement.executeUpdate();
            if(rowUpdated > 0) {
                System.out.println("Updated successfully!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(PastReservations pastReservations) {
        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(DELETE_RES_SQL_QUERY)) {
            statement.setString(1, pastReservations.getPastConfNumber());

            int rowDeleted = statement.executeUpdate();
            if(rowDeleted > 0){
                System.out.println("Res Confirmation Number: " + pastReservations.getPastConfNumber() + " -> delete successfully");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
            //logger.error(e.getMessage());
        }
    }

    public static PastReservationsDAO getInstance(){
        if(instance == null){
            instance = new PastReservationsDAO();
        }
        return instance;
    }
}
