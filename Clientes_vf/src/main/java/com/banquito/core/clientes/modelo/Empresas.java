package com.banquito.core.clientes.modelo;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import com.banquito.core.clientes.enums.EstadoCliente;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "empresas", schema = "public")
public class Empresas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('empresas_id_empresa_seq')")
    @Column(name = "id_empresa", nullable = false)
    private Integer id;

    @Column(name = "tipo_identificacion", nullable = false, length = 10)
    private String tipoIdentificacion;

    @Column(name = "numero_identificacion", nullable = false, length = 13)
    private String numeroIdentificacion;

    @Column(name = "nombre_comercial", nullable = false, length = 100)
    private String nombreComercial;

    @Column(name = "razon_social", nullable = false, length = 100)
    private String razonSocial;

    @Column(name = "tipo", nullable = false, length = 10)
    private String tipo;

    @Column(name = "fecha_constitucion", nullable = false)
    private LocalDate fechaConstitucion;

    @Column(name = "correo_electronico", nullable = false, length = 40)
    private String correoElectronico;

    @Column(name = "sector_economico", nullable = false, length = 15)
    private String sectorEconomico;

    @Column(name = "fecha_registro", nullable = false)
    private Instant fechaRegistro;

    @Column(name = "fecha_actualizacion", nullable = false)
    private Instant fechaActualizacion;

    @Column(name = "estado", nullable = false, length = 15)
    private EstadoCliente estado;

    @Column(name = "version", nullable = false, precision = 9)
    private BigDecimal version;

    @OneToMany(mappedBy = "empresaParticipe")  
    private Set<AccionistasEmpresas> empresasDondeEsAccionista = new LinkedHashSet<>();

    @OneToMany(mappedBy = "empresa")
    private Set<AccionistasEmpresas> accionistasDeEstaEmpresa = new LinkedHashSet<>();

    @OneToMany(mappedBy = "empresa")
    private Set<Clientes> clientes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "empresa")
    private Set<RepresentantesEmpresas> representantesEmpresas = new LinkedHashSet<>();

    public Empresas() {
    }

    public Empresas(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getFechaConstitucion() {
        return fechaConstitucion;
    }

    public void setFechaConstitucion(LocalDate fechaConstitucion) {
        this.fechaConstitucion = fechaConstitucion;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getSectorEconomico() {
        return sectorEconomico;
    }

    public void setSectorEconomico(String sectorEconomico) {
        this.sectorEconomico = sectorEconomico;
    }

    public Instant getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Instant fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Instant getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Instant fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public EstadoCliente getEstado() {
        return estado;
    }

    public void setEstado(EstadoCliente estado) {
        this.estado = estado;
    }

    public BigDecimal getVersion() {
        return version;
    }

    public void setVersion(BigDecimal version) {
        this.version = version;
    }

    public Set<AccionistasEmpresas> getEmpresasDondeEsAccionista() {
        return empresasDondeEsAccionista;
    }

    public void setEmpresasDondeEsAccionista(Set<AccionistasEmpresas> empresasDondeEsAccionista) {
        this.empresasDondeEsAccionista = empresasDondeEsAccionista;
    }

    public Set<AccionistasEmpresas> getAccionistasDeEstaEmpresa() {
        return accionistasDeEstaEmpresa;
    }

    public void setAccionistasDeEstaEmpresa(Set<AccionistasEmpresas> accionistasDeEstaEmpresa) {
        this.accionistasDeEstaEmpresa = accionistasDeEstaEmpresa;
    }

    public Set<Clientes> getClientes() {
        return clientes;
    }

    public void setClientes(Set<Clientes> clientes) {
        this.clientes = clientes;
    }

    public Set<RepresentantesEmpresas> getRepresentantesEmpresas() {
        return representantesEmpresas;
    }

    public void setRepresentantesEmpresas(Set<RepresentantesEmpresas> representantesEmpresas) {
        this.representantesEmpresas = representantesEmpresas;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Empresas other = (Empresas) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Empresas [id=" + id + ", tipoIdentificacion=" + tipoIdentificacion + ", numeroIdentificacion="
                + numeroIdentificacion + ", nombreComercial=" + nombreComercial + ", razonSocial=" + razonSocial
                + ", tipo=" + tipo + ", fechaConstitucion=" + fechaConstitucion + ", correoElectronico="
                + correoElectronico + ", sectorEconomico=" + sectorEconomico + ", fechaRegistro=" + fechaRegistro
                + ", fechaActualizacion=" + fechaActualizacion + ", estado=" + estado + ", version=" + version
                + ", empresasDondeEsAccionista=" + empresasDondeEsAccionista + ", accionistasDeEstaEmpresa="
                + accionistasDeEstaEmpresa + ", clientes=" + clientes + ", representantesEmpresas="
                + representantesEmpresas + "]";
    }

}