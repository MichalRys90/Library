package com.kodilla.kodillalibrary.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "BOOK")
public class Book {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "BOOK_ID", unique = true)
    private long id;

    @ManyToOne
    @JoinColumn(name = "TITLE_ID")
    private Title title;

    @NotNull
    @Column(name = "STATUS")
    private Status status;

    @OneToMany(targetEntity = Hire.class,
            mappedBy = "reader",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Hire> hire = new ArrayList<>();

    public Book(long id, Title title, Status status) {
        this.id = id;
        this.title = title;
        this.status = status;
    }

    public Book(Status status) {
        this.status = status;
    }

    public Book(Title title, Status status) {
        this.title = title;
        this.status = status;
    }

    public Book(Title title) {
        this.title = title;
    }
}
