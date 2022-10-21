package com.kodilla.kodillalibrary.controller;

import com.kodilla.kodillalibrary.domain.Book;
import com.kodilla.kodillalibrary.dto.BookDto;
import com.kodilla.kodillalibrary.domain.Status;
import com.kodilla.kodillalibrary.exception.BookNotFoundException;
import com.kodilla.kodillalibrary.mapper.BookMapper;
import com.kodilla.kodillalibrary.service.BookDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    @Autowired
    private BookDbService dbService;
    @Autowired
    private BookMapper bookMapper;

    @GetMapping
    public ResponseEntity<List<BookDto>> getBook() {
        List<Book> book = dbService.getAllBooks();
        return ResponseEntity.ok(bookMapper.mapToBookDtoList(book));
    }

    @GetMapping(value = "{bookId}")
    public ResponseEntity<BookDto> getBook(@PathVariable Long bookId) throws BookNotFoundException {
        return ResponseEntity.ok(bookMapper.mapToBookDto(dbService.getBookById(bookId)));
    }

    @DeleteMapping(value = "{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long bookId) {
        dbService.deleteBook(bookId);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<BookDto> updateTask(@RequestBody BookDto bookDto) {
        Book book = bookMapper.mapToBook(bookDto);
        Book savedBook = dbService.saveBook(book);
        return ResponseEntity.ok(bookMapper.mapToBookDto(savedBook));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createBook(@RequestBody BookDto bookDto) {
        Book book = bookMapper.mapToBook(bookDto);
        dbService.saveBook(book);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/updateStatus")
    public void updateStatus(@RequestParam Long id, @RequestParam Status status) {
        dbService.updateBookStatus(id, status);
    }

    @GetMapping(value = "/howManyFree")
    public Long howManyBooks(@RequestParam String title) {
        return dbService.freeBooks(title);
    }

}
