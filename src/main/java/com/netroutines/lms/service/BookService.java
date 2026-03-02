package com.netroutines.lms.service;

import com.netroutines.lms.controller.request.BookRequest;
import com.netroutines.lms.controller.response.BookResponse;
import com.netroutines.lms.entity.Author;
import com.netroutines.lms.entity.Book;
import com.netroutines.lms.entity.Genre;
import com.netroutines.lms.entity.Publisher;
import com.netroutines.lms.exception.AuthorNotFoundException;
import com.netroutines.lms.exception.BookNotFoundException;
import com.netroutines.lms.exception.GenreNotFoundException;
import com.netroutines.lms.exception.PublisherNotFoundException;
import com.netroutines.lms.mapper.BookMapper;
import com.netroutines.lms.repository.AuthorRepository;
import com.netroutines.lms.repository.BookRepository;
import com.netroutines.lms.repository.GenreRepository;
import com.netroutines.lms.repository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    private final BookMapper bookMapper;

    public Page<BookResponse> list(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Book> books = bookRepository.findAll(pageable);

        return books.map(bookMapper::toResponse);
    }

    public BookResponse create(BookRequest bookRequest) {
        var deps = resolveDependencies(bookRequest);

        Book book = bookMapper.toEntity(
                bookRequest,
                deps.publisher,
                deps.genre,
                deps.authors
        );

        Book saved = bookRepository.save(book);
        return bookMapper.toResponse(saved);
    }

    public BookResponse read(Long id) {
        return bookMapper.toResponse(findById(id));
    }

    @Transactional
    public BookResponse update(Long id, BookRequest bookRequest) {
        var book = findById(id);
        var deps = resolveDependencies(bookRequest);

        book.setTitle(bookRequest.title());
        book.setPublisher(deps.publisher);
        book.setGenre(deps.genre);
        book.setAuthors(deps.authors);

        return bookMapper.toResponse(book);
    }

    public void delete(Long id) {
        var book = findById(id);
        bookRepository.delete(book);
    }

    private Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
    }

    private Genre findGenreById(Long id) {
        return genreRepository.findById(id).orElseThrow(GenreNotFoundException::new);
    }

    private Publisher findPublisherById(Long id) {
        return publisherRepository.findById(id).orElseThrow(PublisherNotFoundException::new);
    }

    private Set<Author> findAuthorsById(Set<Long> ids) {
        List<Author> authors = authorRepository.findAllById(ids);

        if (authors.size() != ids.size()) {
            throw new AuthorNotFoundException();
        }

        return new HashSet<>(authors);
    }

    private record ResolvedBookDependencies(Publisher publisher, Genre genre, Set<Author> authors) {

    }

    private ResolvedBookDependencies resolveDependencies(BookRequest bookRequest) {
        var publisher = findPublisherById(bookRequest.publisherId());
        var genre = findGenreById(bookRequest.genreId());
        Set<Author> authors = findAuthorsById(bookRequest.authorIds());

        return new ResolvedBookDependencies(publisher, genre, authors);
    }

}
