package com.netroutines.librarymanagementsystem.genre;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    GenreDTO toDTO(Genre genre);
    Genre toEntity(GenreRequest request);
}
