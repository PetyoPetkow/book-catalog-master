package com.example.bookcatalog.domain.library.model;

import com.example.bookcatalog.domain.book.model.BookReturnDto;

import java.util.List;

public record LibraryReturnDto(
        Long id,

        String name,

        String location,

        List<BookReturnDto> books
) {
}
