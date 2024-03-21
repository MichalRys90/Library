package com.kodilla.kodillalibrary.controller;

import com.kodilla.kodillalibrary.domain.Hire;
import com.kodilla.kodillalibrary.dto.HireDto;
import com.kodilla.kodillalibrary.exception.BookCantBeRented;
import com.kodilla.kodillalibrary.exception.HireNotFoundException;
import com.kodilla.kodillalibrary.mapper.HireMapper;
import com.kodilla.kodillalibrary.service.HireDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hire")
@RequiredArgsConstructor
public class HireController {

    @Autowired
    private HireDbService dbService;
    @Autowired
    private HireMapper hireMapper;

    @GetMapping
    public ResponseEntity<List<HireDto>> getHire() {
        List<Hire> hire = dbService.getAllHires();
        return ResponseEntity.ok(hireMapper.mapToHireDtoList(hire));
    }

    @GetMapping(value = "{hireId}")
    public ResponseEntity<HireDto> getHire(@PathVariable Long hireId) throws HireNotFoundException {
        return ResponseEntity.ok(hireMapper.mapToHireDto(dbService.getHireById(hireId)));
    }

    @DeleteMapping(value = "{hireId}")
    public ResponseEntity<Void> deleteHire(@PathVariable Long hireId) {
        dbService.deleteHire(hireId);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<HireDto> updateHire(@RequestBody HireDto hireDto) {
        Hire hire = hireMapper.mapToHire(hireDto);
        Hire savedHire = dbService.saveHire(hire);
        return ResponseEntity.ok(hireMapper.mapToHireDto(savedHire));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createHire(@RequestBody HireDto hireDto) {
        Hire hire = hireMapper.mapToHire(hireDto);
        dbService.saveHire(hire);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/rent")
    public void rentBook(@RequestParam Long bookId, @RequestParam Long readerId) throws BookCantBeRented {
        dbService.rentBook(bookId, readerId);
    }

    @PutMapping(value = "/return")
    public void returnBook(@RequestParam Long hireId) {
        dbService.returnBook(hireId);
    }

    @GetMapping(value = "/user")
    public ResponseEntity<List<HireDto>> getHireForUser(@RequestParam Long readerId) {
        List<Hire> hire = dbService.findHiresForUser(readerId);
        return ResponseEntity.ok(hireMapper.mapToHireDtoList(hire));
    }
}
