package ar.com.codoacodo.personasv2.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Clase persona que va a simular a una entidad.
 */
@AllArgsConstructor
@Data
public class Persona {

    private int id;
    private String dni, nombre, apellido, domicilio;
    private List<String> emails, contactos;
    private LocalDate fechaNac;
    private boolean activo;

    /**
     * <b>Nueva función</b>: Comparación entre el dni de esta persona y el de la persona recibida.
     *
     * @param obj valor a comparar.
     * @return verdadero si y solo si el dni de ambas personas coincide.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj instanceof Persona persona) {
            return persona.getDni().equalsIgnoreCase(getDni());
        } else return false;
    }

    /**
     * <i>Sobrescritura requerida al redefinir el método</i> {@linkplain Object#equals(Object)}.
     *
     * @return un nuevo hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, dni, nombre, apellido, emails, contactos, fechaNac, activo);
    }

}
