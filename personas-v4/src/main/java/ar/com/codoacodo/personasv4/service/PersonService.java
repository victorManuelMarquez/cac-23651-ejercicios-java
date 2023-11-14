package ar.com.codoacodo.personasv4.service;

import ar.com.codoacodo.personasv4.dto.request.SavePersonDto;
import ar.com.codoacodo.personasv4.dto.response.StoredPersonDto;
import ar.com.codoacodo.personasv4.exception.DuplicatedPersonException;
import ar.com.codoacodo.personasv4.exception.PersonNotFoundException;
import ar.com.codoacodo.personasv4.exception.SavingPersonException;
import ar.com.codoacodo.personasv4.mapper.PersonMapper;
import ar.com.codoacodo.personasv4.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PersonService {

    private PersonRepository repository;

    public StoredPersonDto savePerson(SavePersonDto dto) {
        if (duplicatedData(dto))
            throw new DuplicatedPersonException();
        if (repository.addOne(PersonMapper.toBean(dto)))
            return PersonMapper.toDto(PersonMapper.toBean(dto));
        else throw new SavingPersonException();
    }

    public StoredPersonDto findPersonById(Long id) {
        return PersonMapper.toDto(repository.findById(id).orElseThrow(PersonNotFoundException::new));
    }

    public List<StoredPersonDto> listAll() {
        return repository.listAll().stream().map(PersonMapper::toDto).toList();
    }

    public boolean duplicatedData(SavePersonDto dto) {
        return repository.listAll().stream().anyMatch(p -> p.getPersonalId().equals(dto.getPersonalId()));
    }

}
