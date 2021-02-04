package Hibernate.Repository;

import Hibernate.model.Movie;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

public class MovieRepository {

    private final EntityManager entityManager;

    public MovieRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Movie save(Movie movie) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(movie);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return movie;
    }

    public void remove(Movie movie) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.remove(movie);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
    public List<Movie> findByTitle(String title) {
        return entityManager.createQuery("FROM Movie where title = :title", Movie.class)
                .setParameter("title", title)
                .getResultList();
    }

    public Optional<Movie> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Movie.class, id));
    }

    public List<Movie> findAll() {
        return entityManager.createQuery("FROM movies", Movie.class).getResultList();
    }

    public List<Movie> findAllWithActors() {
        return entityManager.createQuery("FROM Movie m LEFT JOIN FETCH m.actors", Movie.class).getResultList();
    }
}
