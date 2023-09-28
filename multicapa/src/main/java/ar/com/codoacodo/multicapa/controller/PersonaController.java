package ar.com.codoacodo.multicapa.controller;

import ar.com.codoacodo.multicapa.entity.EntidadPersona;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SuppressWarnings("unused")
public class PersonaController {

    @PostMapping(value = "/publicar")
    public String publicar(@RequestBody EntidadPersona entidadPersona) {
        return entidadPersona.toString();
    }

}
