package dat;

import dat.config.HibernateConfig;
import dat.daos.PoemDAO;
import dat.entities.Poem;
import dat.entities.Type;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class SeedDatabaseMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory("poems");
        EntityManager entityManager = emf.createEntityManager();
        PoemDAO poemDAO = new PoemDAO(entityManager);

        seedDatabase(poemDAO);

        entityManager.close();
        emf.close();
    }

    public static void seedDatabase(PoemDAO poemDAO) {
        List<Poem> poems = List.of(
            new Poem()
                .setType(Type.ALLITERATION)
                .setAuthor("ChatGPT")
                .setTitle("Whispering Winds")
                .setPoem("Whispering winds waltz wildly, " +
                    "Waking willows with whispered words, " +
                    "While waves wash weary worlds, " +
                    "Whistling warm wishes westward."),
            new Poem()
                .setType(Type.ALLITERATION)
                .setAuthor("ChatGPT")
                .setTitle("Singing Stars")
                .setPoem("Silent stars softly sing, " +
                    "Shining bright with silver wings, " +
                    "Skies stretch, so still they stand, " +
                    "Sending sparkles to the sand."),
            new Poem()
                .setType(Type.ALLITERATION)
                .setAuthor("ChatGPT")
                .setTitle("Daring Dreams")
                .setPoem("Daring dreams dance daily, " +
                    "Distant desires drift dimly, " +
                    "Determined hearts dive deep, " +
                    "Discovering dawns as daylight creeps."),
            new Poem()
                .setType(Type.ALLITERATION)
                .setAuthor("ChatGPT")
                .setTitle("Gentle Giants")
                .setPoem("Giant giraffes gracefully glide, " +
                    "Grazing green grass near golden tides, " +
                    "Gentle giants gather gladly, " +
                    "Guiding gazelles galloping madly."),
            new Poem()
                .setType(Type.ALLITERATION)
                .setAuthor("ChatGPT")
                .setTitle("Fiery Flames")
                .setPoem("Flickering flames fiercely flash, " +
                    "Fueling forests, fast to ash, " +
                    "Fireflies flick and flutter by, " +
                    "Framing flames against the sky."),
            new Poem()
                .setType(Type.ALLITERATION)
                .setAuthor("ChatGPT")
                .setTitle("Quiet Quests")
                .setPoem("Quietly quails quiver in queues, " +
                    "Questioning quickly the quixotic views, " +
                    "Quaintly they quack, quite queer and quaint, " +
                    "Questing quietly, without complaint."),
            new Poem()
                .setType(Type.ALLITERATION)
                .setAuthor("ChatGPT")
                .setTitle("Brilliant Blossoms")
                .setPoem("Blossoms bloom beneath the breeze, " +
                    "Brightly bathing in the bees, " +
                    "Bravely breaking from the boughs, " +
                    "Breathing life into the now."),
            new Poem()
                .setType(Type.ALLITERATION)
                .setAuthor("ChatGPT")
                .setTitle("Frosty Fields")
                .setPoem("Frosty fields freeze fast in fall, " +
                    "Foggy ferns form fragile walls, " +
                    "Frozen footprints fade away, " +
                    "Frosted fields greet frozen day."),
            new Poem()
                .setType(Type.ALLITERATION)
                .setAuthor("ChatGPT")
                .setTitle("Towering Trees")
                .setPoem("Tall trees twist toward the sun, " +
                    "Their trunks trembling, twisted, spun, " +
                    "Together they tower, tall and true, " +
                    "Timeless tales of skies so blue."),
            new Poem()
                .setType(Type.ALLITERATION)
                .setAuthor("ChatGPT")
                .setTitle("Mystic Moonlight")
                .setPoem("Mystic moonlight makes the moor, " +
                    "Misty mountains mesmerize more, " +
                    "Magic murmurs in the mist, " +
                    "Moonbeams mark where shadows twist.")
        );

        poems.forEach(poemDAO::savePoem);
    }
}