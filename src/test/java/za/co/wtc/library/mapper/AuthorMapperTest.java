package za.co.wtc.library.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.co.wtc.library.dto.AuthorDTO;
import za.co.wtc.library.model.Author;

@SpringBootTest()
public class AuthorMapperTest {

    @Autowired
    private AuthorMapper authorMapper;

    @Test
    public void testToDto() {
        // Given
        Author author = new Author();
        author.setName("John");
        author.setEmail("John@example.com");
        author.setSurname("Doe");
        author.setTitle("Prof");
        author.setId(2L);
        author.setRating(5);
        author.setDateCreated(LocalDateTime.of(2023, 1, 1, 10, 0));

        // When
        AuthorDTO authorDTO = authorMapper.toDto(author);

        //Then
        assertNotNull(authorDTO);
        assertEquals(author.getName(), authorDTO.getName());
        assertEquals(author.getSurname(), authorDTO.getSurname());
        assertEquals(author.getTitle(), authorDTO.getTitle());
        assertEquals(author.getEmail(), authorDTO.getEmail());
        assertEquals(author.getDateCreated(),authorDTO.getDateCreated());
        assertEquals(author.getId(), authorDTO.getId());
    }

    @Test
    public void testToEntity(){

        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setName("John");
        authorDTO.setEmail("John@example.com");
        authorDTO.setSurname("Doe");
        authorDTO.setTitle("Prof");
        authorDTO.setId(2L);
        authorDTO.setRating(5);
        authorDTO.setDateCreated(LocalDateTime.of(2023, 1, 1, 10, 0));

        Author author = authorMapper.toEntity(authorDTO);

        //Then
        assertNotNull(authorDTO);
        assertEquals(author.getName(), authorDTO.getName());
        assertEquals(author.getSurname(), authorDTO.getSurname());
        assertEquals(author.getTitle(), authorDTO.getTitle());
        assertEquals(author.getEmail(), authorDTO.getEmail());
        assertEquals(author.getDateCreated(),authorDTO.getDateCreated());
        assertEquals(author.getId(), authorDTO.getId());

    }

    @Test
    public void  toDtoNull(){
        //Given
        Author author = null;

        //When

        AuthorDTO authorDTO = authorMapper.toDto(author);

        //Then
        assertNull(authorDTO);
    }
}
