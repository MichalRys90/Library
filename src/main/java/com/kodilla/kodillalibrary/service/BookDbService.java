package com.kodilla.kodillalibrary.service;

import com.kodilla.kodillalibrary.exception.BookNotFoundException;
import com.kodilla.kodillalibrary.domain.Book;
import com.kodilla.kodillalibrary.domain.Status;
import com.kodilla.kodillalibrary.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookDbService {

    private final BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(final Long id) throws BookNotFoundException {
        return bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
    }

    public Book saveBook(final Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(final Long id) {
        bookRepository.deleteById(id);
    }

    public long freeBooks(String title) {
        return bookRepository.findAll().stream()
                .filter(t -> t.getTitle().getTitle().equals(title))
                .filter(s -> s.getStatus().equals(Status.AVAILABLE))
                .count();
    }

    public void updateBookStatus(Long id, Status status) {
        Optional<Book> books = bookRepository.findById(id);
        Book book = books.get();
        book.setStatus(status);
        bookRepository.save(book);
    }
}
