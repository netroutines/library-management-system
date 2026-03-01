package com.netroutines.lms.publisher;

import com.netroutines.lms.publisher.exception.PublisherNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublisherService {

    private final PublisherRepository publisherRepository;
    private final PublisherMapper publisherMapper;

    public List<PublisherResponse> list() {
        List<Publisher> publishers = publisherRepository.findAll();

        return publishers.stream().map(publisherMapper::toResponse).toList();
    }

    public PublisherResponse create(PublisherRequest publisherRequest) {
        var publisher = publisherMapper.toEntity(publisherRequest);
        publisherRepository.save(publisher);

        return publisherMapper.toResponse(publisher);
    }

    public PublisherResponse read(Long id) {
        return publisherMapper.toResponse(findById(id));
    }

    public PublisherResponse update(Long id, PublisherRequest publisherRequest) {
        var publisher = findById(id);
        publisher.setName(publisherRequest.name());
        publisherRepository.save(publisher);

        return publisherMapper.toResponse(publisher);
    }

    public void delete(Long id) {
        var publisher = findById(id);
        publisherRepository.delete(publisher);
    }

    private Publisher findById(Long id) {
        return publisherRepository.findById(id).orElseThrow(PublisherNotFoundException::new);
    }

}
