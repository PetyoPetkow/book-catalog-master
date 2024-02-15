package com.example.bookcatalog.domain.library.service;

import com.example.bookcatalog.domain.library.entity.Library;
import com.example.bookcatalog.domain.library.model.LibraryCreateDto;
import com.example.bookcatalog.domain.library.model.LibraryReturnDto;
import com.example.bookcatalog.domain.library.model.LibraryUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LibraryService {
    LibraryReturnDto create(LibraryCreateDto libraryCreateDto);

    LibraryReturnDto getById(Long id);

    Page<LibraryReturnDto> getAll(Pageable pageable);

    LibraryReturnDto update(Long id, LibraryUpdateDto libraryUpdateDto);

    void delete(Long id);

    Library returnLibraryById(Long id);
}
