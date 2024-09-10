package za.co.wtc.library.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import za.co.wtc.library.model.Publisher;


@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long>{
    @Query("select c.publisher from Book c where c.ISNI = :ISNI")
    Publisher findByISNI(String ISNI);
}
