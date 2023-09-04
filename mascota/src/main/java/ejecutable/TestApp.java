package ejecutable;

import mascota.Mascota;

public class TestApp {

    public static void main(String[] args) {
        Mascota mascota = new Mascota();
        mascota.setTiempo(20);
        mascota.setAburrida(true);
        mascota.setAburrida(mascota.getTiempo() <= 80);
        System.out.println(mascota);
        if (mascota.puedeJugar()) {
            if (!mascota.isAburrida())
                mascota.setNivel(mascota.getNivel() + 2);
            mascota.jugar();
        } else {
            if (!mascota.isAburrida())
                mascota.setNivel(mascota.getNivel() + 1);
            mascota.comer();
        }
    }

}
