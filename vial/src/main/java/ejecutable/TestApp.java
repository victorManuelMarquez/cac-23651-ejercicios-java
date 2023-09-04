package ejecutable;

import persona.Conductor;
import vehiculo.Auto;
import vehiculo.Moto;
import vehiculo.Vehiculo;

import java.util.ArrayList;
import java.util.Random;

public class TestApp {

    public static void main(String[] args) {
        ArrayList<Vehiculo> carretera = new ArrayList<>();
        Random random = new Random();
        for (int i=0; i<random.nextInt(9, 30) + 1; i++) {
            if (random.nextBoolean())
                carretera.add(new Auto(random.nextInt(220, 321), new Conductor(), random.nextBoolean()));
            else
                carretera.add(new Moto(random.nextInt(100, 191), random.nextInt(5), new Conductor()));
        }
        System.out.printf("Informe : %d vehículos detectados...%n", carretera.size());
        for (Vehiculo vehiculo : carretera) {
            System.out.println(vehiculo);
        }
    }

}
