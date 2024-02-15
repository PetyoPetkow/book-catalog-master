package com.example.bookcatalog.web;

import com.example.bookcatalog.domain.library.model.LibraryCreateDto;
import com.example.bookcatalog.domain.library.model.LibraryReturnDto;
import com.example.bookcatalog.domain.library.model.LibraryUpdateDto;
import com.example.bookcatalog.domain.library.service.LibraryService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/libraries")
public class LibraryController {
    private final LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @PostMapping
    public ResponseEntity<LibraryReturnDto> create(@Valid @RequestBody LibraryCreateDto libraryCreateDto) {
        LibraryReturnDto libraryReturnDto = libraryService.create(libraryCreateDto);
        return ResponseEntity.ok(libraryReturnDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibraryReturnDto> getById(@PathVariable Long id) {
        LibraryReturnDto libraryReturnDto = libraryService.getById(id);
        return ResponseEntity.ok(libraryReturnDto);
    }

    @GetMapping
    public ResponseEntity<Page<LibraryReturnDto>> getAll(Pageable pageable) {
        return ResponseEntity.ok(libraryService.getAll(pageable));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<LibraryReturnDto> update(@PathVariable Long id, @Valid @RequestBody LibraryUpdateDto libraryUpdateDto) {
        LibraryReturnDto libraryReturnDto = libraryService.update(id, libraryUpdateDto);
        return ResponseEntity.ok(libraryReturnDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        libraryService.delete(id);
    }
}
