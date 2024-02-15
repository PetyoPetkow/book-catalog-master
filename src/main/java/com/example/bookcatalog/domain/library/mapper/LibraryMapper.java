package com.example.bookcatalog.domain.library.mapper;

import com.example.bookcatalog.domain.library.entity.Library;
import com.example.bookcatalog.domain.library.model.LibraryCreateDto;
import com.example.bookcatalog.domain.library.model.LibraryReturnDto;
import com.example.bookcatalog.domain.library.model.LibraryUpdateDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface LibraryMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(LibraryUpdateDto libraryUpdateDto, @MappingTarget Library library);

    LibraryReturnDto libraryToReturnDto(Library library);

    Library createDtoToLibrary(LibraryCreateDto libraryCreateDto);
}
