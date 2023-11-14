package ar.com.codoacodo.personasv4.repository;

import ar.com.codoacodo.personasv4.bean.Person;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class PersonRepository {

    private List<Person> database;

    public boolean addOne(Person value) {
        value.setId(database.isEmpty() ? 1L : database.size() + 1L);
        return database.add(value);
    }

    public Optional<Person> findById(Long id) {
        return database.stream().filter(v -> Objects.equals(v.getId(), id)).findFirst();
    }

    public List<Person> listAll() {
        return database;
    }

}
