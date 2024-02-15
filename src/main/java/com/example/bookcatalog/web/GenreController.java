package com.example.bookcatalog.web;

import com.example.bookcatalog.domain.genre.model.GenreCreateDto;
import com.example.bookcatalog.domain.genre.model.GenreReturnDto;
import com.example.bookcatalog.domain.genre.model.GenreUpdateDto;
import com.example.bookcatalog.domain.genre.service.GenreService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/genres")
public class GenreController {
    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @PostMapping
    public ResponseEntity<GenreReturnDto> create(@Valid @RequestBody GenreCreateDto genreCreateDto) {
        GenreReturnDto genreReturnDto = genreService.create(genreCreateDto);
        return ResponseEntity.ok(genreReturnDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreReturnDto> getById(@PathVariable Long id) {
        GenreReturnDto genreReturnDto = genreService.getById(id);
        return ResponseEntity.ok(genreReturnDto);
    }

    @GetMapping
    public ResponseEntity<Page<GenreReturnDto>> getAll(Pageable pageable) {
        return ResponseEntity.ok(genreService.getAll(pageable));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<GenreReturnDto> update(@PathVariable Long id, @Valid @RequestBody GenreUpdateDto genreUpdateDto) {
        GenreReturnDto genreReturnDto = genreService.update(id, genreUpdateDto);
        return ResponseEntity.ok(genreReturnDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        genreService.delete(id);
    }
}
