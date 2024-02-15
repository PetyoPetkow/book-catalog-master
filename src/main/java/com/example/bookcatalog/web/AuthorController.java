package com.example.bookcatalog.web;

import com.example.bookcatalog.domain.author.model.AuthorCreateDto;
import com.example.bookcatalog.domain.author.model.AuthorReturnDto;
import com.example.bookcatalog.domain.author.model.AuthorUpdateDto;
import com.example.bookcatalog.domain.author.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<AuthorReturnDto> create(@Valid @RequestBody AuthorCreateDto authorCreateDto) {
        AuthorReturnDto authorReturnDto = authorService.create(authorCreateDto);
        return ResponseEntity.ok(authorReturnDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorReturnDto> getById(@PathVariable Long id) {
        AuthorReturnDto authorReturnDto = authorService.getById(id);
        return ResponseEntity.ok(authorReturnDto);
    }

    @GetMapping
    public ResponseEntity<Page<AuthorReturnDto>> getAll(Pageable pageable) {
        return ResponseEntity.ok(authorService.getAll(pageable));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AuthorReturnDto> update(@PathVariable Long id, @Valid @RequestBody AuthorUpdateDto authorUpdateDto) {
        AuthorReturnDto authorReturnDto = authorService.update(id, authorUpdateDto);
        return ResponseEntity.ok(authorReturnDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        authorService.delete(id);
    }
}
