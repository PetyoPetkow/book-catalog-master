package com.example.bookcatalog.domain.library.service;

import com.example.bookcatalog.domain.book.entity.Book;
import com.example.bookcatalog.domain.book.service.BookService;
import com.example.bookcatalog.domain.library.entity.Library;
import com.example.bookcatalog.domain.library.mapper.LibraryMapper;
import com.example.bookcatalog.domain.library.model.LibraryCreateDto;
import com.example.bookcatalog.domain.library.model.LibraryReturnDto;
import com.example.bookcatalog.domain.library.model.LibraryUpdateDto;
import com.example.bookcatalog.domain.library.repo.LibraryRepository;
import com.example.bookcatalog.infrastructure.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LibraryServiceImpl implements LibraryService{
    private static final String LIBRARY_DOESNT_EXIST = "Library with id %d doesn't exist!";

    private final LibraryMapper libraryMapper;
    private final BookService bookService;
    private final LibraryRepository libraryRepository;

    public LibraryServiceImpl(LibraryMapper libraryMapper, BookService bookService, LibraryRepository libraryRepository) {
        this.libraryMapper = libraryMapper;
        this.bookService = bookService;
        this.libraryRepository = libraryRepository;
    }

    @Override
    public LibraryReturnDto create(LibraryCreateDto libraryCreateDto) {
        final var library = libraryMapper.createDtoToLibrary(libraryCreateDto);

        final var books = new ArrayList<Book>();
        for (Long bookId : libraryCreateDto.bookIds()) {
            final var book = bookService.returnBookById(bookId);
            books.add(book);
        }
        library.setBooks(books);
        return libraryMapper.libraryToReturnDto(libraryRepository.save(library));
    }

    @Override
    public LibraryReturnDto getById(Long id) {
        final var library = libraryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(LIBRARY_DOESNT_EXIST, id)));
        return libraryMapper.libraryToReturnDto(library);
    }

    @Override
    public Page<LibraryReturnDto> getAll(Pageable pageable) {
        return libraryRepository.findAll(pageable).map(libraryMapper::libraryToReturnDto);
    }

    @Override
    public LibraryReturnDto update(Long id, LibraryUpdateDto libraryUpdateDto) {
        final var library = libraryRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format(LIBRARY_DOESNT_EXIST, id)));
        libraryMapper.update(libraryUpdateDto, library);

        if (libraryUpdateDto.bookIds() != null && !libraryUpdateDto.bookIds().isEmpty()) {

            final var books = new ArrayList<Book>();
            for (Long bookId : libraryUpdateDto.bookIds()) {
                final var book = bookService.returnBookById(bookId);
                books.add(book);
            }

            library.setBooks(books);
        }

        return libraryMapper.libraryToReturnDto(libraryRepository.save(library));
    }

    @Override
    public void delete(Long id) {
        final var library = libraryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(LIBRARY_DOESNT_EXIST, id)));
        libraryRepository.delete(library);
    }

    @Override
    public Library returnLibraryById(Long id) {
        return libraryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(LIBRARY_DOESNT_EXIST, id)));
    }
}
