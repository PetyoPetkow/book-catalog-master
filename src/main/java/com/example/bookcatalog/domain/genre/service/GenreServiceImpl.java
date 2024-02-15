package com.example.bookcatalog.domain.genre.service;

import com.example.bookcatalog.domain.book.entity.Book;
import com.example.bookcatalog.domain.genre.entity.Genre;
import com.example.bookcatalog.domain.genre.mapper.GenreMapper;
import com.example.bookcatalog.domain.genre.model.GenreCreateDto;
import com.example.bookcatalog.domain.genre.model.GenreReturnDto;
import com.example.bookcatalog.domain.genre.model.GenreUpdateDto;
import com.example.bookcatalog.domain.genre.repo.GenreRepository;
import com.example.bookcatalog.infrastructure.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GenreServiceImpl implements GenreService{
    private static final String GENRE_DOESNT_EXIST = "Genre with id %d doesn't exist!";

    private final GenreMapper genreMapper;
    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreMapper genreMapper, GenreRepository genreRepository) {
        this.genreMapper = genreMapper;
        this.genreRepository = genreRepository;
    }

    @Override
    public GenreReturnDto create(GenreCreateDto genreCreateDto) {
        final var genre = genreMapper.createDtoToGenre(genreCreateDto);
        return genreMapper.genreToReturnDto(genreRepository.save(genre));
    }

    @Override
    public GenreReturnDto getById(Long id) {
        final var genre = genreRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(GENRE_DOESNT_EXIST, id)));
        return genreMapper.genreToReturnDto(genre);
    }

    @Override
    public Page<GenreReturnDto> getAll(Pageable pageable) {
        return genreRepository.findAll(pageable).map(genreMapper::genreToReturnDto);
    }

    @Override
    public GenreReturnDto update(Long id, GenreUpdateDto genreUpdateDto) {
        final var genre = genreRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format(GENRE_DOESNT_EXIST, id)));
        genreMapper.update(genreUpdateDto, genre);
        return genreMapper.genreToReturnDto(genreRepository.save(genre));
    }

    @Override
    public void delete(Long id) {
        final var genre = genreRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(GENRE_DOESNT_EXIST, id)));

        final var books = genre.getBooks();
        for (Book book : books) {
            book.getGenres().remove(genre);
        }
        genreRepository.delete(genre);
    }

    @Override
    public Genre returnGenreById(Long id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(GENRE_DOESNT_EXIST, id)));
    }
}
