package ar.com.codoacodo.personasv2;

import ar.com.codoacodo.personasv2.bean.Persona;
import ar.com.codoacodo.personasv2.service.PersonaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonasV2ApplicationTests {

	@Autowired
	PersonaService service;

	@Test
	void contextLoads() {
		int expected = new Random().nextInt(20) + 1;
		for (int i=0; i<expected; i++)
			service.agregarPersona(generarPersona());
		System.out.println("Personas registradas:");
		service.listarPersonas(p -> String.format(
				"%s %s, nacido el %s.",
				p.getNombre(),
				p.getApellido(),
				p.getFechaNac().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
		).forEach(System.out::println);
		Function<Persona, String> calcularEdades;
		calcularEdades = p -> String.format(
				"%s %s tiene %d años de edad.",
				p.getNombre(),
				p.getApellido(),
				Period.between(p.getFechaNac(), LocalDate.now()
		).getYears());
		List<String> edades = service.listarPersonas(calcularEdades);
		System.out.println("\nEdades de cada una:");
		edades.forEach(System.out::println);
		assertEquals(expected, edades.size());
	}

	private Persona generarPersona() {
		Random random = new Random();
		String[] nombres = {"Ana", "Juan", "Sofía", "Lucas", "Mario", "Lucía", "Irma", "Matías"};
		String[] apellidos = {"Pérez", "Sosa", "Morales", "Torres", "Farfán", "Sánchez", "Nuñez", "Reyes"};
		StringBuilder dni = new StringBuilder();
		for (int i=0; i<3; i++)
			dni.append(random.nextInt(1000));
		String domicilio = "Calle falsa " + random.nextInt(100, 5000);
		long inicio = LocalDate.of(1980, 1, 1).toEpochDay();
		long fin = LocalDate.of(2005, 1, 1).toEpochDay();
		return new Persona(
				random.nextInt(-100, 101),
				dni.toString(),
				nombres[random.nextInt(nombres.length)],
				apellidos[random.nextInt(apellidos.length)],
				domicilio,
				generarEmails(random),
				generarContactos(random),
				LocalDate.ofEpochDay(random.nextLong(inicio, fin)),
				random.nextBoolean()
		);
	}

	private List<String> generarEmails(Random random) {
		List<String> emails = new ArrayList<>();
		if (random.nextBoolean()) {
			for (int i=0; i<random.nextInt(1, 3); i++) {
				emails.add(
						"usuario" + random.nextInt(1, 100) + "@example.com"
				);
			}
		}
		return emails;
	}

	private List<String> generarContactos(Random random) {
		List<String> contactos = new ArrayList<>();
		if (random.nextBoolean()) {
			for (int i=0; i<random.nextInt(1, 3); i++) {
				contactos.add("+54 " + random.nextInt(1000000, 10000000));
			}
		}
		return contactos;
	}

}
