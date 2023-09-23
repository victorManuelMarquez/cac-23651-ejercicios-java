package juego;

public class PlayGame {

    public static void main(String[] args) {
        Guerrero guerrero = new Guerrero();
        guerrero.agregarEquipo(new Espada(10));
        guerrero.agregarEquipo(new Espada(12));
        guerrero.agregarEquipo(new Espada(10));
        guerrero.agregarEquipo(new Armadura(3));
        guerrero.agregarEquipo(new Collar(3));
        guerrero.agregarEquipo(new Collar(5));
        guerrero.agregarEquipo(new MascaraOscura(5));
        System.out.println(guerrero);
        for (String equipo : guerrero.equipamiento()) {
            System.out.println(equipo);
        }
    }

}
