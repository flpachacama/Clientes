package com.banquito.cores.clientes.controller;

import com.banquito.cores.clientes.model.Persona;
import com.banquito.cores.clientes.service.PersonaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {

    private static final Logger logger = LoggerFactory.getLogger(PersonaController.class);

    private final PersonaService personaService;

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @PostMapping
    public ResponseEntity<Persona> crearPersona(@RequestBody Persona persona) {
        logger.info("Creando persona con identificación: {} - {}", persona.getTipoIdentificacion(), persona.getNumeroIdentificacion());
        Persona creada = personaService.crear(persona);
        logger.info("Persona creada con ID: {}", creada.getId());
        return ResponseEntity.ok(creada);
    }

    @GetMapping("/{tipoIdentificacion}/{numeroIdentificacion}")
    public ResponseEntity<Persona> obtenerPersona(
            @PathVariable String tipoIdentificacion,
            @PathVariable String numeroIdentificacion) {
        logger.info("Buscando persona con identificación: {} - {}", tipoIdentificacion, numeroIdentificacion);
        Persona persona = personaService.buscarPorIdentificacion(tipoIdentificacion, numeroIdentificacion);
        logger.debug("Persona encontrada: {}", persona);
        return ResponseEntity.ok(persona);
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Void> cambiarEstado(
            @PathVariable String id,
            @RequestBody String nuevoEstado) {
        logger.info("Cambiando estado de la persona ID: {} a {}", id, nuevoEstado);
        personaService.cambiarEstado(id, nuevoEstado);
        logger.info("Estado cambiado correctamente para la persona ID: {}", id);
        return ResponseEntity.noContent().build();
    }
}
