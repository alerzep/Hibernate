package JDBC.ex1;

import java.sql.*;

public class ExJdbc {


    private static final String MYSQL_DB_URL = "jdbc:mysql://localhost:3306/sda_library?serverTimezone=UTC";
    private static final String MYSQL_USER = "root";
    private static final String MYSQL_PASSWORD = "ebi1ola";

    public static void main(String[] args) throws ClassNotFoundException {
ConnectionFactory connectionFactory = new ConnectionFactory();
        // try (Connection connection = DriverManager.getConnection(MYSQL_DB_URL, MYSQL_USER, MYSQL_PASSWORD)) {
            try (Connection connection = connectionFactory.getConnection()) {
        try (Statement deleteStatement = connection.createStatement()) {
                deleteStatement.execute("DROP TABLE IF EXIST MOVIES");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try (Statement createStatement = connection.createStatement()) {

                createStatement.execute("CREATE TABLE MOVIES (id INTEGER PRIMARY KEY AUTO_INCREMENT," +
                        "tittle VARCHAR(100), " +
                        "genre VARCHAR(100), " +
                        "yearOfRelease INTEGER)");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // INSERT
            try (Statement insertStatement = connection.createStatement()) {
                String insertMatrixStatement = "INSERT INTO MOVIES " +
                        "(tittle, genre, yearOfRelease) VALUES ('Matrix1', 'Action', 1999)";
                String insertMatrix2Statement = "INSERT INTO MOVIES " +
                        "(tittle, genre, yearOfRelease) VALUES ('Matrix2', 'Action', 2002)";
                String insertMatrix3Statement = "INSERT INTO MOVIES " +
                        "(tittle, genre, yearOfRelease) VALUES ('Matrix3', 'Action', 2004)";
                insertStatement.execute(insertMatrixStatement);
                insertStatement.execute(insertMatrix2Statement);
                insertStatement.execute(insertMatrix3Statement);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try (Statement selectStatement = connection.createStatement()) {
                String allMoviesQuery = "SELECT * FROM MOVIES";
                ResultSet rs = selectStatement.executeQuery(allMoviesQuery);

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String tittle = rs.getString("tittle");
                    String genre = rs.getString("genre");
                    int yearOfRelease = rs.getInt("yearOfRelease");

                    System.out.println("###");
                    System.out.println("Id:" + id);
                    System.out.println("Tittle: " + tittle);
                    System.out.println("Genre: " + genre);
                    System.out.println("Year of release: " + yearOfRelease);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
//update
            try (PreparedStatement updateStatement = connection.prepareStatement("UPDATE MOVIES SET yearOfRelease = ? WHERE id = ?")){
                updateStatement.setInt(1,2003);
                updateStatement.setInt(2,2);
                updateStatement.executeUpdate();
            }
            try (PreparedStatement updateStatement = connection.prepareStatement("UPDATE MOVIES SET tittle = ? WHERE id = ?")){
                updateStatement.setString(1,"Matrix One");
                updateStatement.setInt(2,1);
                updateStatement.executeUpdate();
            }

            //delete
//
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
