package consumible.animal;

import consumible.Comestible;

public abstract class Animal implements Carne {

    abstract public void comer(Comestible alimento);

    abstract public String emiteSonido();

}
