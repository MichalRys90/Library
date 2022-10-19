package com.kodilla.kodillalibrary.domain;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class TitleDto {

    private long id;
    private String title;
    private String author;
    private Date publicationYear;
}
