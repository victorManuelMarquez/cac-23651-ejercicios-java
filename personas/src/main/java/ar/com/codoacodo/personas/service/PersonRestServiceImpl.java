package ar.com.codoacodo.personas.service;

import ar.com.codoacodo.personas.entity.Person;
import ar.com.codoacodo.personas.exception.PersonNotFoundException;
import ar.com.codoacodo.personas.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PersonRestServiceImpl implements PersonRestService {

    private PersonRepository repository;

    @Override
    public String addPerson(Person entity) {
        return repository.savePerson(entity) != 0 ? "Person saved!" : "Person NOT saved!";
    }

    @Override
    public List<Person> listPersons() {
        return repository.allPersons();
    }

    @Override
    public Person findPersonById(String personalId) {
        Optional<Person> entity = repository.filterPersonById(personalId);
        if (entity.isEmpty())
            throw new PersonNotFoundException();
        else
            return entity.get();
    }

    @Override
    public Person findPersonByName(String name) {
        Optional<Person> entity = repository.filterPersonByName(name);
        if (entity.isPresent())
            return entity.get();
        else
            throw new PersonNotFoundException();
    }

    @Override
    public List<Person> filterPersonByAge(int since, int to) {
        return repository.listFilterByAge(since, to);
    }

}
