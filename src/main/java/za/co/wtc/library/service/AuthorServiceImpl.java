package za.co.wtc.library.service;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.wtc.library.dto.AuthorDTO;
import za.co.wtc.library.mapper.AuthorMapper;
import za.co.wtc.library.model.Author;
import za.co.wtc.library.repository.AuthorRepository;

@Service
public class AuthorServiceImpl implements AuthorService{

    @Autowired 
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorMapper authorMapper;

    private static final Logger logger = LoggerFactory.getLogger(AuthorServiceImpl.class);


    @Override
    public AuthorDTO editAuthor(AuthorDTO author) {
        // TODO Auto-generated method stub
        
        return null;
    }

    @Override
    public AuthorDTO findAuthorByEmail(String email) {
        logger.info("Search for author with email {}", email);
        try {
            Author author = authorRepository.findByEmail(email);
            if( author != null){
                logger.info("found author with email {}, Details {}",email, author);
            }
            return authorMapper.toDto(author);
        } catch (Exception e) {
            String message = "Error while searching for Author with email " + email;

            logger.error(message, e);
            throw new RuntimeException(message, e);
        }
    }

    @Override
    public AuthorDTO registerAuthor(AuthorDTO authorDTO) {
        // TODO Auto-generated method stub
        Author author = authorMapper.toEntity(authorDTO);

        Author existingAuthor = authorRepository.findByEmail(author.getEmail());
        if( existingAuthor != null){
            throw new RuntimeException("Author with emails already exists");
        }

        author.setDateCreated(LocalDateTime.now());
        author = authorRepository.save(author);
        return authorMapper.toDto(author);
    }
    
}
