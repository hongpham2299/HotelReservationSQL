package hotelReservations.connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class ConnectionPool {

    private static int poolSize;
    private BlockingDeque<Connection> connectionBlockingDeque;
    private static ConnectionPool instance;
    private String url;
    private String username;
    private String password;
    private String databasePropertyFile = "src/main/resources/db.properties";
    private Connection connection;
    private static Properties properties;

    public ConnectionPool(int poolSize){

        connectionBlockingDeque = new LinkedBlockingDeque<>(poolSize);

        for (int i = 0; i < poolSize; i++) {
            try (FileInputStream fileInputStream = new FileInputStream(databasePropertyFile)) {
                properties = new Properties();
                properties.load(fileInputStream);

                url = properties.getProperty("db.url");
                username = properties.getProperty("db.username");
                password = properties.getProperty("db.password");

                connection = DriverManager.getConnection(url, username, password);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            connectionBlockingDeque.add(connection);
        }
    }

    public Connection getConnection(){
        try {
            return connectionBlockingDeque.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void releaseConnection(Connection connection){
        connectionBlockingDeque.add(connection);
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            synchronized (ConnectionPool.class) {
                if (instance == null) {
                    instance = new ConnectionPool(poolSize);
                }
            }
        }
        return instance;
    }

}
