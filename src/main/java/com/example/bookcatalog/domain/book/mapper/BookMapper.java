package com.example.bookcatalog.domain.book.mapper;

import com.example.bookcatalog.domain.book.entity.Book;
import com.example.bookcatalog.domain.book.model.BookCreateDto;
import com.example.bookcatalog.domain.book.model.BookReturnDto;
import com.example.bookcatalog.domain.book.model.BookUpdateDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface BookMapper {
    Book createDtoToBook(BookCreateDto bookCreateDto);

    BookReturnDto bookToReturnDto(Book book);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(BookUpdateDto bookUpdateDto, @MappingTarget Book book);
}
