package ar.com.codoacodo.personasv4.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Person {

    private Long id;
    private String personalId;
    private String name;
    private String lastName;
    private LocalDate birthDate;

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o instanceof Person person) {
            return personalId.equals(person.getPersonalId()) && id.equals(person.getId());
        } else return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, personalId, name, lastName, birthDate);
    }

}
