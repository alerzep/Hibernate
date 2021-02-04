package Hibernate;

import Hibernate.Repository.MovieRepository;
import Hibernate.model.Actor;
import Hibernate.model.Address;
import Hibernate.model.Genre;
import Hibernate.model.Movie;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

@Slf4j
public class HibernateDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Movie.class)
                .addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Genre.class)
                .addAnnotatedClass(Address.class)
                .buildSessionFactory();

       //callMovieQueries(sessionFactory);
       /*  callGenreQueries(sessionFactory);
        callActorQueries(sessionFactory);
*/
        //utwoerzyc adresy i przypisac do aktorów i sprawdzic na bazie
        // przetestować usuwanie aktorow/adresow

        MovieRepository movieRepository = new MovieRepository(sessionFactory.createEntityManager());
        List<Movie> movies = movieRepository.findAllWithActors();
        for (Movie movie : movies) {
            log.info(movie.toString());
            log.info("Actors:");
            for (Actor actor : movie.getActors()) {
                log.info(actor.toString());
            }
        }
        //movieRepository.remove(movies.get(0));
    }
/*   private static void callMovieQueries(SessionFactory sessionFactory) {
        EntityManager em = sessionFactory.createEntityManager();
        MovieRepository movieRepository = new MovieRepository(em);
        GenreRepository genreRepository = new GenreRepository(em);
        Genre actionGenre = getActionGenre(genreRepository);
        movieRepository.save(new Movie("Matrix", 1999, actionGenre, new ArrayList<>()));
        movieRepository.save(new Movie("Matrix 2", 2001, actionGenre, new ArrayList<>()));
        movieRepository.save(new Movie("Matrix 3", 2001, actionGenre, new ArrayList<>()));
    }*/


 /*   private static Genre getActionGenre(GenreRepository genreRepository) {
        List<Genre> actionGenres = genreRepository.findAllByName("Action");
        Genre actionGenre = null;
        if (actionGenres != null && !actionGenres.isEmpty()) {
            actionGenre = actionGenres.get(0);
        }
        return actionGenre;
    }*/

/*    private static void callActorQueries(SessionFactory sessionFactory) {
        EntityManager em = sessionFactory.createEntityManager();
        ActorRepository actorRepository = new ActorRepository(em);
        MovieRepository movieRepository = new MovieRepository(em);

        List<Movie> matrixMovie = movieRepository.findByTitle("Matrix");

        actorRepository.save(Actor.builder().name("Keanu").lastName("Reeves").yearOfBirth(1964).movies(matrixMovie).build());
        actorRepository.save(Actor.builder().name("Carrie-Anne").lastName("Moss").yearOfBirth(1967).movies(matrixMovie).build());
        actorRepository.save(Actor.builder().name("Laurence").lastName("Fishburne").yearOfBirth(1961).movies(matrixMovie).build());

        Optional<Actor> actorId4 = actorRepository.findById(4L);
        log.info("Actor with id=4");
        if (actorId4.isPresent()) {
            log.info(actorId4.get().toString());
        } else {
            log.info("Actor not found");
        }

        log.info("Young actors");
        List<Actor> youngActors = actorRepository.findAllBornAfter(1965);
        for (Actor actor : youngActors) {
            log.info(actor.toString());
        }

        log.info("Last name S actors");
        List<Actor> sActors = actorRepository.findAllWithLastNameEndsWith("s");
        for (Actor actor : sActors) {
            log.info(actor.toString());
        }
    }*/

    /*private static void callGenreQueries(SessionFactory sessionFactory) {
        GenreRepository genreRepository = new GenreRepository(sessionFactory.createEntityManager());
        genreRepository.save(new Genre("Action", new ArrayList<>()));
        Genre comedy = genreRepository.save(new Genre("Comedy", new ArrayList<>()));
        Genre drama = genreRepository.save(new Genre("Drama", new ArrayList<>()));
        //genreRepository.delete(comedy);

        List<Genre> genres = genreRepository.findAll();
        Long longdes = genres.get(0).getId();
        for (Genre genre : genres) {
            log.info(genre.toString());
        }
        log.info("First genre: ");
        Optional<Genre> genre = genreRepository.findById(longdes);

        if (genre.isPresent()) {
            log.info(genre.get().toString());
        } else {
            log.info("Not found");
        }
        List<Genre> comedyGenres = genreRepository.findAllByName("Comedy");
        log.info("Comedy genres");
        for (Genre comedyGenre : comedyGenres) {
            log.info(comedyGenre.toString());
        }
    }*/
}
