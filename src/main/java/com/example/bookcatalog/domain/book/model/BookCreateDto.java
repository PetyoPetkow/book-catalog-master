package com.example.bookcatalog.domain.book.model;

import jakarta.validation.constraints.*;

import java.util.Set;

public record BookCreateDto(
        @Size(min = 2, max = 32, message = "Title should be between 2 and 32 chars long")
        @NotBlank(message = "Title is required")
        String title,

        @Size(min = 10, max = 10, message = "Isbn must be be 10 chars long")
        @NotBlank(message = "Isbn is required")
        String isbn,

        @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Invalid date format. Expected format: yyyy-MM-dd")
        @NotBlank(message = "Published date is mandatory")
        String publishedDate,

        @NotNull(message = "You must provide if book is available")
        boolean available,

        @NotNull(message = "You must provide an author to the book")
        Long authorId,

        @NotEmpty(message = "You must provide genres to the book")
        Set<Long> genreIds
) {
}
