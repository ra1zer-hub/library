package ru.ra1zer.elibrary.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.ra1zer.elibrary.entities.Book;
import ru.ra1zer.elibrary.repositories.BookRepository;

import java.util.List;

@Service
public class BooksService {
    private BookRepository bookRepository;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return (List<Book>) bookRepository.findAll();
    }

    public List<Book> searchBooksByGenre(Long genreId) {
        return bookRepository.findBooksByGenre_Id(genreId);
    }

    public Page<Book> getBooksWithPagingAndFiltering(Specification<Book> specifications, Pageable pageable) {
        return bookRepository.findAll(specifications, pageable);

    }

    public void saveOrUpdate(Book book) {
        bookRepository.save(book);
    }

    public Book getById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}