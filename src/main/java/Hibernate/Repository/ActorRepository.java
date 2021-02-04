package Hibernate.Repository;

import Hibernate.model.Actor;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

public class ActorRepository {

    private final EntityManager entityManager;

    public ActorRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Actor save(Actor actor) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(actor); // actorID = 0
            transaction.commit(); // actorID = 1
            return actor;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            return null;
        }
    }

    public Optional<Actor> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Actor.class, id));
    }

    public List<Actor> findAllBornAfter(int year) {
        String sql = "FROM Actor a WHERE a.yearOfBirth > :year";
        return entityManager.createQuery(sql, Actor.class)
                .setParameter("year", year)
                .getResultList();

    }

    public List<Actor> findAllWithLastNameEndsWith(String lastNameEnd) {
        String sql = "FROM Actor a WHERE a.lastName LIKE :lastName" ;
    return entityManager.createQuery(sql, Actor.class)
            .setParameter("lastName", "%" + lastNameEnd)
            .getResultList();
    }

}
