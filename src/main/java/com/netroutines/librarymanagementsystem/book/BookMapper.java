package com.netroutines.librarymanagementsystem.book;

import com.netroutines.librarymanagementsystem.author.Author;
import com.netroutines.librarymanagementsystem.author.AuthorMapper;
import com.netroutines.librarymanagementsystem.genre.Genre;
import com.netroutines.librarymanagementsystem.genre.GenreMapper;
import com.netroutines.librarymanagementsystem.publisher.Publisher;
import com.netroutines.librarymanagementsystem.publisher.PublisherMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.Set;

@Mapper(componentModel = "spring", uses = {AuthorMapper.class, GenreMapper.class, PublisherMapper.class})
public interface BookMapper {
    BookDTO toDTO(Book book);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "genre", source = "genre"),
            @Mapping(target = "publisher", source = "publisher"),
            @Mapping(target = "authors", source = "authors"),
            @Mapping(target = "creationDate", ignore = true),
            @Mapping(target = "updatedDate", ignore = true)
    })
    Book toEntity(BookRequest request, Publisher publisher, Genre genre, Set<Author> authors);
}
