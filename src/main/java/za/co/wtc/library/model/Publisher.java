package za.co.wtc.library.model;

import java.time.LocalDateTime;
import javax.persistence.*;

@Entity
@Table(name = "author")
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @Column(name = "email")
    private String email;

}
