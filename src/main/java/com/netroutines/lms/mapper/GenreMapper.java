package com.netroutines.lms.mapper;

import com.netroutines.lms.controller.request.GenreRequest;
import com.netroutines.lms.controller.response.GenreResponse;
import com.netroutines.lms.entity.Genre;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    GenreResponse toResponse(Genre genre);
    Genre toEntity(GenreRequest request);

}
