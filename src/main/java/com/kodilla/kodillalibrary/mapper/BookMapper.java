package com.kodilla.kodillalibrary.mapper;

import com.kodilla.kodillalibrary.domain.Book;
import com.kodilla.kodillalibrary.domain.Status;
import com.kodilla.kodillalibrary.dto.BookDto;
import com.kodilla.kodillalibrary.repository.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookMapper {

    @Autowired
    private TitleRepository repository;

    public Book mapToBook(final BookDto bookDto) {
        return new Book(
                bookDto.getId(),
                repository.findById(bookDto.getTitleId()).get(),
                Status.AVAILABLE
        );
    }

    public BookDto mapToBookDto(final Book book) {
        return new BookDto(
                book.getId(),
                book.getTitle().getId(),
                book.getStatus()
        );
    }

    public List<BookDto> mapToBookDtoList(final List<Book> bookList) {
        return bookList.stream()
                .map(this::mapToBookDto)
                .toList();
    }
}
