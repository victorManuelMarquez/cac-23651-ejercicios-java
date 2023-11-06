package ar.com.codoacodo.personasv3.service;

import ar.com.codoacodo.personasv3.bean.Persona;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PersonaService {

    private List<Persona> personas;

    public Persona agregarPersona(Persona p) {
        personas.add(p);
        return p;
    }

    public Persona buscarPorId(Integer id) {
        return personas.get(id);
    }

    public List<Persona> listarPersonas() {
        return personas;
    }

}
