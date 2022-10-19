package com.kodilla.kodillalibrary.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "HIRE")
public class Hire {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "HIRE_ID", unique = true)
    private long id;

    @ManyToOne
    @JoinColumn(name = "READER_ID")
    private Reader reader;

    @ManyToOne
    @JoinColumn(name = "BOOK_ID")
    private Book book;


    @Column(name = "RENTED")
    private Date rented;


    @Column(name = "RETURNED")
    private Date returned;
}
