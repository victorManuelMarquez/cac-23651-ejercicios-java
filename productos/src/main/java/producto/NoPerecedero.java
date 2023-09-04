package producto;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class NoPerecedero extends Producto {

    private String tipo;

    public NoPerecedero(String nombre, Double precio, String tipo) {
        super(nombre, precio);
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return String.format("%s; tipo: %s", super.toString(), tipo);
    }

}
