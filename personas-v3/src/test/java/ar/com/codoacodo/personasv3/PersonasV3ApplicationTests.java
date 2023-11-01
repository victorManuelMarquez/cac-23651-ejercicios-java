package ar.com.codoacodo.personasv3;

import ar.com.codoacodo.personasv3.bean.Persona;
import ar.com.codoacodo.personasv3.factory.PersonaFactory;
import ar.com.codoacodo.personasv3.service.PersonaService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PersonasV3ApplicationTests {

	@Mock
	PersonaService service;

	@Test
	void service_cuandoSeAgregaUnaPersona() {
		// creo una persona
		Persona persona = PersonaFactory.crearIndividuo();
		// si se recibe un objeto "persona" deberá retornar el mismo.
		Mockito.when(service.agregarPersona(Mockito.any(Persona.class))).thenReturn(persona);
	}

}
