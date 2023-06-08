package hotelReservations.daos;

import hotelReservations.connection.DatabaseConnection;
import hotelReservations.models.RoomType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomTypeDAO implements GenericInterfaceDAO<RoomType> {

    private static final String GET_BY_ID_SQL_QUERY = "SELECT Room_Type_Name FROM roomTypes WHERE Room_Type_ID=?";
    private static final String GET_ALL_SQL_QUERY = "SELECT * FROM roomTypes";
    private static final String CREATE_SQL_QUERY = "INSERT INTO roomTypes VALUES (?, ?)";
    private static final String UPDATE_SQL_QUERY = "UPDATE roomTypes SET Room_Type_Name = ? WHERE Room_Type_ID=?";
    private static final String DELETE_SQL_QUERY = "DELETE FROM roomTypes WHERE Room_Type_ID = ?";

    private static RoomTypeDAO instance;

    @Override
    public void getByID(RoomType roomType) {
        try(PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(GET_BY_ID_SQL_QUERY)) {
            preparedStatement.setString(1, roomType.getRoomTypeID());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String roomTypeName = resultSet.getString("Room_Type_Name");

                System.out.println("Room Type ID: " + roomType.getRoomTypeID()
                        + ", Room Type Name: " + roomTypeName);
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
                String roomTypeID = resultSet.getString("Room_Type_ID");
                String roomTypeName = resultSet.getString("Room_Type_Name");

                System.out.println("Room Type ID: " + roomTypeID
                                    + "\nRoom Type Name: " + roomTypeName + "\n----");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(RoomType roomType) {
        try (PreparedStatement prepareStatement = DatabaseConnection.getConnection().prepareStatement(CREATE_SQL_QUERY))
        {
            prepareStatement.setString(1, roomType.getRoomTypeID());
            prepareStatement.setString(2, roomType.getRoomTypeName());

            int rowInserted = prepareStatement.executeUpdate();
            if(rowInserted > 0){
                System.out.println("Created successfully!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
            //logger.error(e.getMessage());
        }
    }

    @Override
    public void update(RoomType roomType) {
        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(UPDATE_SQL_QUERY)) {
            preparedStatement.setString(1, roomType.getRoomTypeName());
            preparedStatement.setString(2, roomType.getRoomTypeID());

            int rowUpdated = preparedStatement.executeUpdate();
            if(rowUpdated > 0) {
                System.out.println("Updated successfully!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
           // logger.error(e.getMessage());
        }
    }

    @Override
    public void delete(RoomType roomType) {
        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(DELETE_SQL_QUERY)) {
            preparedStatement.setString(1, roomType.getRoomTypeID());

            int rowDeleted = preparedStatement.executeUpdate();
            if(rowDeleted > 0){
                System.out.println("Delete Successfully!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
           // logger.error(e.getMessage());
        }
    }

    public static RoomTypeDAO getInstance(){
        if(instance == null){
            instance = new RoomTypeDAO();
        }
        return instance;
    }
}
