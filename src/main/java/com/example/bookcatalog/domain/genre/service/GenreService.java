package com.example.bookcatalog.domain.genre.service;

import com.example.bookcatalog.domain.genre.entity.Genre;
import com.example.bookcatalog.domain.genre.model.GenreCreateDto;
import com.example.bookcatalog.domain.genre.model.GenreReturnDto;
import com.example.bookcatalog.domain.genre.model.GenreUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GenreService {
    GenreReturnDto create(GenreCreateDto genreCreateDto);

    GenreReturnDto getById(Long id);

    Page<GenreReturnDto> getAll(Pageable pageable);

    GenreReturnDto update(Long id, GenreUpdateDto genreUpdateDto);

    void delete(Long id);

    Genre returnGenreById(Long id);
}
