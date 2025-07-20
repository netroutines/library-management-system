package com.netroutines.librarymanagementsystem.genre;

import com.netroutines.librarymanagementsystem.genre.exceptions.GenreNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    public List<GenreDTO> list() {
        List<Genre> genres = genreRepository.findAll();

        return genres.stream().map(genreMapper::toDTO).toList();
    }

    public GenreDTO create(GenreRequest genreRequest) {
        Genre genre = genreMapper.toEntity(genreRequest);
        genreRepository.save(genre);

        return genreMapper.toDTO(genre);
    }

    public GenreDTO read(Long id) {
        return genreMapper.toDTO(findById(id));
    }

    public GenreDTO update(Long id, GenreRequest genreRequest) {
        Genre current = findById(id);
        current.setName(genreRequest.name());
        genreRepository.save(current);

        return genreMapper.toDTO(current);
    }

    public void delete(Long id) {
        findById(id);
        genreRepository.deleteById(id);
    }

    private Genre findById(Long id) {
        return genreRepository.findById(id).orElseThrow(GenreNotFoundException::new);
    }
}
