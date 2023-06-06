package hotelReservations.connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {

    private static String url;
    private static String username;
    private static String password;
    private static String databasePropertyFile = "src/main/resources/db.properties";
    private static Connection connection;
    private static Properties properties;

    public static Connection getConnection() {
        try (FileInputStream fileInputStream = new FileInputStream(databasePropertyFile)){
            properties = new Properties();
            properties.load(fileInputStream);

            url = properties.getProperty("db.url");
            username = properties.getProperty("db.username");
            password = properties.getProperty("db.password");

            connection = DriverManager.getConnection(url, username, password);
            //System.out.println("Successful Connection");
            return connection;
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
