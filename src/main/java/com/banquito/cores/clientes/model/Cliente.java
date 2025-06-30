package com.banquito.cores.clientes.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
@Document(collection = "Clientes")
@CompoundIndexes(
        @CompoundIndex(name = "idxu_Cliente_tipoIdentificacionnumeroIdentificacion", def = "{'tipoIdentificacion':1, 'numeroIdentificacion:1'}", unique = true)
)
public class Cliente {

    @Id
    private String id;
    private String tipoEntidad;
    private String codigoEntidad;
    private String nombre;
    private String nacionalidad;
    private String tipoIdentificacion;
    private String numeroIdentificacion;
    private String tipoCliente;
    private String segmento;
    private String canalAfiliacion;
    private String comentarios;
    private String estado;
    private Long version;

    private ContactoTransaccional contactoTransacional;
    private List<Direccion> direcciones;
    private List<NumeroTelefonico> telefonos;

    public Cliente(String id){
        this.id = id;
    }
}
