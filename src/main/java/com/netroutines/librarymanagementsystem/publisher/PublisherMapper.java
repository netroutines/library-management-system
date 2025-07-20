package com.netroutines.librarymanagementsystem.publisher;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PublisherMapper {
    PublisherDTO toDTO(Publisher publisher);
    Publisher toEntity(PublisherRequest request);
}
