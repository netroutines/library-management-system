package com.netroutines.lms.genre;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    GenreResponse toResponse(Genre genre);
    Genre toEntity(GenreRequest request);

}
