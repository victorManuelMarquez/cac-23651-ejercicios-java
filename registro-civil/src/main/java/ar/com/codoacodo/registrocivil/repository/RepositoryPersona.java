package ar.com.codoacodo.registrocivil.repository;

import ar.com.codoacodo.registrocivil.entity.PersonaEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Repository
public class RepositoryPersona {

    @SuppressWarnings("FieldMayBeFinal")
    private List<PersonaEntity> database = new ArrayList<>();

    public PersonaEntity agregar(PersonaEntity entity) {
        if (!database.contains(entity)) {
            entity.setId(!database.isEmpty() ? database.size() - 1 : 0);
            entity.setHijos(new ArrayList<>());
            entity.setPadres(new ArrayList<>());
            database.add(entity);
            return entity;
        } return null;
    }

    public List<PersonaEntity> listar() {
        return database.stream().filter(PersonaEntity::getActivo).toList();
    }

    public Optional<PersonaEntity> buscarPorDni(String dni) {
        return database.stream().filter(p -> p.getDni().equals(dni)).findFirst();
    }

    public PersonaEntity actualizar(String dni, PersonaEntity nuevosDatos) {
        Optional<PersonaEntity> entity = buscarPorDni(dni);
        if (entity.isPresent()) {
            PersonaEntity encontrado = entity.get();
            encontrado.setDni(nuevosDatos.getDni());
            encontrado.setNombres(nuevosDatos.getNombres());
            encontrado.setApellidos(nuevosDatos.getApellidos());
            encontrado.setDomicilio(nuevosDatos.getDomicilio());
            encontrado.setFecha_nac(nuevosDatos.getFecha_nac());
            encontrado.setActivo(nuevosDatos.getActivo());
            return encontrado;
        } else return null;
    }

    public PersonaEntity borrar(String dni) {
        Optional<PersonaEntity> entity = buscarPorDni(dni);
        if (entity.isPresent()) {
            database.get(database.indexOf(entity.get())).setActivo(false);
            return entity.get();
        } else return null;
    }

    public void agregarPadre(String dni, String dniPadre) {
        Optional<PersonaEntity> hijo = buscarPorDni(dni);
        Optional<PersonaEntity> padre = buscarPorDni(dniPadre).stream().findAny();
        padre.ifPresent(p -> hijo.ifPresent(h -> h.getPadres().add(p)));
        hijo.ifPresent(h -> padre.ifPresent(p -> p.getHijos().add(h)));
    }

    public List<PersonaEntity> padres(String dni) {
        Optional<PersonaEntity> entity = buscarPorDni(dni);
        return entity.map(PersonaEntity::getPadres).orElse(null);
    }

    public void agregarHijo(String dni, String dniHijo) {
        Optional<PersonaEntity> padre = buscarPorDni(dni);
        Optional<PersonaEntity> hijo = buscarPorDni(dniHijo).stream().findAny();
        hijo.ifPresent(h -> padre.ifPresent(p -> p.getHijos().add(h)));
        padre.ifPresent(p -> hijo.ifPresent(h -> h.getPadres().add(p)));
    }

    public List<PersonaEntity> hijos(String dni) {
        Optional<PersonaEntity> entity = buscarPorDni(dni);
        return entity.map(PersonaEntity::getHijos).orElse(null);
    }

    public void casar(String dni1, String dni2) {
        Optional<PersonaEntity> pareja1 = buscarPorDni(dni1);
        Optional<PersonaEntity> pareja2 = buscarPorDni(dni2);
        if (pareja1.isPresent() && pareja2.isPresent()) {
            if (pareja1.get().getPareja() == null && pareja2.get().getPareja() == null) {
                pareja1.get().setPareja(pareja2.get());
                pareja2.get().setPareja(pareja1.get());
            }
        }
    }

    public int crearPersonas(Integer cantidad) {
        String[] nombres = {
                "José", "Ana", "María", "Lucia", "Marcos", "Juan", "Fabián", "Irma"
        };
        String[] apellidos = {
                "Serrano", "Nuñez", "Ocampo", "Jurado", "Martínez"
        };
        Random random = new Random();
        for (int i=0; i<cantidad; i++) {
            PersonaEntity nueva = new PersonaEntity();
            nueva.setDni(((Integer) random.nextInt(33000000, 46999999)).toString());
            nueva.setNombres(nombres[random.nextInt(nombres.length)]);
            nueva.setApellidos(apellidos[random.nextInt(apellidos.length)]);
            nueva.setDomicilio("Calle falsa " + random.nextInt(100, 9999));
            long inicio = LocalDate.of(1980, 1, 1).toEpochDay();
            long fin = LocalDate.of(2005, 1, 1).toEpochDay();
            nueva.setFecha_nac(LocalDate.ofEpochDay(random.nextLong(inicio, fin)));
            nueva.setActivo(true);
            nueva.setHijos(new ArrayList<>());
            nueva.setPadres(new ArrayList<>());
            agregar(nueva);
        }
        return listar().size();
    }

}
