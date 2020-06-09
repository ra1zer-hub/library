package ru.ra1zer.elibrary.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.ra1zer.elibrary.entities.Publisher;

@Repository
public interface PublisherRepository extends CrudRepository<Publisher, Long> {
    String findOneNameById(Long id);
}