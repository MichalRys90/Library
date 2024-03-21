package com.kodilla.kodillalibrary.repository;

import com.kodilla.kodillalibrary.domain.Hire;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HireRepository extends CrudRepository<Hire, Long> {
    List<Hire> findAll();

    Optional<Hire> findById(Long id);

    Hire save(Hire hire);

    void deleteById(Long id);

    List<Hire> findAllByReaderId(long id);
}
