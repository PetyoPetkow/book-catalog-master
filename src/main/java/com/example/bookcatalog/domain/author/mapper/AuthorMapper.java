package com.example.bookcatalog.domain.author.mapper;

import com.example.bookcatalog.domain.author.entity.Author;
import com.example.bookcatalog.domain.author.model.AuthorCreateDto;
import com.example.bookcatalog.domain.author.model.AuthorReturnDto;
import com.example.bookcatalog.domain.author.model.AuthorUpdateDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(AuthorUpdateDto authorUpdateDto, @MappingTarget Author author);

    AuthorReturnDto authorToReturnDto(Author author);

    Author createDtoToAuthor(AuthorCreateDto authorCreateDto);
}
