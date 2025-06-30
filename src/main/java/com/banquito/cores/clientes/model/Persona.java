package com.banquito.cores.clientes.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
@Document(collection = "Personas")
@CompoundIndexes(
        @CompoundIndex(name = "idxu_Persona_tipoIdentificacionnumeroIdentificacion", def = "{'tipoIdentificacion':1, 'numeroIdentificacion:1'}", unique = true)
)
public class Persona {

    @Id
    private String id;
    private String tipoIdentificacion;
    private String numeroIdentificacion;
    private String nombre;
    private String genero;
    private String fechaNacimiento;
    private String estadoCivil;
    private String nivelEstudio;
    private String correoElectronico;
    private String estado;
    private Long version;

    private List<Accionista> accionistas;

    public Persona(String id){
        this.id = id;
    }
}
