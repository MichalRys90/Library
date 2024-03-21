package com.kodilla.kodillalibrary.controller;

import com.kodilla.kodillalibrary.domain.Reader;
import com.kodilla.kodillalibrary.dto.ReaderDto;
import com.kodilla.kodillalibrary.exception.ReaderNotFoundException;
import com.kodilla.kodillalibrary.exception.UserWithGivenNickNameExist;
import com.kodilla.kodillalibrary.mapper.ReaderMapper;
import com.kodilla.kodillalibrary.service.ReaderDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reader")
@RequiredArgsConstructor
public class ReaderController {

    @Autowired
    private ReaderDbService dbService;
    @Autowired
    private ReaderMapper readerMapper;

    @GetMapping
    public ResponseEntity<List<ReaderDto>> getReader() {
        List<Reader> reader = dbService.getAllReaders();
        return ResponseEntity.ok(readerMapper.mapToReaderDtoList(reader));
    }

    @GetMapping(value = "{readerId}")
    public ResponseEntity<ReaderDto> getReader(@PathVariable Long readerId) throws ReaderNotFoundException {
        return ResponseEntity.ok(readerMapper.mapToReaderDto(dbService.getReaderById(readerId)));
    }

    @DeleteMapping(value = "{readerId}")
    public ResponseEntity<Void> deleteReader(@PathVariable Long readerId) {
        dbService.deleteReader(readerId);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<ReaderDto> updateTask(@RequestBody ReaderDto readerDto) throws UserWithGivenNickNameExist {
        Reader reader = readerMapper.mapToReader(readerDto);
        Reader savedReader = dbService.saveReader(reader);
        return ResponseEntity.ok(readerMapper.mapToReaderDto(savedReader));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createReader(@RequestBody ReaderDto readerDto) throws UserWithGivenNickNameExist {
        Reader reader = readerMapper.mapToReader(readerDto);
        dbService.saveReader(reader);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/readId")
    public ResponseEntity<Long> getReaderId(@RequestParam String nickname) {
        return ResponseEntity.ok(dbService.getReaderId(nickname));
    }
}
