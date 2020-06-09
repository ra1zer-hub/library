package ru.ra1zer.elibrary.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fullname")
    private String fullname;

    @Basic(fetch = FetchType.LAZY) // ленивая инцилизации. Если не нужно, то не будет загружаться
    @OneToMany(mappedBy = "author")
    private List<Book> Books;

    public Author(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public String toString() {
        return fullname;
    }
}