package ar.com.codoacodo.links.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("unused")
@RestController
public class Controller {

    @GetMapping(value = "/saludame/{nombre}-{apellido}")
    public String resultados(
            @PathVariable(value = "nombre") String nombres,
            @PathVariable(value = "apellido") String apellidos
    ) {
        return String.format("¡Bienvenido!: %s %s.", nombres, apellidos);
    }

}
