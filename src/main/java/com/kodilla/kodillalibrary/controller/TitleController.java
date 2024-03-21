package com.kodilla.kodillalibrary.controller;

import com.kodilla.kodillalibrary.domain.Title;
import com.kodilla.kodillalibrary.dto.TitleDto;
import com.kodilla.kodillalibrary.exception.TitleNotFoundException;
import com.kodilla.kodillalibrary.mapper.TitleMapper;
import com.kodilla.kodillalibrary.service.TitleDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/title")
@RequiredArgsConstructor
public class TitleController {

    @Autowired
    private TitleDbService dbService;
    @Autowired
    private TitleMapper titleMapper;

    @GetMapping
    public ResponseEntity<List<TitleDto>> getTitle() {
        List<Title> title = dbService.getAllTitles();
        return ResponseEntity.ok(titleMapper.mapToTitleDtoList(title));
    }

    @GetMapping(value = "{titleId}")
    public ResponseEntity<TitleDto> getTitle(@PathVariable Long titleId) throws TitleNotFoundException {
        return ResponseEntity.ok(titleMapper.mapToTitleDto(dbService.getTitleById(titleId)));
    }

    @DeleteMapping(value = "{titleId}")
    public ResponseEntity<Void> deleteTitle(@PathVariable Long titleId) {
        dbService.deleteTitle(titleId);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<TitleDto> updateTask(@RequestBody TitleDto titleDto) {
        Title title = titleMapper.mapToTitle(titleDto);
        Title savedTitle = dbService.saveTitle(title);
        return ResponseEntity.ok(titleMapper.mapToTitleDto(savedTitle));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TitleDto> createTitle(@RequestBody TitleDto titleDto) {
        Title title = titleMapper.mapToTitle(titleDto);
        dbService.saveTitle(title);
        return ResponseEntity.ok(titleMapper.mapToTitleDto(title));
    }
}
