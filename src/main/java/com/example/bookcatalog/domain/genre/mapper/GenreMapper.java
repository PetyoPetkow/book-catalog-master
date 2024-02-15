package com.example.bookcatalog.domain.genre.mapper;

import com.example.bookcatalog.domain.genre.entity.Genre;
import com.example.bookcatalog.domain.genre.model.GenreCreateDto;
import com.example.bookcatalog.domain.genre.model.GenreReturnDto;
import com.example.bookcatalog.domain.genre.model.GenreUpdateDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    GenreReturnDto genreToReturnDto(Genre genre);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(GenreUpdateDto genreUpdateDto, @MappingTarget Genre genre);

    Genre createDtoToGenre(GenreCreateDto genreCreateDto);
}
