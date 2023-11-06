package ar.com.codoacodo.personasv3;

import ar.com.codoacodo.personasv3.controller.PersonaController;
import ar.com.codoacodo.personasv3.dto.PersonaDto;
import ar.com.codoacodo.personasv3.factory.PersonaFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class PersonasV3ApplicationTests {

	@Autowired
	PersonaController controller;

	@Test
	void cuandoSeAgregaUnaPersona() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		PersonaDto dto = mapper.convertValue(PersonaFactory.crearIndividuo(), PersonaDto.class);
		ResponseEntity<PersonaDto> respuesta = controller.agregar(dto);
        Assertions.assertSame(respuesta.getStatusCode(), HttpStatus.OK);
	}

}
