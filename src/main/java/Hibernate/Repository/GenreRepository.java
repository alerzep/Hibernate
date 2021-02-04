package Hibernate.Repository;

import Hibernate.model.Genre;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

public class GenreRepository {
    private final EntityManager entityManager;

    public GenreRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Genre save(Genre genre) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(genre);
            transaction.commit();
            return genre;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return null;
    }

    public void delete(Genre genre) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.remove(genre);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    // zwracanie wszystkich rekordów
    public List<Genre> findAll() {

        return entityManager.createQuery("from Genre", Genre.class).getResultList();
    }

    //wyszukiwanie rekordów po ID
    public Optional<Genre> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Genre.class, id));
    }

    // wyszukiwania po nazwie
    public List<Genre> findAllByName(String name) {
        return entityManager.createQuery("FROM Genre WHERE name = :name", Genre.class)
                .setParameter("name", name)
                .getResultList();
    }
}

//W tym i kolejnych zadaniach załóż, że poprawnie zainicjowany obiekt typu EntityManager przychodzi w konstruktorze tej klasy.
// Pamiętaj o konieczności stworzenia transakcji podczas modyfikacji obiektów w bazie.
//
//dodawanie rekordów typu Genre do bazy danych
//usuwanie rekordów typu Genre z bazy danych
//wyszukiwanie rekordów po nazwie
//wyszukiwanie rekordów po identyfikatorze

