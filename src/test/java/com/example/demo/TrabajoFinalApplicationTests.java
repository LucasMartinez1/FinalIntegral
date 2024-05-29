package com.example.demo;

import com.example.demo.DTO.InstruccionesDTO;
import com.example.demo.API.naveEspacial;
import com.example.demo.model.Instrucciones;
import com.example.demo.model.Telemetrias;
import com.example.demo.services.InstruccionesServices;
import com.example.demo.services.TelemetriasServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
class TrabajoFinalApplicationTests {


	@InjectMocks
	private naveEspacial NaveEspacial;

	@Mock
	InstruccionesServices instruccionesServices;
	@Mock
	TelemetriasServices telemetriasServices;
	@Mock
	Telemetrias telemetrias;


	//TEST INSTRUCCIONES

	@Test
	public void testEnviarInstruccionCorrecta1() {

		NaveEspacial.verificado = true;
		InstruccionesDTO instruccionesDTO = new InstruccionesDTO();

		instruccionesDTO.setInstruccion("scan");

		doNothing().when(instruccionesServices).crearInstruccion(any(Instrucciones.class));

		ResponseEntity<Map<String, String>> response = NaveEspacial.instructions(instruccionesDTO);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("El terreno cuenta con una superficie de 200mt²", response.getBody().get("message"));
	}

	@Test
	public void testEnviarInstruccionCorrecta2() {

		NaveEspacial.verificado = true;
		InstruccionesDTO instruccionesDTO = new InstruccionesDTO();

		instruccionesDTO.setInstruccion("collect sample");

		doNothing().when(instruccionesServices).crearInstruccion(any(Instrucciones.class));

		ResponseEntity<Map<String, String>> response = NaveEspacial.instructions(instruccionesDTO);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Información del suelo", response.getBody().get("message"));
	}

	@Test
	public void testEnviarInstruccionCorrecta3() {

		NaveEspacial.verificado = true;
		InstruccionesDTO instruccionesDTO = new InstruccionesDTO();

		instruccionesDTO.setInstruccion("deploy rover");

		doNothing().when(instruccionesServices).crearInstruccion(any(Instrucciones.class));

		ResponseEntity<Map<String, String>> response = NaveEspacial.instructions(instruccionesDTO);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Rover desplegado con éxito", response.getBody().get("message"));
	}

	@Test
	public void testEnviarInstruccionNoRegistrada() {

		NaveEspacial.verificado = true;
		InstruccionesDTO instruccionesDTO = new InstruccionesDTO();

		instruccionesDTO.setInstruccion("instruccion no registrada en la nave");

		doNothing().when(instruccionesServices).crearInstruccion(any(Instrucciones.class));

		ResponseEntity<Map<String, String>> response = NaveEspacial.instructions(instruccionesDTO);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("No se pudo procesar la instrucción", response.getBody().get("message"));
	}


	@Test
	public void testEnviarInstruccionError() {
		NaveEspacial.verificado = true;
		InstruccionesDTO instruccionesDTO = null;

		ResponseEntity<Map<String, String>> response = NaveEspacial.instructions(instruccionesDTO);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	@Test
	public void testEnviarInstruccionNoAutorizada() {
		NaveEspacial.verificado = false;
		InstruccionesDTO instruccionesDTO = new InstruccionesDTO();
		instruccionesDTO.setInstruccion("scan");

		ResponseEntity<Map<String, String>> response = NaveEspacial.instructions(instruccionesDTO);

		assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
	}


	//TEST TELEMETRIAS

	@Test
	public void testTelemetriaOk(){
		NaveEspacial.verificado = true;
		telemetrias = new Telemetrias(30.3, 40.0, 25.1);
		doNothing().when(telemetriasServices).crearTelemetria(any(Telemetrias.class));

		ResponseEntity<Map<String, String>> response = NaveEspacial.telemetrias();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		Map<String, String> expectedResponse = new HashMap<>();
		expectedResponse.put("message", "Velocidad 30.3 km/h, Presion 40.0, Temperatura 25.1°");
		assertEquals(expectedResponse, response.getBody());
	}

	@Test
	public void testTelemetriaNoAutorizada(){
		NaveEspacial.verificado = false;
		ResponseEntity<Map<String, String>> response = NaveEspacial.telemetrias();

		assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
	}



	//TEST AUTENTICAR


	@Test
	public void testAutenticarOk() {
		String authorizationHeader = "Basic dGllcnJhOnRpZXJyYQ==";

		ResponseEntity<Map<String, Object>> response = NaveEspacial.authenticate(authorizationHeader);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Usuario verificado con exito", response.getBody().get("message"));
	}

	@Test
	public void testAutenticarError(){
		String authorizationHeader = "Basic dGllcnJhOnRpZXJyYTEyMw==";

		ResponseEntity<Map<String, Object>> response = NaveEspacial.authenticate(authorizationHeader);

		assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
	}

}
