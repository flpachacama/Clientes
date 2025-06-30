package com.banquito.cores.clientes.service;

import com.banquito.cores.clientes.exception.ArgumentoIlegalException;
import com.banquito.cores.clientes.exception.NoEncontradoException;
import com.banquito.cores.clientes.model.Cliente;
import com.banquito.cores.clientes.model.ContactoTransaccional;
import com.banquito.cores.clientes.model.Direccion;
import com.banquito.cores.clientes.model.NumeroTelefonico;
import com.banquito.cores.clientes.repository.ClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private static final Logger logger = LoggerFactory.getLogger(ClienteService.class);

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente crear(Cliente cliente) {
        logger.info("Intentando crear cliente: {} - {}", cliente.getTipoIdentificacion(), cliente.getNumeroIdentificacion());
        if (clienteRepository.existsByTipoIdentificacionAndNumeroIdentificacion(
                cliente.getTipoIdentificacion(), cliente.getNumeroIdentificacion())) {
            logger.warn("Ya existe un cliente con la identificación: {} - {}", cliente.getTipoIdentificacion(), cliente.getNumeroIdentificacion());
            throw new ArgumentoIlegalException("Ya existe un cliente con esta identificación.");
        }
        Cliente creado = clienteRepository.save(cliente);
        logger.info("Cliente creado con ID: {}", creado.getId());
        return creado;
    }

    public Cliente buscarPorIdentificacion(String tipo, String numero) {
        logger.info("Buscando cliente por identificación: {} - {}", tipo, numero);
        Cliente cliente = clienteRepository.findByTipoIdentificacionAndNumeroIdentificacion(tipo, numero);
        if (cliente == null) {
            logger.error("Cliente no encontrado con identificación: {} - {}", tipo, numero);
            throw new NoEncontradoException("Cliente no encontrado.");
        }
        logger.debug("Cliente encontrado: {}", cliente);
        return cliente;
    }

    public void actualizarContacto(String idCliente, ContactoTransaccional contacto) {
        logger.info("Actualizando contacto transaccional del cliente ID: {}", idCliente);
        Cliente cliente = clienteRepository.findById(idCliente).orElse(null);
        if (cliente == null) {
            logger.error("Cliente no encontrado para actualizar contacto, ID: {}", idCliente);
            throw new NoEncontradoException("Cliente no encontrado.");
        }
        cliente.setContactoTransacional(contacto);
        clienteRepository.save(cliente);
        logger.info("Contacto actualizado correctamente para cliente ID: {}", idCliente);
    }

    public void agregarDireccion(String idCliente, Direccion direccion) {
        logger.info("Agregando dirección al cliente ID: {}", idCliente);
        Cliente cliente = clienteRepository.findById(idCliente).orElse(null);
        if (cliente == null) {
            logger.error("Cliente no encontrado para agregar dirección, ID: {}", idCliente);
            throw new NoEncontradoException("Cliente no encontrado.");
        }
        cliente.getDirecciones().add(direccion);
        clienteRepository.save(cliente);
        logger.info("Dirección agregada correctamente al cliente ID: {}", idCliente);
    }

    public void agregarTelefono(String idCliente, NumeroTelefonico telefono) {
        logger.info("Agregando teléfono al cliente ID: {}", idCliente);
        Cliente cliente = clienteRepository.findById(idCliente).orElse(null);
        if (cliente == null) {
            logger.error("Cliente no encontrado para agregar teléfono, ID: {}", idCliente);
            throw new NoEncontradoException("Cliente no encontrado.");
        }
        cliente.getTelefonos().add(telefono);
        clienteRepository.save(cliente);
        logger.info("Teléfono agregado correctamente al cliente ID: {}", idCliente);
    }

    public void cambiarEstado(String idCliente, String nuevoEstado) {
        logger.info("Cambiando estado del cliente ID: {} a {}", idCliente, nuevoEstado);
        Cliente cliente = clienteRepository.findById(idCliente).orElse(null);
        if (cliente == null) {
            logger.error("Cliente no encontrado para cambiar estado, ID: {}", idCliente);
            throw new NoEncontradoException("Cliente no encontrado.");
        }
        cliente.setEstado(nuevoEstado);
        clienteRepository.save(cliente);
        logger.info("Estado cambiado correctamente para cliente ID: {}", idCliente);
    }
}
