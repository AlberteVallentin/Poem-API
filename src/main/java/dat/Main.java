package dat;

import dat.config.HibernateConfig;
import dat.controllers.PoemController;
import io.javalin.Javalin;
import jakarta.persistence.EntityManagerFactory;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory("poems");
        PoemController poemController = new PoemController(emf);

        var app = Javalin.create(config -> {
            config.router.contextPath = "/api";
            config.bundledPlugins.enableRouteOverview("/routes");
        }).start(7007);

        app.get("/poems", poemController::getAllPoems);
        app.get("/poem/{id}", poemController::getPoemById);
        app.post("/poem", poemController::createPoem);
        app.put("/poem/{id}", poemController::updatePoem);
        app.delete("/poem/{id}", poemController::deletePoem);
    }
}