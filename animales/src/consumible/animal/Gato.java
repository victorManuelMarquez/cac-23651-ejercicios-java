package consumible.animal;

import consumible.Comestible;

public class Gato extends Animal {

    @Override
    public String toString() {
        return "consumible.animal.Gato";
    }

    @Override
    public void comer(Comestible alimento) {
        if (alimento instanceof Carne)
            System.out.printf("Este %s está consumiendo %s.%n", toString().toLowerCase(), alimento.detalles());
        else
            System.out.printf("Este consumible.animal no puede consumir %s.%n", alimento.detalles());
    }

    @Override
    public String emiteSonido() {
        return "miau";
    }

    @Override
    public String detalles() {
        return "carne de gato";
    }

}
