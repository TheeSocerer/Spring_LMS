package za.co.wtc.library.mapper;

import org.springframework.stereotype.Component;

import za.co.wtc.library.dto.AuthorDTO;
import za.co.wtc.library.model.Author;

@Component
public class AuthorMapper {

    public AuthorDTO toDto(Author author){
        if( author == null){
            return null;
        }

        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setName(author.getName());
        authorDTO.setSurname(author.getSurname());
        authorDTO.setEmail(author.getEmail());
        authorDTO.setId(author.getId());
        authorDTO.setTitle(author.getTitle());
        authorDTO.setDateCreated(author.getDateCreated());
        authorDTO.setRating(author.getRating());

        return authorDTO;

    }
    

    public Author toEntity( AuthorDTO authorDTO){
        Author author = new Author();
        author.setName(authorDTO.getName());
        author.setSurname(authorDTO.getSurname());
        author.setEmail(authorDTO.getEmail());
        author.setId(authorDTO.getId());
        author.setDateCreated(authorDTO.getDateCreated());
        author.setRating(authorDTO.getRating());
        author.setTitle(authorDTO.getTitle());

        return author;
    }
}
