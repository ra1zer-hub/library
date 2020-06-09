package ru.ra1zer.elibrary.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "page_count")
    private int pageCount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Genre genre;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Author author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Publisher publisher;

    @Column(name = "image")
    private String image;

    @Column(name = "description")
    private String description;

    public Book(String title, String image, int pageCount, Genre genre, Author author, Publisher publisher, String content, String description) {
        this.title = title;
        this.content = content;
        this.pageCount = pageCount;
        this.genre = genre;
        this.author = author;
        this.publisher = publisher;
        this.image = image;
        this.description = description;
    }
}