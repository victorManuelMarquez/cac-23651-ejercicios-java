package producto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
@AllArgsConstructor
public class Producto {

    private String nombre;
    private Double precio;

    @Override
    public String toString() {
        return String.format("%s -> cuesta: $%.2f", nombre, precio);
    }

    public void calcular(int cantidadDeProductos) {
        precio *= cantidadDeProductos;
    }

}
