package ar.com.codoacodo.personas.bundle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PersonDto {

    private Integer id;
    private String personalId, name, lastName;
    private LocalDate birthdate;

}
