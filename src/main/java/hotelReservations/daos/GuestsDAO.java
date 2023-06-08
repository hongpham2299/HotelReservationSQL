package hotelReservations.daos;

import hotelReservations.connection.DatabaseConnection;
import hotelReservations.models.Guests;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GuestsDAO implements GenericInterfaceDAO<Guests> {

    private static final String GET_BY_ID_SQL_QUERY = "SELECT * FROM guests WHERE Guest_ID=?";
    private static final String GET_ALL_SQL_QUERY = "SELECT * FROM guests";
    private static final String CREATE_GUEST_SQL_QUERY = "INSERT INTO guests (First_Name, Last_Name, Email, Phone) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_GUEST_SQL_QUERY = "UPDATE guests SET First_Name = ?, Last_Name = ?, Email = ?, Phone = ? WHERE Guest_ID=?";
    private static final String DELETE_GUEST_SQL_QUERY = "DELETE FROM guests WHERE Guest_ID = ?";
    private static GuestsDAO instance;

    Logger logger = LogManager.getLogger(GuestsDAO.class.getName());

    @Override
    public void getByID(Guests guests) {
        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(GET_BY_ID_SQL_QUERY)) {
            preparedStatement.setInt(1, guests.getGuestID());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String firstName = resultSet.getString("First_Name");
                String lastName = resultSet.getString("Last_Name");
                String email = resultSet.getString("Email");
                String phone = resultSet.getString("Phone");

                System.out.println("Guest ID: " + guests.getGuestID() + "\nFirst Name: " + firstName
                        + "\nLast Name: " + lastName
                        + "\nEmail: " + email
                        + "\nPhone: " + phone + "\n-------");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void getAll() {
        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(GET_ALL_SQL_QUERY)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int guestId = resultSet.getInt(1);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                String email = resultSet.getString(4);
                String phone = resultSet.getString(5);

                System.out.println("Guest ID: " + guestId + "\nFirst Name: " + firstName
                        + "\nLast Name: " + lastName
                        + "\nEmail: " + email
                        + "\nPhone: " + phone + "\n-------");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(Guests guest) {

        try (PreparedStatement prepareStatement = DatabaseConnection.getConnection().prepareStatement(CREATE_GUEST_SQL_QUERY))
        {
            prepareStatement.setString(1, guest.getFirstName());
            prepareStatement.setString(2, guest.getLastName());
            prepareStatement.setString(3, guest.getEmail());
            prepareStatement.setString(4, guest.getPhone());

            int rowInserted = prepareStatement.executeUpdate();
            if(rowInserted > 0){
                logger.info("Guest First Name: " + guest.getFirstName() + " -> Created successfully!");
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            logger.error(e.getMessage());
        }
    }

    @Override
    public void update(Guests guest) {

        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(UPDATE_GUEST_SQL_QUERY)) {
            preparedStatement.setString(1, guest.getFirstName());
            preparedStatement.setString(2, guest.getLastName());
            preparedStatement.setString(3, guest.getEmail());
            preparedStatement.setString(4, guest.getPhone());
            preparedStatement.setInt(5, guest.getGuestID());

            int rowUpdated = preparedStatement.executeUpdate();
            if(rowUpdated > 0) {
                logger.info("Guest ID " + guest.getGuestID() + "-> Updated successfully!");
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            logger.error(e.getMessage());
        }
    }

    @Override
    public void delete(Guests guest) {

        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(DELETE_GUEST_SQL_QUERY)) {
            preparedStatement.setInt(1, guest.getGuestID());

            int rowDeleted = preparedStatement.executeUpdate();
            if(rowDeleted > 0){
                logger.info("Guest ID: " + guest.getGuestID() + " -> Delete Successfully");
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            logger.error(e.getMessage());
        }
    }

    public static GuestsDAO getInstance(){
        if(instance == null){
            instance = new GuestsDAO();
        }
        return instance;
    }

}

