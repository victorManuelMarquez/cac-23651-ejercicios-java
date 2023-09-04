package mascota;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@NoArgsConstructor
public class Mascota implements Come, Juega {

    private int nivel, tiempo;
    private boolean aburrida, hambrienta;

    @Override
    public String toString() {
        return String.format("Tamagotchi: nivel::%d, aburrida::%b, hambrienta::%b, tiempo::%d", nivel, aburrida, hambrienta, tiempo);
    }

    @Override
    public void comer() {
        aburrida = false;
        hambrienta = false;
        tiempo += 10;
        System.out.println("Comer -> " + this);
    }

    @Override
    public void jugar() {
        aburrida = false;
        hambrienta = true;
        tiempo += 30;
        System.out.println("Jugar -> " + this);
    }

    public boolean puedeJugar() {
        return !hambrienta;
    }

}
