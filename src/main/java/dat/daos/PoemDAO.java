package dat.daos;

import dat.entities.Poem;
import jakarta.persistence.EntityManager;

import java.util.List;

public class PoemDAO {
    private final EntityManager entityManager;

    public PoemDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Poem> getAllPoems() {
        return entityManager.createQuery("SELECT p FROM Poem p", Poem.class).getResultList();
    }

    public Poem getPoemById(Long id) {
        return entityManager.find(Poem.class, id);
    }

    public void savePoem(Poem poem) {
        if (!poemExists(poem)) {
            entityManager.getTransaction().begin();
            entityManager.persist(poem);
            entityManager.getTransaction().commit();
        } else {
            System.out.println("Poem already exists and will not be saved.");
        }
    }

    public void updatePoem(Poem poem) {
        entityManager.getTransaction().begin();
        entityManager.merge(poem);
        entityManager.getTransaction().commit();
    }

    public void deletePoem(Poem poem) {
        entityManager.getTransaction().begin();
        entityManager.remove(poem);
        entityManager.getTransaction().commit();
    }

    public boolean poemExists(Poem poem) {
        List<Poem> existingPoems = entityManager.createQuery(
                "SELECT p FROM Poem p WHERE LOWER(p.title) = LOWER(:title) AND LOWER(p.author) = LOWER(:author) AND LOWER(p.poem) = LOWER(:poem) AND p.type = :type", Poem.class)
            .setParameter("title", poem.getTitle())
            .setParameter("author", poem.getAuthor())
            .setParameter("poem", poem.getPoem())
            .setParameter("type", poem.getType())
            .getResultList();
        return !existingPoems.isEmpty();
    }
}