package ru.ra1zer.elibrary.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.ra1zer.elibrary.entities.Book;

public class BooksSpecs {
    public static Specification<Book> titleContains(String word) {
        return (Specification<Book>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title".toLowerCase()), "%" + word + "%");
    }
}