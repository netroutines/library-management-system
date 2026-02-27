package com.netroutines.lms.publisher;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PublisherMapper {

    PublisherResponse toDTO(Publisher publisher);
    Publisher toEntity(PublisherRequest request);

}
