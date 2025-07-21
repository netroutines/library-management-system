package com.netroutines.librarymanagementsystem.author;

import com.netroutines.librarymanagementsystem.author.exceptions.AuthorNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AuthorServiceTest {

    private AuthorRepository authorRepository;
    private AuthorMapper authorMapper;
    private AuthorService authorService;

    @BeforeEach
    void setUp() {
        authorRepository = mock(AuthorRepository.class);
        authorMapper = mock(AuthorMapper.class);
        authorService = new AuthorService(authorRepository, authorMapper);
    }

    @Test
    void testList() {
        List<Author> authors = List.of(new Author(1L, "James", "Gosling"));
        when(authorRepository.findAll()).thenReturn(authors);
        when(authorMapper.toDTO(any())).thenReturn(new AuthorDTO(1L, "James", "Gosling"));

        List<AuthorDTO> result = authorService.list();

        assertEquals(1, result.size());
        assertEquals("James", result.getFirst().firstName());
    }

    @Test
    void testCreate() {
        AuthorRequest authorRequest = new AuthorRequest("James", "Gosling");
        Author author = new Author(null, "James", "Gosling");
        Author savedAuthor = new Author(1L, "James", "Gosling");
        AuthorDTO authorDTO = new AuthorDTO(1L, "James", "Gosling");

        when(authorMapper.toEntity(authorRequest)).thenReturn(author);
        when(authorRepository.save(author)).thenReturn(savedAuthor);
        when(authorMapper.toDTO(author)).thenReturn(authorDTO);

        AuthorDTO result = authorService.create(authorRequest);

        assertEquals("James", result.firstName());
        verify(authorRepository).save(author);
    }

    @Test
    void testRead() {
        Author author = new Author(1L, "James", "Gosling");
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));
        when(authorMapper.toDTO(author)).thenReturn(new AuthorDTO(1L, "James", "Gosling"));

        AuthorDTO authorDTO = authorService.read(1L);

        assertEquals("Gosling", authorDTO.lastName());
    }

    @Test
    void testUpdate() {
        Long id = 1L;
        Author existing = new Author(id, "Sun", "Microsystems");
        AuthorRequest update = new AuthorRequest("Oracle", "Corporation");
        AuthorDTO authorDTO = new AuthorDTO(id, "Oracle", "Corporation");

        when(authorRepository.findById(id)).thenReturn(Optional.of(existing));
        when(authorRepository.save(existing)).thenReturn(existing);
        when(authorMapper.toDTO(existing)).thenReturn(authorDTO);

        AuthorDTO result = authorService.update(id, update);

        assertEquals("Oracle", result.firstName());
        assertEquals("Corporation", result.lastName());

        assertEquals("Oracle", existing.getFirstName());
        assertEquals("Corporation", existing.getLastName());
    }

    @Test
    void testDelete() {
        Long id = 1L;
        Author author = new Author(id, "James", "Gosling");

        when(authorRepository.findById(id)).thenReturn(Optional.of(author));
        doNothing().when(authorRepository).deleteById(id);

        assertDoesNotThrow(() -> authorService.delete(id));
        verify(authorRepository).deleteById(id);
    }

    @Test
    void findById_notFound() {
        when(authorRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(AuthorNotFoundException.class, () -> authorService.read(1L));
    }
}
