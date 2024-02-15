package com.example.bookcatalog.domain.genre.entity;

import com.example.bookcatalog.domain.book.entity.Book;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(min = 2, max = 32, message = "Name must be between 2 and 32 chars long")
    private String name;

    @Column(nullable = false)
    @Size(min = 32, max = 256, message = "Description must be between 32 and 256 chars long")
    private String description;

    @ManyToMany(mappedBy = "genres")
    private List<Book> books;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
