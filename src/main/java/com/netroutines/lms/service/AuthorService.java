package com.netroutines.lms.service;

import com.netroutines.lms.controller.request.AuthorRequest;
import com.netroutines.lms.controller.response.AuthorResponse;
import com.netroutines.lms.entity.Author;
import com.netroutines.lms.exception.AuthorNotFoundException;
import com.netroutines.lms.mapper.AuthorMapper;
import com.netroutines.lms.repository.AuthorRepository;
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
        var author = findById(id);
        authorRepository.delete(author);
    }

    private Author findById(Long id) {
        return authorRepository.findById(id).orElseThrow(AuthorNotFoundException::new);
    }

}
