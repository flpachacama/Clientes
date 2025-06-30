package com.banquito.cores.clientes.service;

import com.banquito.cores.clientes.exception.ArgumentoIlegalException;
import com.banquito.cores.clientes.exception.NoEncontradoException;
import com.banquito.cores.clientes.model.Accionista;
import com.banquito.cores.clientes.model.Empresa;
import com.banquito.cores.clientes.model.Representante;
import com.banquito.cores.clientes.repository.EmpresaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EmpresaService {

    private static final Logger logger = LoggerFactory.getLogger(EmpresaService.class);

    private final EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public Empresa crear(Empresa empresa) {
        logger.info("Intentando crear empresa con identificación: {} - {}", empresa.getTipoIdentificacion(), empresa.getNumeroIdentificacion());
        if (empresaRepository.existsByTipoIdentificacionAndNumeroIdentificacion(
                empresa.getTipoIdentificacion(), empresa.getNumeroIdentificacion())) {
            logger.warn("Ya existe una empresa con la identificación: {} - {}", empresa.getTipoIdentificacion(), empresa.getNumeroIdentificacion());
            throw new ArgumentoIlegalException("Ya existe una empresa con esta identificación.");
        }
        Empresa creada = empresaRepository.save(empresa);
        logger.info("Empresa creada con ID: {}", creada.getId());
        return creada;
    }

    public Empresa buscarPorIdentificacion(String tipo, String numero) {
        logger.info("Buscando empresa por identificación: {} - {}", tipo, numero);
        Empresa empresa = empresaRepository.findByTipoIdentificacionAndNumeroIdentificacion(tipo, numero);
        if (empresa == null) {
            logger.error("Empresa no encontrada con identificación: {} - {}", tipo, numero);
            throw new NoEncontradoException("Empresa no encontrada.");
        }
        logger.debug("Empresa encontrada: {}", empresa);
        return empresa;
    }

    public void cambiarEstado(String idEmpresa, String nuevoEstado) {
        logger.info("Cambiando estado de la empresa ID: {} a {}", idEmpresa, nuevoEstado);
        Empresa empresa = empresaRepository.findById(idEmpresa).orElse(null);
        if (empresa == null) {
            logger.error("Empresa no encontrada para cambiar estado, ID: {}", idEmpresa);
            throw new NoEncontradoException("Empresa no encontrada.");
        }
        empresa.setEstado(nuevoEstado);
        empresaRepository.save(empresa);
        logger.info("Estado cambiado correctamente para empresa ID: {}", idEmpresa);
    }

    public void agregarRepresentante(String idEmpresa, Representante representante) {
        logger.info("Agregando representante a la empresa ID: {}", idEmpresa);
        Empresa empresa = empresaRepository.findById(idEmpresa).orElse(null);
        if (empresa == null) {
            logger.error("Empresa no encontrada para agregar representante, ID: {}", idEmpresa);
            throw new NoEncontradoException("Empresa no encontrada.");
        }
        empresa.getRepresentantes().add(representante);
        empresaRepository.save(empresa);
        logger.info("Representante agregado correctamente a la empresa ID: {}", idEmpresa);
    }

    public void agregarAccionista(String idEmpresa, Accionista accionista) {
        logger.info("Agregando accionista a la empresa ID: {}", idEmpresa);
        Empresa empresa = empresaRepository.findById(idEmpresa).orElse(null);
        if (empresa == null) {
            logger.error("Empresa no encontrada para agregar accionista, ID: {}", idEmpresa);
            throw new NoEncontradoException("Empresa no encontrada.");
        }
        empresa.getAccionistas().add(accionista);
        empresaRepository.save(empresa);
        logger.info("Accionista agregado correctamente a la empresa ID: {}", idEmpresa);
    }
}
