package ar.com.codoacodo.holamundo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping(value = "mensaje")
    public String mensaje() {
        return "¡¡¡Hola mundo!!!";
    }

}