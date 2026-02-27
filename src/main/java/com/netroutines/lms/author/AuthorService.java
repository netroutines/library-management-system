package com.netroutines.lms.author;

import com.netroutines.lms.author.exception.AuthorNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public List<AuthorResponse> list() {
        List<Author> authors = authorRepository.findAll();

        return authors.stream().map(authorMapper::toDTO).toList();
    }

    public AuthorResponse create(AuthorRequest authorRequest) {
        Author author = authorMapper.toEntity(authorRequest);
        authorRepository.save(author);

        return authorMapper.toDTO(author);
    }

    public AuthorResponse read(Long id) {
        return authorMapper.toDTO(findById(id));
    }

    public AuthorResponse update(Long id, AuthorRequest authorRequest) {
        Author current = findById(id);
        current.setFirstName(authorRequest.firstName());
        current.setLastName(authorRequest.lastName());
        authorRepository.save(current);

        return authorMapper.toDTO(current);
    }

    public void delete(Long id) {
        findById(id);
        authorRepository.deleteById(id);
    }

    private Author findById(Long id) {
        return authorRepository.findById(id).orElseThrow(AuthorNotFoundException::new);
    }

}
