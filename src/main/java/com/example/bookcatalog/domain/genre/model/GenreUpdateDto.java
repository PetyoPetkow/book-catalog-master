package com.example.bookcatalog.domain.genre.model;

import jakarta.validation.constraints.Size;

public record GenreUpdateDto(
        @Size(min = 2, max = 32, message = "Name must be between 2 and 32 chars long")
        String name,

        @Size(min = 32, max = 256, message = "Description must be between 32 and 256 chars long")
        String description
) {
}
