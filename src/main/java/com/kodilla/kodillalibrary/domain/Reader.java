package com.kodilla.kodillalibrary.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Table(name = "READER")
public class Reader {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "READER_ID", unique = true)
    private long id;

    @NotNull
    @Column(name = "NAME")
    private String name;

    @NotNull
    @Column(name = "LASTNAME")
    private String lastName;

    @NotNull
    @Column(name = "CREATED")
    private Date accountCreated;

    @NotNull
    @Column(name = "NICKNAME")
    private String nickName;

    @OneToMany(targetEntity = Hire.class,
            mappedBy = "reader",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Hire> hire = new ArrayList<>();

    public Reader(long id, String name, String lastName, Date accountCreated) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.accountCreated = accountCreated;
    }

    public Reader(String name, String lastName, Date accountCreated) {
        this.name = name;
        this.lastName = lastName;
        this.accountCreated = accountCreated;
    }

    public Reader(long id, String name, String lastName, Date accountCreated, String nickName) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.accountCreated = accountCreated;
        this.nickName = nickName;
    }

    public Reader(String name, String lastName, String nickName) {
        this.name = name;
        this.lastName = lastName;
        this.nickName = nickName;
    }
}
