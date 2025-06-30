package com.banquito.cores.clientes.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ContactoTransaccional {
    private String telefono;
    private String correoElectronico;
    private LocalDateTime fechaUltimaActualizacion;
}
