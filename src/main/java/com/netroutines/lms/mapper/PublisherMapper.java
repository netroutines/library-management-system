package com.netroutines.lms.mapper;

import com.netroutines.lms.controller.request.PublisherRequest;
import com.netroutines.lms.controller.response.PublisherResponse;
import com.netroutines.lms.entity.Publisher;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PublisherMapper {

    PublisherResponse toResponse(Publisher publisher);
    Publisher toEntity(PublisherRequest request);

}
