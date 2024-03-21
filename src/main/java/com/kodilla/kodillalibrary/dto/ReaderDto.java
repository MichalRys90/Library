package com.kodilla.kodillalibrary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ReaderDto {

    private long id;
    private String name;
    private String lastName;
    private Date accountCreated;
    private String nickName;
}
