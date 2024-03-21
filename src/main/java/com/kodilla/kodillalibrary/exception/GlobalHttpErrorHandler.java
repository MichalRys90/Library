package com.kodilla.kodillalibrary.exception;

import com.kodilla.kodillalibrary.exception.BookNotFoundException;
import com.kodilla.kodillalibrary.exception.HireNotFoundException;
import com.kodilla.kodillalibrary.exception.ReaderNotFoundException;
import com.kodilla.kodillalibrary.exception.TitleNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<Object> handleBookNotFoundException(BookNotFoundException exception) {
        return new ResponseEntity<>("Book with given id doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HireNotFoundException.class)
    public ResponseEntity<Object> handleHireNotFoundException(HireNotFoundException exception) {
        return new ResponseEntity<>("Hire with given id doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ReaderNotFoundException.class)
    public ResponseEntity<Object> handleTaskNotFoundException(ReaderNotFoundException exception) {
        return new ResponseEntity<>("Reader with given id doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TitleNotFoundException.class)
    public ResponseEntity<Object> handleTaskNotFoundException(TitleNotFoundException exception) {
        return new ResponseEntity<>("Title with given id doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserWithGivenNickNameExist.class)
    public ResponseEntity<Object> handleUserWithGivenNickNameExist(UserWithGivenNickNameExist exception) {
        return new ResponseEntity<>("Username is taken, please choose another one", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookCantBeRented.class)
    public ResponseEntity<Object> BookCantBeRented(BookCantBeRented exception) {
        return new ResponseEntity<>("This book cant be rented. Status is not AVAILABLE", HttpStatus.BAD_REQUEST);
    }
}
