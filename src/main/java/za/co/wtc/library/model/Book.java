package za.co.wtc.library.model;


import java.time.LocalDateTime;
import javax.persistence.*;

import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "ISBN")
    private String ISBN;

    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "publisher", referencedColumnName = "id")
    private Publisher publisher;
    
    @Column(name = "date_published")
    private LocalDateTime datePublished;
    
    @Column(name = "category")
    private String category;
    
    @Column(name = "date_created")
    private LocalDateTime dateCreated;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "customer_id")
    private Set<CustomerBooks> customerBooks = new HashSet<>();

    public Book(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String iSBN) {
        ISBN = iSBN;
    }

    public Author getAuthorId() {
        return author;
    }

    public void setAuthorId(Author author) {
        this.author = author;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public LocalDateTime getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(LocalDateTime datePublished) {
        this.datePublished = datePublished;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }
    
}
