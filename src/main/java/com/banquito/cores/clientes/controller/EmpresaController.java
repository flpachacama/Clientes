package com.banquito.cores.clientes.controller;

import com.banquito.cores.clientes.model.Accionista;
import com.banquito.cores.clientes.model.Empresa;
import com.banquito.cores.clientes.model.Representante;
import com.banquito.cores.clientes.service.EmpresaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/empresas")
public class EmpresaController {

    private static final Logger logger = LoggerFactory.getLogger(EmpresaController.class);

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @PostMapping
    public ResponseEntity<Empresa> crearEmpresa(@RequestBody Empresa empresa) {
        logger.info("Creando empresa con identificación: {} - {}", empresa.getTipoIdentificacion(), empresa.getNumeroIdentificacion());
        Empresa creada = empresaService.crear(empresa);
        logger.info("Empresa creada con ID: {}", creada.getId());
        return ResponseEntity.ok(creada);
    }

    @GetMapping("/{tipoIdentificacion}/{numeroIdentificacion}")
    public ResponseEntity<Empresa> obtenerEmpresa(
            @PathVariable String tipoIdentificacion,
            @PathVariable String numeroIdentificacion) {
        logger.info("Buscando empresa con identificación: {} - {}", tipoIdentificacion, numeroIdentificacion);
        Empresa empresa = empresaService.buscarPorIdentificacion(tipoIdentificacion, numeroIdentificacion);
        logger.debug("Empresa encontrada: {}", empresa);
        return ResponseEntity.ok(empresa);
    }

    @PostMapping("/{id}/representantes")
    public ResponseEntity<Void> agregarRepresentante(
            @PathVariable String id,
            @RequestBody Representante representante) {
        logger.info("Agregando representante al empresa con ID: {}", id);
        empresaService.agregarRepresentante(id, representante);
        logger.info("Representante agregado correctamente a la empresa ID: {}", id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/accionistas")
    public ResponseEntity<Void> agregarAccionista(
            @PathVariable String id,
            @RequestBody Accionista accionista) {
        logger.info("Agregando accionista a la empresa con ID: {}", id);
        empresaService.agregarAccionista(id, accionista);
        logger.info("Accionista agregado correctamente a la empresa ID: {}", id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Void> cambiarEstado(
            @PathVariable String id,
            @RequestBody String nuevoEstado) {
        logger.info("Cambiando estado de la empresa ID: {} a {}", id, nuevoEstado);
        empresaService.cambiarEstado(id, nuevoEstado);
        logger.info("Estado cambiado correctamente para la empresa ID: {}", id);
        return ResponseEntity.noContent().build();
    }
}
