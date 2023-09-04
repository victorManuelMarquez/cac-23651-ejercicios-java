package ejecutable;

import producto.NoPerecedero;
import producto.Perecedero;
import producto.Producto;

import java.util.ArrayList;
import java.util.List;

public class Distribuidora {

    public static void main(String[] args) {
        double total = .0;
        ArrayList<Producto> productos = new ArrayList<>(List.of(
                new Perecedero("Salchichas", 299.99, 23),
                new NoPerecedero("Vasos", 1080.0, "Utensilio de cocina"),
                new Perecedero("Leche descremada", 439.82, 3),
                new Perecedero("Yogurt", 490.0, 1),
                new Perecedero("Barra de chocolate con maní", 999.99, 3)
        ));
        for (Producto producto : productos) {
            System.out.println(producto);
            total += producto.getPrecio();
        }
        System.out.println("El precio total es de: $" + total);
    }

}
