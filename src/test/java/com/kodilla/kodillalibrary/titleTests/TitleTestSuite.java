package com.kodilla.kodillalibrary.titleTests;

import com.kodilla.kodillalibrary.domain.Title;
import com.kodilla.kodillalibrary.exception.TitleNotFoundException;
import com.kodilla.kodillalibrary.repository.TitleRepository;
import com.kodilla.kodillalibrary.service.TitleDbService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TitleTestSuite {

    @Autowired
    private TitleRepository repository;
    @Autowired
    TitleDbService service;

    @Test
    void TestCreateTitle() throws TitleNotFoundException {
        //Given
        Title title = new Title("Ogniem i mieczem", "Henryk Sienkiewicz", LocalDate.of(1883, 10,10));

        //When
        repository.save(title);
        long id = title.getId();
        String result = service.getTitleById(id).getTitle();

        //Then
        assertEquals("Ogniem i mieczem", result);

        //Clen up
        repository.deleteById(id);
    }
}
