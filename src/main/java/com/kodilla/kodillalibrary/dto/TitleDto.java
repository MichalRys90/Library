package com.kodilla.kodillalibrary.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class TitleDto {

    private long id;
    private String title;
    private String author;
    private LocalDate publicationYear;
}
