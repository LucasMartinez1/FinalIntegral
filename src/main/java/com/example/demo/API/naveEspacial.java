package com.example.demo.API;

import com.example.demo.DTO.InstruccionesDTO;
import com.example.demo.model.Instrucciones;
import com.example.demo.model.Telemetrias;
import com.example.demo.model.Usuario;
import com.example.demo.services.InstruccionesServices;
import com.example.demo.services.TelemetriasServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api")
public class naveEspacial {

    @Autowired
    InstruccionesServices instruccionesServices;
    @Autowired
    TelemetriasServices telemetriasServices;
    @Autowired
    PasswordEncoder passwordEncoder;
    public boolean verificado = false;
    private String dato = "";
    private Telemetrias telemetrias;
    private Usuario user;

    @PostMapping(value = "/instructions")
    @ResponseBody
    public ResponseEntity<Map<String, String>> instructions(@RequestBody InstruccionesDTO instruccionesDTO) {
        try {
            if (instruccionesDTO == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            if (verificado) {
                Instrucciones instruccion = new Instrucciones(instruccionesDTO);
                instruccionesServices.crearInstruccion(instruccion);

                String inst = instruccionesDTO.getInstruccion().toLowerCase().trim();

                String dato;
                switch (inst) {
                    case "scan":
                        dato = "El terreno cuenta con una superficie de 200mt²";
                        break;
                    case "collect sample":
                        dato = "Información del suelo";
                        break;
                    case "deploy rover":
                        dato = "Rover desplegado con éxito";
                        break;
                    default:
                        dato = "No se pudo procesar la instrucción";
                        break;
                }

                Map<String, String> response = new HashMap<>();
                response.put("message", dato);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        } catch (DataAccessException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/telemetry")
    @ResponseBody
    public ResponseEntity<Map<String, String>> telemetrias() {
        try {
            if (verificado) {
                telemetrias = new Telemetrias(1, 30.3, 25.1, 40.0);
                telemetriasServices.crearTelemetria(telemetrias);

                dato = "Velocidad " + telemetrias.getVelocidad() + " km/h, Presion " + telemetrias.getPresion() + ", Temperatura " + telemetrias.getTemperatura() + "°";

                Map<String, String> response = new HashMap<>();
                response.put("message", dato);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/authenticate")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> authenticate(@RequestHeader("Authorization") String authorizationHeader) {
        try {
            if (authorizationHeader != null && authorizationHeader.startsWith("Basic ")) {
                String informacion = authorizationHeader.substring("Basic ".length());
                String credenciales = new String(Base64.getDecoder().decode(informacion));

                final String[] valores = credenciales.split(":", 2);
                final String usuario = valores[0];
                final String contraseña = valores[1];


                user = new Usuario("tierra", "tierra", "USER");


                if (user.getUsername().equals(usuario) && contraseña.equals(user.getContraseña())) {

                    verificado = true;
                    Map<String, Object> response = new HashMap<>();
                    response.put("status", "ok");
                    response.put("code", "200");
                    response.put("message", "Usuario verificado con exito");
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }

            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

