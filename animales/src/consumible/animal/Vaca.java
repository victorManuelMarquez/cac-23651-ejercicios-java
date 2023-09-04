package consumible.animal;

import consumible.Comestible;
import consumible.vegetal.Vegetal;

public class Vaca extends Animal {

    @Override
    public String toString() {
        return "consumible.animal.Vaca";
    }

    @Override
    public void comer(Comestible alimento) {
        if (alimento instanceof Vegetal)
            System.out.printf("Esta %s está consumiendo %s.%n", toString().toLowerCase(), alimento.detalles());
        else
            System.out.printf("Este consumible.animal no puede consumir %s.%n", alimento.detalles());
    }

    @Override
    public String emiteSonido() {
        return "muu";
    }

    @Override
    public String detalles() {
        return "carne de vaca";
    }

}
