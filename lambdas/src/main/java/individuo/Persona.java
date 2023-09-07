package individuo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Setter @Getter
@AllArgsConstructor
public class Persona {

    private String dni, nombreCompleto;
    private Date fechaNac;

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fecha = LocalDate.ofInstant(Instant.ofEpochMilli(fechaNac.getTime()), ZoneId.systemDefault());
        return String.format("%s - %s - %s", dni, nombreCompleto, dtf.format(fecha));
    }

}
