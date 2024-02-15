package com.example.bookcatalog.web;

import com.example.bookcatalog.domain.book.model.BookCreateDto;
import com.example.bookcatalog.domain.book.model.BookReturnDto;
import com.example.bookcatalog.domain.book.model.BookUpdateDto;
import com.example.bookcatalog.domain.book.service.BookService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookReturnDto> create(@Valid @RequestBody BookCreateDto bookCreateDto) {
        final var bookReturnDto = bookService.create(bookCreateDto);
        return ResponseEntity.ok(bookReturnDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookReturnDto> getById(@PathVariable Long id) {
        final var bookReturnDto = bookService.getById(id);
        return ResponseEntity.ok(bookReturnDto);
    }

    @GetMapping
    public ResponseEntity<Page<BookReturnDto>> getAll(Pageable pageable) {
        return ResponseEntity.ok(bookService.getAll(pageable));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BookReturnDto> update(@PathVariable Long id, @Valid @RequestBody BookUpdateDto bookUpdateDto) {
        final var bookReturnDto = bookService.update(id, bookUpdateDto);
        return ResponseEntity.ok(bookReturnDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookReturnDto>> search(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "authorName", required = false) String authorName,
            @RequestParam(value = "genre", required = false) String genre
    ) {
        return ResponseEntity.ok(bookService.search(title, authorName, genre));
    }
}
