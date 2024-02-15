package com.example.bookcatalog.domain.book.service;

import com.example.bookcatalog.domain.book.entity.Book;
import com.example.bookcatalog.domain.book.model.BookCreateDto;
import com.example.bookcatalog.domain.book.model.BookReturnDto;
import com.example.bookcatalog.domain.book.model.BookUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    BookReturnDto create(BookCreateDto bookCreateDto);

    BookReturnDto getById(Long id);

    Page<BookReturnDto> getAll(Pageable pageable);

    BookReturnDto update(Long id, BookUpdateDto bookUpdateDto);

    void delete(Long id);

    Book returnBookById(Long id);

    List<BookReturnDto> search(String title, String authorName, String genre);
}
