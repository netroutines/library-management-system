package com.netroutines.librarymanagementsystem.book;

import com.netroutines.librarymanagementsystem.author.Author;
import com.netroutines.librarymanagementsystem.author.AuthorRepository;
import com.netroutines.librarymanagementsystem.author.exceptions.AuthorNotFoundException;
import com.netroutines.librarymanagementsystem.book.exceptions.BookNotFoundException;
import com.netroutines.librarymanagementsystem.genre.Genre;
import com.netroutines.librarymanagementsystem.genre.GenreRepository;
import com.netroutines.librarymanagementsystem.genre.exceptions.GenreNotFoundException;
import com.netroutines.librarymanagementsystem.publisher.Publisher;
import com.netroutines.librarymanagementsystem.publisher.PublisherRepository;
import com.netroutines.librarymanagementsystem.publisher.exceptions.PublisherNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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

    public Page<BookDTO> list(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Book> books = bookRepository.findAll(pageable);

        return books.map(bookMapper::toDTO);
    }

    public BookDTO create(BookRequest bookRequest) {
        Genre genre = findGenreById(bookRequest.genreId());
        Publisher publisher = findPublisherById(bookRequest.publisherId());
        Set<Author> authors = findAuthorsById(bookRequest.authorIds());

        if (authors.size() != bookRequest.authorIds().size()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ONE_OR_MORE_AUTHORS_NOT_FOUND");
        }

        Book book = bookMapper.toEntity(bookRequest, publisher, genre, authors);
        bookRepository.save(book);

        return bookMapper.toDTO(book);
    }

    public BookDTO read(Long id) {
        return bookMapper.toDTO(findById(id));
    }

    @Transactional
    public BookDTO update(Long id, BookRequest bookRequest) {
        Book currentBook = findById(id);

        Publisher publisher = findPublisherById(bookRequest.publisherId());
        Genre genre = findGenreById(bookRequest.genreId());
        Set<Author> authors = findAuthorsById(bookRequest.authorIds());

        if (authors.size() != bookRequest.authorIds().size()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ONE_OR_MORE_AUTHORS_NOT_FOUND");
        }

        currentBook.setTitle(bookRequest.title());
        currentBook.setPublisher(publisher);
        currentBook.setGenre(genre);
        currentBook.setAuthors(authors);

        return bookMapper.toDTO(bookRepository.save(currentBook));
    }

    public void delete(Long id) {
        findById(id);
        bookRepository.deleteById(id);
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
}
