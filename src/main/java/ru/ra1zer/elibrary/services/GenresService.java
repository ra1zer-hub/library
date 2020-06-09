package ru.ra1zer.elibrary.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ra1zer.elibrary.entities.Genre;
import ru.ra1zer.elibrary.repositories.GenreRepository;

import java.util.List;

@Service
public class GenresService {
    private GenreRepository genreRepository;

    @Autowired
    public void setGenreRepository(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> getAllGenres() {
        return (List<Genre>) genreRepository.findAll();
    }
}