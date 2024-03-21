package com.kodilla.kodillalibrary.bookTests;

import com.kodilla.kodillalibrary.domain.Book;
import com.kodilla.kodillalibrary.domain.Status;
import com.kodilla.kodillalibrary.domain.Title;
import com.kodilla.kodillalibrary.repository.BookRepository;
import com.kodilla.kodillalibrary.repository.TitleRepository;
import com.kodilla.kodillalibrary.service.BookDbService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BookTestSuite {

    @Autowired
    private BookDbService dbService;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private TitleRepository titleRepository;

    @Test
    void testBookSave() {
        //Given
        Book book = new Book();
        Title title = new Title("title", "author", LocalDate.now());
        book.setStatus(Status.AVAILABLE);
        book.setTitle(title);

        //When
        titleRepository.save(title);
        bookRepository.save(book);
        long id = book.getId();
        long titleId = title.getId();

        //Then
        assertNotEquals(0, id);

        //CleanUp
        bookRepository.deleteById(id);
        titleRepository.deleteById(titleId);
    }

    @Test
    void testHowManyBooks() {
        //Given
        Title title = new Title("Title", "author", LocalDate.now());
        Book book = new Book();
        Book book1 = new Book();
        book.setTitle(title);
        book.setStatus(Status.AVAILABLE);
        book1.setTitle(title);
        book1.setStatus(Status.AVAILABLE);

        //When
        titleRepository.save(title);
        bookRepository.save(book);
        bookRepository.save(book1);
        long id = title.getId();
        long id1 = book.getId();
        long id2 = book1.getId();
        long result = dbService.freeBooks("Title");

        //Then
        assertEquals(2, result);

        //Clen up
        bookRepository.deleteById(id2);
        bookRepository.deleteById(id1);
        titleRepository.deleteById(id);
    }

    @Test
    void testForChangeStatus() {
        //Given
        Title title = new Title("Title", "author", LocalDate.now());
        Book book = new Book();
        book.setTitle(title);
        book.setStatus(Status.AVAILABLE);

        //When
        titleRepository.save(title);
        bookRepository.save(book);
        long id = title.getId();
        long id2 = book.getId();
        dbService.updateBookStatus(id2, Status.LOST);
        Status result = bookRepository.findById(id2).get().getStatus();

        //Then
        assertEquals(Status.LOST, result);

        //Clen up
        bookRepository.deleteById(id2);
        titleRepository.deleteById(id);
    }
}
