package dat.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@ToString
@Entity
@Setter
@Accessors(chain = true)
public class Poem {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Type type;

    private String author;

    private String title;

    @Column(length = 1000)
    private String poem;

    public Poem(Type type, String author, String title, String poem) {
        this.type = type;
        this.author = author;
        this.title = title;
        this.poem = poem;
    }

    public Poem setType(Type type) {
        this.type = type;
        return this;
    }

    public Poem setAuthor(String author) {
        this.author = author;
        return this;
    }

    public Poem setTitle(String title) {
        this.title = title;
        return this;
    }

    public Poem setPoem(String poem) {
        this.poem = poem;
        return this;
    }
}