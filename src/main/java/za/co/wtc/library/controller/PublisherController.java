package za.co.wtc.library.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import za.co.wtc.library.model.Publisher;
import za.co.wtc.library.service.PublisherService;

@RestController
@RequestMapping("/publishers")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @RequestMapping(method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<Publisher> registerPublisher(@RequestBody Publisher publisher){
        Publisher registeredCustomer = publisherService.registPublisher(publisher);
        return new ResponseEntity<>(registeredCustomer, HttpStatus.CREATED);
    }
    
}
