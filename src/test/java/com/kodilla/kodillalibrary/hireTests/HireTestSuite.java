package com.kodilla.kodillalibrary.hireTests;

import com.kodilla.kodillalibrary.domain.Book;
import com.kodilla.kodillalibrary.domain.Hire;
import com.kodilla.kodillalibrary.domain.Reader;
import com.kodilla.kodillalibrary.domain.Status;
import com.kodilla.kodillalibrary.exception.BookCantBeRented;
import com.kodilla.kodillalibrary.repository.BookRepository;
import com.kodilla.kodillalibrary.repository.HireRepository;
import com.kodilla.kodillalibrary.repository.ReaderRepository;
import com.kodilla.kodillalibrary.service.HireDbService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class HireTestSuite {

    @Autowired
    private HireRepository hireRepository;
    @Autowired
    private ReaderRepository readerRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private HireDbService dbService;

    @Test
    void TestRentBook() throws BookCantBeRented {
        //Given
        Book book = new Book(Status.AVAILABLE);
        Reader reader = new Reader("Adam", "Adamski", Date.from(Instant.now()));

        //When
        readerRepository.save(reader);
        bookRepository.save(book);
        long bookId = book.getId();
        long readerId = reader.getId();
        Hire hire = dbService.rentBook(bookId, readerId);
        long hireId = hire.getId();
        Status status = bookRepository.findById(bookId).get().getStatus();

        //Then
        assertEquals(Status.RENTED, status);

        //Clean up
        hireRepository.deleteById(hireId);
        readerRepository.deleteById(readerId);
        bookRepository.deleteById(bookId);
    }

    @Test
    void TestReturnBook() {
        //Given
        Book book = new Book(Status.RENTED);
        Hire hire = new Hire();
        hire.setBook(book);

        //When
        bookRepository.save(book);
        hireRepository.save(hire);
        long bookId = book.getId();
        long hireId = hire.getId();
        dbService.returnBook(hireId);
        Status status = bookRepository.findById(bookId).get().getStatus();

        //Then
        assertEquals(Status.AVAILABLE, status);

        //Clean up
        hireRepository.deleteById(hireId);
        bookRepository.deleteById(bookId);
    }
}
