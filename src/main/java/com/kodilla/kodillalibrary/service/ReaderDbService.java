package com.kodilla.kodillalibrary.service;

import com.kodilla.kodillalibrary.exception.ReaderNotFoundException;
import com.kodilla.kodillalibrary.domain.Reader;
import com.kodilla.kodillalibrary.exception.UserWithGivenNickNameExist;
import com.kodilla.kodillalibrary.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReaderDbService {

    private final ReaderRepository readerRepository;

    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }

    public Reader getReaderById(final Long id) throws ReaderNotFoundException {
        return readerRepository.findById(id).orElseThrow(ReaderNotFoundException::new);
    }

    public Reader saveReader(final Reader reader) throws UserWithGivenNickNameExist {
        Reader reader1 = readerRepository.findByNickName(reader.getNickName());

        if (reader1 == null) {
            return readerRepository.save(reader);
        }
        throw new UserWithGivenNickNameExist();
    }

    public void deleteReader(final Long id) {
        readerRepository.deleteById(id);
    }

    public long getReaderId(String nickname) {
        Reader reader = readerRepository.findByNickName(nickname);
        return reader.getId();
    }
}
