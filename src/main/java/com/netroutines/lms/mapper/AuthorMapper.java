package com.netroutines.lms.mapper;

import com.netroutines.lms.controller.request.AuthorRequest;
import com.netroutines.lms.controller.response.AuthorResponse;
import com.netroutines.lms.entity.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorResponse toResponse(Author author);
    Author toEntity(AuthorRequest request);

}
