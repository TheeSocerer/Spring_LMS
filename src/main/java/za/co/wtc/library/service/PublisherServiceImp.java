package za.co.wtc.library.service;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.wtc.library.model.Publisher;
import za.co.wtc.library.repository.PublisherRepository;

@Service
public class PublisherServiceImp implements PublisherService{

    @Autowired
    private PublisherRepository publisherRepository;

    private static final Logger logger = LoggerFactory.getLogger(PublisherServiceImp.class);


    @Override
    public Publisher findByIdISNI(String ISNI) {
        // TODO Auto-generated method stub
        logger.info("search for publisher with ISNI {}", ISNI);
        try{
            Publisher publisher = publisherRepository.findByISNI(ISNI);
            if (publisher != null) {
                logger.info("Found publisher with ISNI {} details {}", ISNI, publisher);

            }
            return publisher;
        }catch(Exception exception){
            String message = "Error while searching for publisher with ISNI " + ISNI
          + exception.getMessage();
            logger.error(message, exception);
            throw new RuntimeException(message, exception);
        }
        
    }


    @Override
    public Publisher registPublisher(Publisher publisher) {
        // TODO Auto-generated method stub
        Publisher existingPublisher = publisherRepository.findByISNI(publisher.getBook().getISNI());
        if( existingPublisher != null){
            throw new RuntimeException("Publisher with ISNI ISNI already exist");
        }

        publisher.setDateCreated(LocalDateTime.now());
        
        return publisherRepository.save(publisher);
    
    }
    
}
