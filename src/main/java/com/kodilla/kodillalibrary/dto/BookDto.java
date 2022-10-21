package com.kodilla.kodillalibrary.dto;

import com.kodilla.kodillalibrary.domain.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class BookDto {

    private long id;
    private long titleId;
    private Status status;
}
