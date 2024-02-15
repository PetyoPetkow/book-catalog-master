package com.example.bookcatalog.domain.author.service;

import com.example.bookcatalog.domain.author.entity.Author;
import com.example.bookcatalog.domain.author.model.AuthorCreateDto;
import com.example.bookcatalog.domain.author.model.AuthorReturnDto;
import com.example.bookcatalog.domain.author.model.AuthorUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuthorService {
    AuthorReturnDto create(AuthorCreateDto authorCreateDto);

    AuthorReturnDto getById(Long id);

    Page<AuthorReturnDto> getAll(Pageable pageable);

    AuthorReturnDto update(Long id, AuthorUpdateDto authorUpdateDto);

    void delete(Long id);

    Author returnAuthorById(Long id);
}
