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

        return authors.stream().map(authorMapper::toResponse).toList();
    }

    public AuthorResponse create(AuthorRequest authorRequest) {
        var author = authorMapper.toEntity(authorRequest);
        authorRepository.save(author);

        return authorMapper.toResponse(author);
    }

    public AuthorResponse read(Long id) {
        return authorMapper.toResponse(findById(id));
    }

    public AuthorResponse update(Long id, AuthorRequest authorRequest) {
        var author = findById(id);
        author.setFirstName(authorRequest.firstName());
        author.setLastName(authorRequest.lastName());
        authorRepository.save(author);

        return authorMapper.toResponse(author);
    }

    public void delete(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new AuthorNotFoundException();
        }

        authorRepository.deleteById(id);
    }

    private Author findById(Long id) {
        return authorRepository.findById(id).orElseThrow(AuthorNotFoundException::new);
    }

}
