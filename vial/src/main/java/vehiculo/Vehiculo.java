package vehiculo;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Setter @Getter
public class Vehiculo {

    private int velocidadMax;

    public Vehiculo(int velocidadMax) {
        if (velocidadMax < 100)
            throw new IllegalArgumentException("Velocidad base muy baja.");
        this.velocidadMax = velocidadMax;
    }

    public int circular() {
        return new Random().nextInt(99, velocidadMax) + 1;
    }

}
