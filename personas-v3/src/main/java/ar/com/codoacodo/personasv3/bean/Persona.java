package ar.com.codoacodo.personasv3.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Objects;

@Component("persona")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Persona {

    private Long id;
    private String dni, nombre, apellido;
    private LocalDate fechaNac;

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (o instanceof Persona persona) {
            return getId().equals(persona.getId()) || getDni().equalsIgnoreCase(persona.getDni());
        } else return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dni, nombre, apellido, fechaNac);
    }

}
