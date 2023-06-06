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

    private static final String GET_GUEST_BY_ID_SQL_QUERY = "SELECT Guest_ID, First_Name, Last_Name FROM guests WHERE Guest_ID=?";
    private static final String GET_ALL_GUEST_SQL_QUERY = "SELECT * FROM guests";
    private static final String CREATE_GUEST_SQL_QUERY = "INSERT INTO guests (First_Name, Last_Name, Email, Phone) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_GUEST_SQL_QUERY = "UPDATE guests SET First_Name = ?, Last_Name = ?, Email = ?, Phone = ? WHERE Guest_ID=?";
    private static final String DELETE_GUEST_SQL_QUERY = "DELETE FROM guests WHERE Guest_ID = ?";
    private static GuestsDAO instance;

    Logger logger = LogManager.getLogger(GuestsDAO.class.getName());

    public Guests getByID(int guestID) {

        try (PreparedStatement prepareStatement = DatabaseConnection.getConnection().prepareStatement(GET_GUEST_BY_ID_SQL_QUERY))
        {
            prepareStatement.setInt(1, guestID);
            ResultSet resultSet = prepareStatement.executeQuery();

            while (resultSet.next()){
                Guests guests = new Guests();
                guests.setGuestID(resultSet.getInt("Guest_ID"));
                guests.setFirstName(resultSet.getString("First_Name"));
                guests.setLastName(resultSet.getString("Last_Name"));
                return guests;
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            logger.error(e.getMessage());
        }
        return null;
    }

    public List<Guests> getAll(){
        List<Guests> guestsList = new ArrayList<>();

        try (PreparedStatement prepareStatement = DatabaseConnection.getConnection().prepareStatement(GET_ALL_GUEST_SQL_QUERY);
             ResultSet resultSet = prepareStatement.executeQuery())
        {
            while (resultSet.next()){
                Guests guests = new Guests();
                guests.setGuestID(resultSet.getInt("Guest_ID"));
                guests.setFirstName(resultSet.getString("First_Name"));
                guests.setLastName(resultSet.getString("Last_Name"));
                guests.setEmail(resultSet.getString("Email"));
                guests.setPhone(resultSet.getString("Phone"));

                guestsList.add(guests);
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            logger.error(e.getMessage());
        }
        return guestsList;
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
                logger.info(guest + "-> Updated successfully!");
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
                logger.info("Guest ID: " + guest.getGuestID() + " -> delete successfully");
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

