package hotelReservations.daos;

import hotelReservations.connection.DatabaseConnection;
import hotelReservations.models.FrontDeskAgents;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class FrontDeskAgentsDAO implements GenericInterfaceDAO<FrontDeskAgents>{

    private static final String GET_BY_ID_SQL_QUERY = "SELECT First_Name, Last_Name FROM frontDeskAgents WHERE FrontDeskAgent_ID = ?";
    private static final String GET_ALL_SQL_QUERY = "SELECT * FROM frontDeskAgents";
    private static final String CREATE_SQL_QUERY = "INSERT INTO (First_Name, Last_Name, Gender, Working_Shift, Hourly_Pay) " +
            "frontDeskAgents VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_SQL_QUERY = "UPDATE frontDeskAgents SET First_Name = ?, Last_Name = ?," +
            "Gender = ?, Working_Shift = ?, Hourly_Pay = ? WHERE FrontDeskAgent_ID=?";
    private static final String DELETE_SQL_QUERY = "DELETE FROM frontDeskAgents FrontDeskAgent_ID= ?";
    private static FrontDeskAgentsDAO instance;

    @Override
    public void getByID(FrontDeskAgents frontDeskAgents) {
        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(GET_BY_ID_SQL_QUERY)){

            preparedStatement.setInt(1, frontDeskAgents.getFrontDeskAgentID());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);

                System.out.println("Front Desk ID: " + frontDeskAgents.getFrontDeskAgentID()
                        + "-> First Name: " + firstName + ", Last Name: " + lastName);
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
                int frontDeskAgentID = resultSet.getInt(1);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                String gender = resultSet.getString(4);
                String workingShift = resultSet.getString(5);
                BigDecimal hourlyPay = resultSet.getBigDecimal(6);

                System.out.println("Front Desk ID: " + frontDeskAgentID + "\nFirst Name" + firstName
                        + "\nLast Name: " + lastName
                        + "\nGender: " + gender
                        + "\nWorking Shift: " + workingShift
                        + "\nHourly Pay: " + hourlyPay
                        + "\n------");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(FrontDeskAgents frontDeskAgents) {
        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(CREATE_SQL_QUERY)){

            preparedStatement.setString(1, frontDeskAgents.getFirstName());
            preparedStatement.setString(2, frontDeskAgents.getLastName());
            preparedStatement.setString(3, frontDeskAgents.getGender());
            preparedStatement.setString(4, frontDeskAgents.getWorkingShift());
            preparedStatement.setBigDecimal(5, frontDeskAgents.getHourlyPay());

            int rowInserted = preparedStatement.executeUpdate();
            if(rowInserted > 0) {
                System.out.println("Created successfully!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(FrontDeskAgents frontDeskAgents) {
        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(UPDATE_SQL_QUERY)){

            preparedStatement.setString(1, frontDeskAgents.getFirstName());
            preparedStatement.setString(2, frontDeskAgents.getLastName());
            preparedStatement.setString(3, frontDeskAgents.getGender());
            preparedStatement.setString(4, frontDeskAgents.getWorkingShift());
            preparedStatement.setBigDecimal(5, frontDeskAgents.getHourlyPay());

            int rowInserted = preparedStatement.executeUpdate();
            if(rowInserted > 0) {
                System.out.println("Updated successfully!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(FrontDeskAgents frontDeskAgents) {
        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(DELETE_SQL_QUERY)){

            preparedStatement.setInt(1, frontDeskAgents.getFrontDeskAgentID());

            int rowDeleted = preparedStatement.executeUpdate();
            if(rowDeleted > 0){
                System.out.println("Delete Successfully!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static FrontDeskAgentsDAO getInstance(){
        if(instance == null){
            instance = new FrontDeskAgentsDAO();
        }
        return instance;
    }
}
