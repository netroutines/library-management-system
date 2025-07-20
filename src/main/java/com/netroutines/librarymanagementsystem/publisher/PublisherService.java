package com.netroutines.librarymanagementsystem.publisher;

import com.netroutines.librarymanagementsystem.publisher.exceptions.PublisherNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublisherService {

    private final PublisherRepository publisherRepository;
    private final PublisherMapper publisherMapper;

    public List<PublisherDTO> list() {
        List<Publisher> publishers = publisherRepository.findAll();

        return publishers.stream().map(publisherMapper::toDTO).toList();
    }

    public PublisherDTO create(PublisherRequest publisherRequest) {
        Publisher publisher = publisherMapper.toEntity(publisherRequest);
        publisherRepository.save(publisher);

        return publisherMapper.toDTO(publisher);
    }

    public PublisherDTO read(Long id) {
        return publisherMapper.toDTO(findById(id));
    }

    public PublisherDTO update(Long id, PublisherRequest publisherRequest) {
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
