package com.example.bookcatalog.domain.book.service;

import com.example.bookcatalog.domain.author.service.AuthorService;
import com.example.bookcatalog.domain.book.entity.Book;
import com.example.bookcatalog.domain.book.mapper.BookMapper;
import com.example.bookcatalog.domain.book.model.BookCreateDto;
import com.example.bookcatalog.domain.book.model.BookReturnDto;
import com.example.bookcatalog.domain.book.model.BookUpdateDto;
import com.example.bookcatalog.domain.book.repo.BookRepository;
import com.example.bookcatalog.domain.genre.entity.Genre;
import com.example.bookcatalog.domain.genre.service.GenreService;
import com.example.bookcatalog.domain.library.entity.Library;
import com.example.bookcatalog.infrastructure.exception.NotFoundException;
import com.example.bookcatalog.infrastructure.exception.SearchParamParseException;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BookServiceImpl implements BookService {

    private static final String BOOK_DOESNT_EXIST = "Book with id %d doesn't exist!";
    private static final String PUBLISHED_DATE_CANT_BE_PARSED = "You need to provide a correct date in format yyyy-MM-dd";
    private final BookMapper bookMapper;
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final GenreService genreService;

    public BookServiceImpl(BookMapper bookMapper, BookRepository bookRepository, AuthorService authorService, GenreService genreService) {
        this.bookMapper = bookMapper;
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.genreService = genreService;
    }


    @Override
    public BookReturnDto create(BookCreateDto bookCreateDto) {
        final var book = bookMapper.createDtoToBook(bookCreateDto);
        final var author = authorService.returnAuthorById(bookCreateDto.authorId());

        final var genres = new HashSet<Genre>();
        for (Long genreId : bookCreateDto.genreIds()) {
            final var genre = genreService.returnGenreById(genreId);
            genres.add(genre);
        }

        book.setAuthor(author);
        book.setGenres(genres);
        return bookMapper.bookToReturnDto(bookRepository.save(book));
    }

    @Override
    public BookReturnDto getById(Long id) {
        final var book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(BOOK_DOESNT_EXIST, id)));
        return bookMapper.bookToReturnDto(book);
    }

    @Override
    public Page<BookReturnDto> getAll(Pageable pageable) {
        return bookRepository.findAll(pageable).map(bookMapper::bookToReturnDto);
    }

    @Override
    public BookReturnDto update(Long id, BookUpdateDto bookUpdateDto) {
        final var book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(BOOK_DOESNT_EXIST, id)));
        bookMapper.update(bookUpdateDto, book);

        if (bookUpdateDto.authorId() != null){
            book.setAuthor(authorService.returnAuthorById(bookUpdateDto.authorId()));
        }
        if (bookUpdateDto.genreIds() != null && !bookUpdateDto.genreIds().isEmpty()){

            final var genres = new HashSet<Genre>();
            for (Long genreId : bookUpdateDto.genreIds()) {
                final var genre = genreService.returnGenreById(genreId);
                genres.add(genre);
            }

            book.setGenres(genres);
        }
        return bookMapper.bookToReturnDto(bookRepository.save(book));
    }

    @Override
    public void delete(Long id) {
        final var book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(BOOK_DOESNT_EXIST, id)));

        for (Library library : book.getLibraries()) {
            library.getBooks().remove(book);
        }
        bookRepository.delete(book);
    }

    @Override
    public Book returnBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(BOOK_DOESNT_EXIST, id)));
    }

    @Override
    public List<BookReturnDto> search(String title, String authorName, String genreName) {
        Set<Book> books = new HashSet<>();
        List<Book> bookList = bookRepository.findAll();

        if (title != null) {
            books.addAll(bookList.stream().filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase())).toList());
        }

        if (authorName != null) {
            books.addAll(bookList.stream().filter(book -> book.getAuthor().getFirstName().toLowerCase().contains(authorName.toLowerCase())).toList());
        }

        if (genreName != null) {
            books.addAll(bookList.stream().filter(book ->
                                               book.getGenres().stream().anyMatch(genre -> genre.getName().toLowerCase().contains(genreName.toLowerCase()))).toList());
        }


        return books.stream()
                .map(bookMapper::bookToReturnDto)
                .collect(Collectors.toList());
    }

    private LocalDate parseToPublishedDate(String publishDate) {
        try {
            return LocalDate.parse(publishDate);
        } catch (DateTimeParseException e) {
            throw new SearchParamParseException(PUBLISHED_DATE_CANT_BE_PARSED);
        }
    }
}
