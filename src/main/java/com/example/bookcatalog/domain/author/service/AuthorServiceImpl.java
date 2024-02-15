package com.example.bookcatalog.domain.author.service;

import com.example.bookcatalog.domain.author.entity.Author;
import com.example.bookcatalog.domain.author.mapper.AuthorMapper;
import com.example.bookcatalog.domain.author.model.AuthorCreateDto;
import com.example.bookcatalog.domain.author.model.AuthorReturnDto;
import com.example.bookcatalog.domain.author.model.AuthorUpdateDto;
import com.example.bookcatalog.domain.author.repo.AuthorRepository;
import com.example.bookcatalog.infrastructure.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService{
    private static final String AUTHOR_DOESNT_EXIST = "Author with id %d doesn't exist!";

    private final AuthorMapper authorMapper;
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorMapper authorMapper, AuthorRepository authorRepository) {
        this.authorMapper = authorMapper;
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorReturnDto create(AuthorCreateDto authorCreateDto) {
        final var author = authorMapper.createDtoToAuthor(authorCreateDto);
        return authorMapper.authorToReturnDto(authorRepository.save(author));
    }

    @Override
    public AuthorReturnDto getById(Long id) {
        final var author = authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(AUTHOR_DOESNT_EXIST, id)));
        return authorMapper.authorToReturnDto(author);
    }

    @Override
    public Page<AuthorReturnDto> getAll(Pageable pageable) {
        return authorRepository.findAll(pageable).map(authorMapper::authorToReturnDto);
    }

    @Override
    public AuthorReturnDto update(Long id, AuthorUpdateDto authorUpdateDto) {
        final var author = authorRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format(AUTHOR_DOESNT_EXIST, id)));
        authorMapper.update(authorUpdateDto, author);
        return authorMapper.authorToReturnDto(authorRepository.save(author));
    }

    @Override
    public void delete(Long id) {
        final var author = authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(AUTHOR_DOESNT_EXIST, id)));
        authorRepository.delete(author);
    }

    @Override
    public Author returnAuthorById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(AUTHOR_DOESNT_EXIST, id)));
    }
}
