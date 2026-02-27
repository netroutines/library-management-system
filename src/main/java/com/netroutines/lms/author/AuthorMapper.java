package com.netroutines.lms.author;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorResponse toDTO(Author author);
    Author toEntity(AuthorRequest request);

}
