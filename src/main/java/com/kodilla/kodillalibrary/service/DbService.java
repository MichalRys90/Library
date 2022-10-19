package com.kodilla.kodillalibrary.service;

import com.kodilla.kodillalibrary.controller.BookNotFoundException;
import com.kodilla.kodillalibrary.controller.HireNotFoundException;
import com.kodilla.kodillalibrary.controller.ReaderNotFoundException;
import com.kodilla.kodillalibrary.controller.TitleNotFoundException;
import com.kodilla.kodillalibrary.domain.Book;
import com.kodilla.kodillalibrary.domain.Hire;
import com.kodilla.kodillalibrary.domain.Reader;
import com.kodilla.kodillalibrary.domain.Title;
import com.kodilla.kodillalibrary.repository.BookRepository;
import com.kodilla.kodillalibrary.repository.HireRepository;
import com.kodilla.kodillalibrary.repository.ReaderRepository;
import com.kodilla.kodillalibrary.repository.TitleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DbService {

    private final BookRepository bookRepository;
    private final HireRepository hireRepository;
    private final ReaderRepository readerRepository;
    private final TitleRepository titleRepository;

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

    public List<Hire> getAllHires() {
        return hireRepository.findAll();
    }

    public Hire getHireById(final Long id) throws HireNotFoundException {
        return hireRepository.findById(id).orElseThrow(HireNotFoundException::new);
    }

    public Hire saveHire(final Hire hire) {
        return hireRepository.save(hire);
    }

    public void deleteHire(final Long id) {
        bookRepository.deleteById(id);
    }

    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }

    public Reader getReaderById(final Long id) throws ReaderNotFoundException {
        return readerRepository.findById(id).orElseThrow(ReaderNotFoundException::new);
    }

    public Reader saveReader(final Reader reader) {
        return readerRepository.save(reader);
    }

    public void deleteReader(final Long id) {
        readerRepository.deleteById(id);
    }

    public List<Title> getAllTitles() {
        return titleRepository.findAll();
    }

    public Title getTitleById(final Long id) throws TitleNotFoundException {
        return titleRepository.findById(id).orElseThrow(TitleNotFoundException::new);
    }

    public Title saveTitle(final Title title) {
        return titleRepository.save(title);
    }

    public void deleteTitle(final Long id) {
        titleRepository.deleteById(id);
    }

}
