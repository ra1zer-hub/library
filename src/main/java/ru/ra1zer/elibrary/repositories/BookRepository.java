package ru.ra1zer.elibrary.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.ra1zer.elibrary.entities.Book;

import java.util.List;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Long>, JpaSpecificationExecutor<Book> {

    List<Book> findBooksByGenre_Id(Long id);
}