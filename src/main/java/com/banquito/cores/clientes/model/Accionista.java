package com.banquito.cores.clientes.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Accionista {

    private String codigoPersona;
    private BigDecimal participacion;
    private String estado;
    private Long version;

}
