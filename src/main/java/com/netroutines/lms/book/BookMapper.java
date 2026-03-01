package com.netroutines.lms.book;

import com.netroutines.lms.author.Author;
import com.netroutines.lms.author.AuthorMapper;
import com.netroutines.lms.genre.Genre;
import com.netroutines.lms.genre.GenreMapper;
import com.netroutines.lms.publisher.Publisher;
import com.netroutines.lms.publisher.PublisherMapper;
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
