package dat.controllers;

import dat.dtos.PoemDTO;
import dat.entities.Poem;
import io.javalin.http.Context;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.stream.Collectors;

public class PoemController {
    private final EntityManager entityManager;

    public PoemController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void getAllPoems(Context ctx) {
        List<Poem> poems = entityManager.createQuery("SELECT p FROM Poem p", Poem.class).getResultList();
        List<PoemDTO> poemDTOs = poems.stream()
            .map(poem -> {
                poem.setPoem(poem.getPoem().replace("\n", "<br>"));
                return new PoemDTO(poem);
            })
            .collect(Collectors.toList());
        ctx.json(poemDTOs);
    }

    public void getPoemById(Context ctx) {
        Long id = Long.parseLong(ctx.pathParam("id"));
        Poem poem = entityManager.find(Poem.class, id);
        if (poem != null) {
            poem.setPoem(poem.getPoem().replace("\n", "<br>"));
            ctx.json(new PoemDTO(poem));
        } else {
            ctx.status(404);
        }
    }

    public void createPoem(Context ctx) {
        Poem newPoem = ctx.bodyAsClass(Poem.class);
        List<Poem> existingPoems = entityManager.createQuery(
                "SELECT p FROM Poem p WHERE LOWER(p.title) = LOWER(:title) AND LOWER(p.author) = LOWER(:author) AND LOWER(p.poem) = LOWER(:poem) AND p.type = :type", Poem.class)
            .setParameter("title", newPoem.getTitle())
            .setParameter("author", newPoem.getAuthor())
            .setParameter("poem", newPoem.getPoem())
            .setParameter("type", newPoem.getType())
            .getResultList();
        if (!existingPoems.isEmpty()) {
            ctx.status(409).result("A poem with this title, author, content, and type already exists.");
            return;
        }
        entityManager.getTransaction().begin();
        entityManager.persist(newPoem);
        entityManager.getTransaction().commit();
        ctx.status(201);
    }

    public void updatePoem(Context ctx) {
        Long id = Long.parseLong(ctx.pathParam("id"));
        Poem poem = ctx.bodyAsClass(Poem.class);
        poem.setId(id);
        entityManager.getTransaction().begin();
        entityManager.merge(poem);
        entityManager.getTransaction().commit();
        ctx.status(204);
    }

    public void deletePoem(Context ctx) {
        Long id = Long.parseLong(ctx.pathParam("id"));
        Poem poem = entityManager.find(Poem.class, id);
        if (poem != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(poem);
            entityManager.getTransaction().commit();
            ctx.status(204);
        } else {
            ctx.status(404);
        }
    }
}
