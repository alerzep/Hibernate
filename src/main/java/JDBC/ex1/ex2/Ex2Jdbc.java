package JDBC.ex1.ex2;

import JDBC.ex1.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class Ex2Jdbc {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        try (Connection connection = connectionFactory.getConnection()) {
            MovieDAO movieDAOImpl = new MovieDAOImpl(connection);
            movieDAOImpl.deleteTable();
            movieDAOImpl.createTable();
            movieDAOImpl.createMovie(new Movie("The Godfather", "Drama", 1974));
            movieDAOImpl.createMovie(new Movie("The Matrix", "Action", 1999));
            movieDAOImpl.createMovie(new Movie("The Matrix 2", "Action", 2001));
   /*         movieDAOImpl.deleteMovie(2);
            movieDAOImpl.updateMoviesTitle(1, "Harry Potter and Philosopher Stone");
            movieDAOImpl.createMovie(new Movie("The Matrix 3", "Action", 2004));
            // jak wstawić coś o id 2 ?
            movieDAOImpl.findMovieById(1).ifPresent(System.out::println);*/
            //System.out.println(movieDAOImpl.findAll());
            System.out.println(movieDAOImpl.findMovieByGenre("Action"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
