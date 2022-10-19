package com.kodilla.kodillalibrary.mapper;

import com.kodilla.kodillalibrary.domain.Hire;
import com.kodilla.kodillalibrary.domain.HireDto;
import com.kodilla.kodillalibrary.repository.BookRepository;
import com.kodilla.kodillalibrary.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HireMapper {

    @Autowired
    private ReaderRepository readerRepository;
    @Autowired
    private BookRepository bookRepository;

    public Hire mapToHire(final HireDto hireDto) {
        return new Hire(
                hireDto.getId(),
                readerRepository.findById(hireDto.getId()).get(),
                bookRepository.findById(hireDto.getId()).get(),
                hireDto.getRented(),
                hireDto.getReturned()
        );
    }

    public HireDto mapToHireDto(final Hire hire) {
        return new HireDto(
                hire.getId(),
                hire.getReader().getId(),
                hire.getBook().getId(),
                hire.getRented(),
                hire.getReturned()
        );
    }

    public List<HireDto> mapToHireDtoList(final List<Hire> hireList) {
        return hireList.stream()
                .map(this::mapToHireDto)
                .toList();
    }
}
