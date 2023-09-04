package criatura;

import poderes.Ataque;

public class Charizard extends Pokemon {

    public Charizard() {
        super("Charizard", new Ataque("lanzallamas", 5));
    }

    @Override
    public void aprender(Ataque ataque) {
        getAtaques().clear();
        super.aprender(ataque);
    }

}
