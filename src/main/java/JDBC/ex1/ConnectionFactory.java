package JDBC.ex1;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {
    private static Logger logger = LoggerFactory.getLogger(ConnectionFactory.class);

    private static final String SERVER_NAME = "localhost";
    private static final String DATABASE_NAME = "sda_library";
    private static final String USER = "root";
    private static final String PASSWORD = "ebi1ola";
    private static final int PORT = 3306;

    private DataSource dataSource;

    public ConnectionFactory() {
        try {
            dataSource = getDataSource();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private DataSource getDataSource() throws SQLException {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName(SERVER_NAME);
        dataSource.setDatabaseName(DATABASE_NAME);
        dataSource.setUser(USER);
        dataSource.setPassword(PASSWORD);
        dataSource.setPort(PORT);
        dataSource.setServerTimezone("Europe/Warsaw");
        return dataSource;
    }

    public Connection getConnection() throws SQLException {
        if(dataSource == null){
            throw new IllegalStateException("DataSource is not created.");
        }
        return dataSource.getConnection();
    }

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        try (Connection connection = connectionFactory.getConnection()){
            logger.info("Connection = " + connection);
            logger.info("Database name = " + connection.getCatalog());
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

}
