package ar.com.codoacodo.personas.repository;

import ar.com.codoacodo.personas.entity.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PersonRepository {

    @SuppressWarnings("FieldMayBeFinal")
    private List<Person> database = new ArrayList<>();

    public List<Person> allPersons() {
        return database;
    }

    public Optional<Person> filterPersonById(String personalId) {
        return database.stream().filter(person -> person.getPersonalId().equals(personalId)).findFirst();
    }

    public Optional<Person> filterPersonByName(String name) {
        return database.stream().filter(person -> person.getName().equalsIgnoreCase(name)).findFirst();
    }

    public List<Person> listFilterByAge(int min, int max) {
        return database.stream().filter(person -> person.getAge() >= min && person.getAge() <= max).toList();
    }

    public int savePerson(Person entity) {
        entity.setId(database.isEmpty() ? 1 : database.size() + 1);
        database.add(entity);
        return database.size();
    }

}
