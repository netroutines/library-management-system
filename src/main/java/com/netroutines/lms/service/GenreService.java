package com.netroutines.lms.service;

import com.netroutines.lms.controller.request.GenreRequest;
import com.netroutines.lms.controller.response.GenreResponse;
import com.netroutines.lms.entity.Genre;
import com.netroutines.lms.exception.GenreNotFoundException;
import com.netroutines.lms.mapper.GenreMapper;
import com.netroutines.lms.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    public List<GenreResponse> list() {
        List<Genre> genres = genreRepository.findAll();

        return genres.stream().map(genreMapper::toResponse).toList();
    }

    public GenreResponse create(GenreRequest genreRequest) {
        var genre = genreMapper.toEntity(genreRequest);
        genreRepository.save(genre);

        return genreMapper.toResponse(genre);
    }

    public GenreResponse read(Long id) {
        return genreMapper.toResponse(findById(id));
    }

    public GenreResponse update(Long id, GenreRequest genreRequest) {
        var genre = findById(id);
        genre.setName(genreRequest.name());
        genreRepository.save(genre);

        return genreMapper.toResponse(genre);
    }

    public void delete(Long id) {
        var genre = findById(id);
        genreRepository.delete(genre);
    }

    private Genre findById(Long id) {
        return genreRepository.findById(id).orElseThrow(GenreNotFoundException::new);
    }

}
