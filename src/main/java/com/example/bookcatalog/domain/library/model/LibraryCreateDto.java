package com.example.bookcatalog.domain.library.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public record LibraryCreateDto(
        @NotBlank(message = "Name cannot be blank")
        @Size(min = 3, max = 32, message = "Name should be between 3 and 32 chars long")
        String name,

        @NotBlank(message = "Location cannot be blank")
        @Size(min = 8, max = 64, message = "Location should be between 8 and 64 chars long")
        String location,

        @NotEmpty(message = "You must provide books to the library")
        List<Long> bookIds
) {
}
