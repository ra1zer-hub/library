package ru.ra1zer.elibrary.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ra1zer.elibrary.entities.Publisher;
import ru.ra1zer.elibrary.repositories.PublisherRepository;

import java.util.List;

@Service
public class PublishersService {
    private PublisherRepository publisherRepository;

    @Autowired
    public void setPublisherRepository(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public List<Publisher> getAllPublishers() {
        return (List<Publisher>) publisherRepository.findAll();
    }
}