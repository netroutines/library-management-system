package com.netroutines.lms.mapper;

import com.netroutines.lms.controller.request.BookRequest;
import com.netroutines.lms.controller.response.BookResponse;
import com.netroutines.lms.entity.Author;
import com.netroutines.lms.entity.Book;
import com.netroutines.lms.entity.Genre;
import com.netroutines.lms.entity.Publisher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.Set;

@Mapper(componentModel = "spring", uses = {AuthorMapper.class, GenreMapper.class, PublisherMapper.class})
public interface BookMapper {

    BookResponse toResponse(Book book);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "genre", source = "genre"),
            @Mapping(target = "publisher", source = "publisher"),
            @Mapping(target = "authors", source = "authors"),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true)
    })
    Book toEntity(BookRequest request, Publisher publisher, Genre genre, Set<Author> authors);

}
