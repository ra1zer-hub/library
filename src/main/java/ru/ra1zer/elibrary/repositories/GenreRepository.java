package ru.ra1zer.elibrary.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.ra1zer.elibrary.entities.Genre;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Long> {

}