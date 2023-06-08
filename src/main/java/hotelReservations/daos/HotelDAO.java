package hotelReservations.daos;

import hotelReservations.connection.DatabaseConnection;
import hotelReservations.models.Guests;
import hotelReservations.models.Hotel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HotelDAO implements GenericInterfaceDAO<Hotel>{

    private static final String GET_BY_ID_SQL_QUERY = "SELECT Name FROM hotel WHERE Hotel_ID=?";
    private static final String GET_ALL_SQL_QUERY = "SELECT * FROM hotel";
    private static final String CREATE_SQL_QUERY = "INSERT INTO hotel VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";
    private static final String UPDATE_SQL_QUERY = "UPDATE hotel SET Name = ?, Phone = ?, Fax = ? WHERE Hotel_ID=?";
    private static final String DELETE_SQL_QUERY = "DELETE FROM hotel WHERE Hotel_ID = ?";

    private static HotelDAO instance;
    Logger logger = LogManager.getLogger(HotelDAO.class.getName());

    @Override
    public void getByID(Hotel hotel) {
        try (PreparedStatement prepareStatement = DatabaseConnection.getConnection().prepareStatement(GET_BY_ID_SQL_QUERY))
        {
            prepareStatement.setString(1, hotel.getHotelID());
            ResultSet resultSet = prepareStatement.executeQuery();

            while (resultSet.next()){
                String name = resultSet.getString("Name");
                String address = resultSet.getString("Address");
                String city = resultSet.getString("City");
                String state = resultSet.getString("State");
                int zipCode = resultSet.getInt("ZipCode");
                String phone = resultSet.getString("Phone");
                String fax = resultSet.getString("Fax");

                logger.info("Hotel ID: " + hotel.getHotelID() + "\nHotel Name: " + name
                                        + "\nAddress: " + address
                                        + "\nCity: " + city
                                        + "\nState: " + state
                                        + "\nZipCde: " + zipCode
                                        + "\nPhone: " + phone
                                        + "\nFax: " + fax);

            }
        } catch (SQLException e) {
            // throw new RuntimeException(e);
            logger.error(e.getMessage());
        }
    }

    @Override
    public void getAll() {
        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(GET_ALL_SQL_QUERY)){

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String hotelID = resultSet.getString("Hotel_ID");
                String name = resultSet.getString("Name");
                String address = resultSet.getString("Address");
                String city = resultSet.getString("City");
                String state = resultSet.getString("State");
                int zipCode = resultSet.getInt("ZipCode");
                String phone = resultSet.getString("Phone");
                String fax = resultSet.getString("Fax");

                System.out.println("Hotel ID: " + hotelID + "\nName: " + name
                                    + "\nAddress: " + address
                                    + "\nCity: " + city
                                    + "\nState: " + state
                                    + "\nZipCde: " + zipCode
                                    + "\nPhone: " + phone
                                    + "\nFax: " + fax);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(Hotel hotel) {

        try (PreparedStatement prepareStatement = DatabaseConnection.getConnection().prepareStatement(CREATE_SQL_QUERY))
        {
            prepareStatement.setString(1, hotel.getHotelID());
            prepareStatement.setString(2, hotel.getHotelName());
            prepareStatement.setString(3, hotel.getAddress());
            prepareStatement.setString(4, hotel.getCity());
            prepareStatement.setString(5, hotel.getState());
            prepareStatement.setInt(6, hotel.getZipCode());
            prepareStatement.setString(7, hotel.getPhone());
            prepareStatement.setString(8, hotel.getFax());

            int rowInserted = prepareStatement.executeUpdate();
            if(rowInserted > 0){
                logger.info("Created successfully!");
            }
        } catch (SQLException e) {
           // throw new RuntimeException(e);
            logger.error(e.getMessage());
        }
    }

    @Override
    public void update(Hotel hotel) {
        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(UPDATE_SQL_QUERY)) {
            preparedStatement.setString(1, hotel.getHotelName());
            preparedStatement.setString(2, hotel.getPhone());
            preparedStatement.setString(3, hotel.getFax());
            preparedStatement.setString(4, hotel.getHotelID());

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
    public void delete(Hotel hotel) {
        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(DELETE_SQL_QUERY)) {
            preparedStatement.setString(1, hotel.getHotelID());

            int rowDeleted = preparedStatement.executeUpdate();
            if(rowDeleted > 0){
                logger.info("Delete successfully!");
            }
        } catch (SQLException e) {
           // throw new RuntimeException(e);
            logger.info(e.getMessage());
        }
    }

    public static HotelDAO getInstance(){
        if(instance == null){
            instance = new HotelDAO();
        }
        return instance;
    }
}
