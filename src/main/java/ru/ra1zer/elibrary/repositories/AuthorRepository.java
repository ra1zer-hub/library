package ru.ra1zer.elibrary.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.ra1zer.elibrary.entities.Author;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
    String findOneFullNameById(Long id);
}