package JDBC.ex1.ex2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MovieDAOImpl implements MovieDAO {

    private final Connection connection;

    public MovieDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createTable() {
        try (Statement statement = connection.createStatement()) {
            String sql = "CREATE TABLE MOVIES (id INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                    "title VARCHAR(100), " +
                    "genre VARCHAR(100), " +
                    "yearOfRelease INTEGER)";
            statement.execute(sql);
        } catch (SQLException e) {
            throw new DatabaseActionException(e);
        }
    }

    @Override
    public void deleteTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS MOVIES");
        } catch (final SQLException exp) {
            throw new DatabaseActionException(exp);
        }
    }

    // statement dotyczy raczej całej tabeli a preparedStatement  poszczególnych rekordów
    @Override
    public void createMovie(Movie movie) {
        try (PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO MOVIES (title, genre, yearOfRelease) VALUES (?,?,?)")) {
            insertStatement.setString(1, movie.getTitle());
            insertStatement.setString(2, movie.getGenre());
            insertStatement.setInt(3, movie.getYearOfRelease());
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseActionException(e);
        }
    }

    @Override
    public void deleteMovie(int id) {
        try (PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM MOVIES WHERE id = ?")) {
            deleteStatement.setInt(1, id);
            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseActionException(e);
        }
    }

    @Override
    public void updateMoviesTitle(int id, String newTitle) {
        try (PreparedStatement updateStatement = connection.prepareStatement("UPDATE MOVIES SET title = ? WHERE id = ?")) {
            updateStatement.setString(1, newTitle);
            updateStatement.setInt(2, id);
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseActionException(e);
        }
    }

    @Override
    public Optional<Movie> findMovieById(final int id) {
        try (PreparedStatement findStatement = connection.prepareStatement("SELECT * FROM MOVIES WHERE id = ?")) {
            findStatement.setInt(1, id);
            final boolean searchResult = findStatement.execute();
            if (searchResult) {
                final ResultSet foundMovie = findStatement.getResultSet();
                if (foundMovie.next()) {
                    final String title = foundMovie.getString(2);
                    final String genre = foundMovie.getString(3);
                    final Integer yearOfRelease = foundMovie.getInt(4);
                    return Optional.of(new Movie(id, title, genre, yearOfRelease));
                }
            }
            return Optional.empty();
        } catch (final SQLException exp) {
            throw new DatabaseActionException(exp);
        }
    }

    @Override
    public List<Movie> findAll() {
        try {
            ArrayList<Movie> movies = new ArrayList<>();
            Statement tableStatement = connection.createStatement();
            ResultSet result = tableStatement.executeQuery("SELECT * FROM MOVIES");
            while (result.next()) {
                Integer id = result.getInt(1);
                String title = result.getString(2);
                String genre = result.getString(3);
                Integer yearOfRelease = result.getInt(4);
                movies.add(new Movie(id, title, genre, yearOfRelease));
            }
            return movies;
        } catch (SQLException e) {
            throw new DatabaseActionException(e);
        }
    }

  /*  // @Override
    public void updateMovie(int id, Movie movie) {
            try (PreparedStatement updateStatement = connection.prepareStatement("UPDATE MOVIES SET title = ? genre = ? yearOfRelease = ? WHERE id = ?")) {
                updateStatement.setString(1, newTitle);
                updateStatement.setInt(4, id);
                String title = updateStatement.getString(1);
                String genre = result.getString(3);
                Integer yearOfRelease = result.getInt(4);
            } catch (SQLException e) {
                throw new DatabaseActionException(e);
            }
        }
    }*/


    @Override
    public List<Movie> findMovieByGenre(String genre) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM MOVIES WHERE genre = ?")) {
            preparedStatement.setString(1, genre);
            ArrayList<Movie> moviesByGenre = new ArrayList<>();

            ResultSet foundMovie = preparedStatement.executeQuery();
            while (foundMovie.next()) {
                Integer id = foundMovie.getInt(1);
                String title = foundMovie.getString(2);
                Integer yearOfRelease = foundMovie.getInt(4);
                // czy to jest ok ? czy na pewno nowy obiekt ?
                moviesByGenre.add(new Movie(id, title, genre, yearOfRelease));
            }
            return moviesByGenre;

        } catch (SQLException e) {
            throw new DatabaseActionException(e);
        }
    }
}
