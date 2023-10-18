package ar.com.codoacodo.personas.bundle;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PersonErrorDto {

    private String message, clazz;

}
