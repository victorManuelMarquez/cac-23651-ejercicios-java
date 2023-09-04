package vehiculo;

import lombok.Getter;
import lombok.Setter;
import persona.Conductor;

@Setter @Getter
public class Auto extends Vehiculo {

    private Conductor conductor;
    private boolean auxilio;

    public Auto(int velocidadMax, Conductor conductor, boolean auxilio) {
        super(velocidadMax);
        this.conductor = conductor;
        this.auxilio = auxilio;
    }

    @Override
    public String toString() {
        int velocidad = circular();
        return String.format("Conductor %s y %s circulando a %d Km/h. (%s)",
                isAuxilio() ? "en regla" : "infractor",
                conductor.tieneRegistro()? "con registro" : "sin registro",
                velocidad,
                isAuxilio() && velocidad <= 140 && conductor.tieneRegistro() ? "SEGURO" : "NO SEGURO"
        );
    }

}
