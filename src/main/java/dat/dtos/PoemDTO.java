package dat.dtos;

import dat.entities.Poem;
import dat.entities.Type;
import lombok.Data;

@Data
public class PoemDTO {
    private Long id;
    private Type type;
    private String author;
    private String title;
    private String poem;

    public PoemDTO(Poem poem) {
        this.id = poem.getId();
        this.type = poem.getType();
        this.author = poem.getAuthor();
        this.title = poem.getTitle();
        this.poem = poem.getPoem();
    }
}