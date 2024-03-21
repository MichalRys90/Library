package com.kodilla.kodillalibrary.repository;

import com.kodilla.kodillalibrary.domain.Reader;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReaderRepository extends CrudRepository<Reader, Long> {
    List<Reader> findAll();

    Optional<Reader> findById(Long id);

    Reader save(Reader reader);

    void deleteById(Long id);
    Reader findByNickName(String nickName);
}
