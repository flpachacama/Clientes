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
@Document(collection = "Empresas")
@CompoundIndexes(
        @CompoundIndex(name = "idxu_Empresa_tipoIdentificacionnumeroIdentificacion", def = "{'tipoIdentificacion':1, 'numeroIdentificacion:1'}", unique = true)
)
public class Empresa {
    @Id
    private String id;
    private String tipoIdentificacion;
    private String numeroIdentificacion;
    private String nombreComercial;
    private String razonSocial;
    private String tipo;
    private String fechaConstitucion;
    private String correoElectronico;
    private String sectorEconomico;
    private String estado;
    private Long version;

    private List<Representante> representantes;
    private List<Accionista> accionistas;

    public Empresa(String id){
        this.id = id;
    }
}
