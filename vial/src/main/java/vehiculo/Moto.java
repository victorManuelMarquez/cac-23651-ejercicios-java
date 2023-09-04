package vehiculo;

import lombok.Getter;
import lombok.Setter;
import persona.Conductor;

@Setter @Getter
public class Moto extends Vehiculo {

    private int totalDeEspejos;
    private Conductor conductor;

    public Moto(int velocidadMax, int totalDeEspejos, Conductor conductor) {
        super(velocidadMax);
        this.totalDeEspejos = totalDeEspejos;
        this.conductor = conductor;
    }

    @Override
    public String toString() {
        int velocidad = circular();
        return String.format("Motociclista %s y %s circulando a %d Km/h. (%s)",
                totalDeEspejos >= 2 ? "en regla" : "infractor",
                conductor.tieneRegistro()? "con registro" : "sin registro",
                velocidad,
                totalDeEspejos >= 2 && velocidad <= 160 ? "SEGURO" : "NO SEGURO"
        );
    }

}
