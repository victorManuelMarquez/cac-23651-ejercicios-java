package producto;

import lombok.Getter;
import lombok.Setter;


@Setter @Getter
public class Perecedero extends Producto {

    private Integer diasPorCaducar;

    public Perecedero(String nombre, Double precio, Integer diasPorCaducar) {
        super(nombre, precio);
        this.diasPorCaducar = diasPorCaducar;
    }

    @Override
    public String toString() {
        return String.format("%s; días para caducar: %d", super.toString(), diasPorCaducar);
    }

    @Override
    public void calcular(int cantidadDeProductos) {
        super.calcular(cantidadDeProductos);
        switch (diasPorCaducar) {
            case 1 -> setPrecio(getPrecio() / 4);
            case 2 -> setPrecio(getPrecio() / 3);
            case 3 -> setPrecio(getPrecio() / 2);
        }
    }

}
