package com.kodilla.kodillalibrary.service;

import com.kodilla.kodillalibrary.domain.Book;
import com.kodilla.kodillalibrary.domain.Reader;
import com.kodilla.kodillalibrary.domain.Status;
import com.kodilla.kodillalibrary.exception.BookCantBeRented;
import com.kodilla.kodillalibrary.exception.HireNotFoundException;
import com.kodilla.kodillalibrary.domain.Hire;
import com.kodilla.kodillalibrary.repository.BookRepository;
import com.kodilla.kodillalibrary.repository.HireRepository;
import com.kodilla.kodillalibrary.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HireDbService {

    private final HireRepository hireRepository;
    private final BookRepository bookRepository;
    private final ReaderRepository repository;

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
        hireRepository.deleteById(id);
    }

    public Hire rentBook(Long bookId, Long readerId) throws BookCantBeRented {
        Book book = bookRepository.findById(bookId).get();
        if (book.getStatus().equals(Status.AVAILABLE)) {
            book.setStatus(Status.RENTED);
            bookRepository.save(book);
            Reader reader = repository.findById(readerId).get();
            Hire hire = new Hire();
            hire.setRented(LocalDate.now());
//            hire.setReturned(LocalDate.now().plusMonths(1));
            hire.setBook(book);
            hire.setReader(reader);
            return hireRepository.save(hire);
        }
        throw new BookCantBeRented();
    }

    public Hire returnBook(Long hireId) {
        Hire hire = hireRepository.findById(hireId).get();
        hire.getBook().setStatus(Status.AVAILABLE);
        bookRepository.save(hire.getBook());
        hire.setReturned(LocalDate.now());
        return hireRepository.save(hire);
    }

    public List<Hire> findHiresForUser (long readerId) {
        return hireRepository.findAllByReaderId(readerId);
    }
}
