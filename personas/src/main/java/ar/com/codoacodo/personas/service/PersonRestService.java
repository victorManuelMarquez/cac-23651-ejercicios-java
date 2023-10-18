package ar.com.codoacodo.personas.service;

import ar.com.codoacodo.personas.entity.Person;

import java.util.List;

public interface PersonRestService {

    String addPerson(Person entity);

    List<Person> listPersons();

    Person findPersonById(String personalId);

    Person findPersonByName(String name);

    List<Person> filterPersonByAge(int since, int to);

}
