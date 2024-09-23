package za.co.wtc.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.co.wtc.library.model.Author;


@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    
  @Query("select a from Author a "
    + "where a.email = :email")
  Author findByEmail(String email);
}
