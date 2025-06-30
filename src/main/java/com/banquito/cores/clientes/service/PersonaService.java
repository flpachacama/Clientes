package com.banquito.cores.clientes.service;

import com.banquito.cores.clientes.exception.ArgumentoIlegalException;
import com.banquito.cores.clientes.exception.NoEncontradoException;
import com.banquito.cores.clientes.model.Accionista;
import com.banquito.cores.clientes.model.Persona;
import com.banquito.cores.clientes.repository.PersonaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PersonaService {

    private static final Logger logger = LoggerFactory.getLogger(PersonaService.class);

    private final PersonaRepository personaRepository;

    public PersonaService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public Persona crear(Persona persona) {
        logger.info("Intentando crear persona: {} - {}", persona.getTipoIdentificacion(), persona.getNumeroIdentificacion());
        if (personaRepository.existsByTipoIdentificacionAndNumeroIdentificacion(
                persona.getTipoIdentificacion(), persona.getNumeroIdentificacion())) {
            logger.warn("Ya existe una persona con la identificación: {} - {}", persona.getTipoIdentificacion(), persona.getNumeroIdentificacion());
            throw new ArgumentoIlegalException("Ya existe una persona con esta identificación.");
        }
        Persona creada = personaRepository.save(persona);
        logger.info("Persona creada con ID: {}", creada.getId());
        return creada;
    }

    public Persona buscarPorIdentificacion(String tipo, String numero) {
        logger.info("Buscando persona por identificación: {} - {}", tipo, numero);
        Persona persona = personaRepository.findByTipoIdentificacionAndNumeroIdentificacion(tipo, numero);
        if (persona == null) {
            logger.error("Persona no encontrada con identificación: {} - {}", tipo, numero);
            throw new NoEncontradoException("Persona no encontrada.");
        }
        logger.debug("Persona encontrada: {}", persona);
        return persona;
    }

    public void cambiarEstado(String idPersona, String nuevoEstado) {
        logger.info("Cambiando estado de la persona ID: {} a {}", idPersona, nuevoEstado);
        Persona persona = personaRepository.findById(idPersona).orElse(null);
        if (persona == null) {
            logger.error("Persona no encontrada para cambiar estado, ID: {}", idPersona);
            throw new NoEncontradoException("Persona no encontrada.");
        }
        persona.setEstado(nuevoEstado);
        personaRepository.save(persona);
        logger.info("Estado cambiado correctamente para persona ID: {}", idPersona);
    }

}
