package com.banquito.cores.clientes.controller;

import com.banquito.cores.clientes.model.Cliente;
import com.banquito.cores.clientes.model.ContactoTransaccional;
import com.banquito.cores.clientes.model.Direccion;
import com.banquito.cores.clientes.model.NumeroTelefonico;
import com.banquito.cores.clientes.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {
        logger.info("Creando cliente con identificación: {} - {}", cliente.getTipoIdentificacion(), cliente.getNumeroIdentificacion());
        Cliente creado = clienteService.crear(cliente);
        logger.info("Cliente creado con ID: {}", creado.getId());
        return ResponseEntity.ok(creado);
    }

    @GetMapping("/{tipoIdentificacion}/{numeroIdentificacion}")
    public ResponseEntity<Cliente> obtenerCliente(
            @PathVariable String tipoIdentificacion,
            @PathVariable String numeroIdentificacion) {
        logger.info("Buscando cliente con identificación: {} - {}", tipoIdentificacion, numeroIdentificacion);
        Cliente cliente = clienteService.buscarPorIdentificacion(tipoIdentificacion, numeroIdentificacion);
        logger.debug("Cliente encontrado: {}", cliente);
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/{id}/contacto")
    public ResponseEntity<Void> actualizarContacto(
            @PathVariable String id,
            @RequestBody ContactoTransaccional contacto) {
        logger.info("Actualizando contacto del cliente con ID: {}", id);
        clienteService.actualizarContacto(id, contacto);
        logger.info("Contacto actualizado correctamente para cliente ID: {}", id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/direcciones")
    public ResponseEntity<Void> agregarDireccion(
            @PathVariable String id,
            @RequestBody Direccion direccion) {
        logger.info("Agregando dirección al cliente con ID: {}", id);
        clienteService.agregarDireccion(id, direccion);
        logger.info("Dirección agregada correctamente al cliente ID: {}", id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/telefonos")
    public ResponseEntity<Void> agregarTelefono(
            @PathVariable String id,
            @RequestBody NumeroTelefonico telefono) {
        logger.info("Agregando teléfono al cliente con ID: {}", id);
        clienteService.agregarTelefono(id, telefono);
        logger.info("Teléfono agregado correctamente al cliente ID: {}", id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Void> cambiarEstado(
            @PathVariable String id,
            @RequestBody String nuevoEstado) {
        logger.info("Cambiando estado del cliente ID: {} a {}", id, nuevoEstado);
        clienteService.cambiarEstado(id, nuevoEstado);
        logger.info("Estado cambiado correctamente para cliente ID: {}", id);
        return ResponseEntity.noContent().build();
    }
}
