package com.banquito.cores.clientes.model;

import lombok.Data;

@Data
public class Direccion {

    private String tipo;
    private String linea1;
    private String linea2;
    private String codigoPostal;
    private String codigoGeografico;
}
