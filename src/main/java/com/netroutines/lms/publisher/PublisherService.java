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

        return publishers.stream().map(publisherMapper::toDTO).toList();
    }

    public PublisherResponse create(PublisherRequest publisherRequest) {
        Publisher publisher = publisherMapper.toEntity(publisherRequest);
        publisherRepository.save(publisher);

        return publisherMapper.toDTO(publisher);
    }

    public PublisherResponse read(Long id) {
        return publisherMapper.toDTO(findById(id));
    }

    public PublisherResponse update(Long id, PublisherRequest publisherRequest) {
        Publisher current = findById(id);
        current.setName(publisherRequest.name());
        publisherRepository.save(current);

        return publisherMapper.toDTO(current);
    }

    public void delete(Long id) {
        findById(id);
        publisherRepository.deleteById(id);
    }

    private Publisher findById(Long id) {
        return publisherRepository.findById(id).orElseThrow(PublisherNotFoundException::new);
    }

}
