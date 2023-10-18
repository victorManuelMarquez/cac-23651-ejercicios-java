package ar.com.codoacodo.personas.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.Period;

@AllArgsConstructor
@Data
public class Person {

    private Integer id;
    private String personalId, name, lastName;
    private LocalDate birthdate;

    public int getAge() {
        return Period.between(birthdate, LocalDate.now()).getYears();
    }

}
