package com.example.bookcatalog.domain.book.model;

import jakarta.validation.constraints.*;

import java.util.Set;

public record BookUpdateDto(
        @Size(min = 2, max = 32, message = "Title should be between 2 and 32 chars long")
        String title,

        @Size(min = 10, max = 10, message = "Isbn must be be 10 chars long")
        String isbn,

        @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Invalid date format. Expected format: yyyy-MM-dd")
        String publishedDate,

        boolean available,

        Long authorId,

        Set<Long> genreIds
) {
}
