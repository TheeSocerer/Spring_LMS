package za.co.wtc.library.dto;

import java.time.LocalDateTime;

public class AuthorDTO {
 // TODO ADD AUTHOR FIELDS
 
  private Long id;
  private String name;
  private String surname;
  private String title;
  private String email;
  private Integer rating;
  private LocalDateTime dateCreated;
  
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Integer getRating() {
    return rating;
  }

  public void setRating(Integer rating) {
    this.rating = rating;
  }

  public LocalDateTime getDateCreated() {
    return this.dateCreated;
  }

  public void setDateCreated(LocalDateTime date_created) {
    this.dateCreated = date_created;
  }
}
