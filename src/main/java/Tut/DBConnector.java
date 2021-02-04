package Tut;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private static final String URL = "jdbc:mysql://localhost/ptaki?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "ebi1ola";

    public static Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Połączono");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }
}
