package com.netroutines.librarymanagementsystem.author;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorDTO toDTO(Author author);
    Author toEntity(AuthorRequest request);
}
