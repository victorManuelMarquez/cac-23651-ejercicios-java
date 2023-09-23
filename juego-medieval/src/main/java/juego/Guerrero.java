package juego;

import java.util.ArrayList;
import java.util.List;

public class Guerrero {

    private final List<Combate> equipo;

    public Guerrero() {
        equipo = new ArrayList<>();
    }

    @Override
    public String toString() {
        return String.format("Guerrero equipado con %d artefactos, su nivel de lucha es de %d.", cantidadDeArtefactos(), nivelDeLucha());
    }

    public void agregarEquipo(Combate elemento) {
        if (elemento instanceof Espada) {
            // no más de 2 espadas
            if (equipo.stream().filter(v -> v instanceof Espada).count() < 2)
                equipo.add(elemento);
        } else if (elemento instanceof Collar) {
            if (equipo.stream().noneMatch(v -> v instanceof Collar))
                equipo.add(elemento);
        } else if (elemento instanceof MascaraOscura) {
            if (equipo.stream().noneMatch(v -> v instanceof MascaraOscura))
                equipo.add(elemento);
        } else if (elemento instanceof Armadura) {
            if (equipo.stream().noneMatch(v -> v instanceof Armadura))
                equipo.add(elemento);
        }
    }

    public int cantidadDeArtefactos() {
        return equipo.size();
    }

    public int nivelDeLucha() {
        return equipo.stream().mapToInt(Combate::unidadDeLucha).sum();
    }

    public String[] equipamiento() {
        return equipo.stream().map(Object::toString).toArray(String[]::new);
    }

}
