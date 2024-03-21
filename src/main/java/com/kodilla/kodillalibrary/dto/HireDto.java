package com.kodilla.kodillalibrary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class HireDto {

    private long id;
    private long readerId;
    private long bookId;
    private LocalDate rented;
    private LocalDate returned;
}
