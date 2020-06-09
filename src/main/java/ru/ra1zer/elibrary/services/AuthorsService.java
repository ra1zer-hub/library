package ru.ra1zer.elibrary.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ra1zer.elibrary.entities.Author;
import ru.ra1zer.elibrary.repositories.AuthorRepository;

import java.util.List;

@Service
public class AuthorsService {
    private AuthorRepository authorRepository;

    @Autowired
    public void setAuthorRepository(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAllAuthors() {
        return (List<Author>) authorRepository.findAll();
    }
}