package com.kodilla.kodillalibrary.mapper;

import com.kodilla.kodillalibrary.domain.Reader;
import com.kodilla.kodillalibrary.dto.ReaderDto;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;

@Service
public class ReaderMapper {

    public Reader mapToReader(final ReaderDto readerDto) {

        return new Reader(
                readerDto.getId(),
                readerDto.getName(),
                readerDto.getLastName(),
                new Date(),
                readerDto.getNickName()
        );
    }

    public ReaderDto mapToReaderDto(final Reader reader) {
        return new ReaderDto(
                reader.getId(),
                reader.getName(),
                reader.getLastName(),
                reader.getAccountCreated(),
                reader.getNickName()
        );
    }

    public List<ReaderDto> mapToReaderDtoList(final List<Reader> readerList) {
        return readerList.stream()
                .map(this::mapToReaderDto)
                .toList();
    }
}
