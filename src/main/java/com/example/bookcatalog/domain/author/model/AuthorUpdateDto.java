package com.example.bookcatalog.domain.author.model;

import jakarta.validation.constraints.Size;

public record AuthorUpdateDto(
        @Size(min = 3, max = 16, message = "First name should be between 3 and 16 chars long")
        String firstName,

        @Size(min = 3, max = 16, message = "Last name should be between 3 and 16 chars long")
        String lastName
) {
}
