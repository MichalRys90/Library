//package com.kodilla.kodillalibrary.readerTests;
//
//import com.kodilla.kodillalibrary.domain.Reader;
//import com.kodilla.kodillalibrary.exception.ReaderNotFoundException;
//import com.kodilla.kodillalibrary.repository.ReaderRepository;
//import com.kodilla.kodillalibrary.service.ReaderDbService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//
//import javax.transaction.Transactional;
//import java.time.Instant;
//import java.util.Date;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest
//@ActiveProfiles("test")
//@Transactional
//public class H2ReaderTestSuite {
//
//    @Autowired
//    private ReaderRepository repository;
//    @Autowired
//    private ReaderDbService service;
//
//
//    @Test
//    void TestCreateReader() throws ReaderNotFoundException {
//        //Given
//        Reader reader = new Reader("Michał", "Ryś", Date.from(Instant.now()));
//
//        //When
//        repository.save(reader);
//        long id = reader.getId();
//        long result = service.getReaderById(id).getId();
//
//        //Then
//        assertEquals(id, result);
//    }
//}
