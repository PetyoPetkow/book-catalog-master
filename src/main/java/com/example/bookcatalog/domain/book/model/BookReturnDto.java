package com.example.bookcatalog.domain.book.model;

import com.example.bookcatalog.domain.author.model.AuthorReturnDto;
import com.example.bookcatalog.domain.genre.model.GenreReturnDto;

import java.util.Set;

public record BookReturnDto(
        Long id,

        String title,

        String isbn,

        String publishedDate,

        boolean available,

        AuthorReturnDto author,

        Set<GenreReturnDto> genres
) {
}
