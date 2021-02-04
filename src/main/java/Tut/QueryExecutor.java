package Tut;

import java.sql.*;

public class QueryExecutor {
    static Connection connection = DBConnector.connect();

    public static ResultSet executeSelect(String selectQuery) {
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(selectQuery);
        }
        catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void executeQuery(String newNazwa_zwyczajowa, int ID_gatunku) {
        try ( PreparedStatement preparedStatement = connection.prepareStatement("UPDATE gatunki SET nazwa_zwyczajowa = ? WHERE ID_gatunku = ?"))
        {
            preparedStatement.setString(1, newNazwa_zwyczajowa);
            preparedStatement.setInt(2, ID_gatunku);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
