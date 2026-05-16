/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author hp
 */
@Entity
@Table(name = "paquete")
@NamedQueries({
    @NamedQuery(name = "Paquete.findAll", query = "SELECT p FROM Paquete p"),
    @NamedQuery(name = "Paquete.findByIdPaquete", query = "SELECT p FROM Paquete p WHERE p.idPaquete = :idPaquete"),
    @NamedQuery(name = "Paquete.findByCodigoseguimiento", query = "SELECT p FROM Paquete p WHERE p.codigoseguimiento = :codigoseguimiento"),
    @NamedQuery(name = "Paquete.findByDireccionentrega", query = "SELECT p FROM Paquete p WHERE p.direccionentrega = :direccionentrega"),
    @NamedQuery(name = "Paquete.findByPeso", query = "SELECT p FROM Paquete p WHERE p.peso = :peso"),
    @NamedQuery(name = "Paquete.findByTipoenvio", query = "SELECT p FROM Paquete p WHERE p.tipoenvio = :tipoenvio"),
    @NamedQuery(name = "Paquete.findByCostototal", query = "SELECT p FROM Paquete p WHERE p.costototal = :costototal"),
    @NamedQuery(name = "Paquete.findByEstadoactual", query = "SELECT p FROM Paquete p WHERE p.estadoactual = :estadoactual"),
    @NamedQuery(name = "Paquete.findByFecharegistro", query = "SELECT p FROM Paquete p WHERE p.fecharegistro = :fecharegistro")})
public class Paquete implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPaquete")
    private Integer idPaquete;
    @Basic(optional = false)
    @Column(name = "Codigo_seguimiento")
    private String codigoseguimiento;
    @Basic(optional = false)
    @Column(name = "Direccion_entrega")
    private String direccionentrega;
    @Basic(optional = false)
    @Column(name = "Peso")
    private Double peso;
    @Basic(optional = false)
    @Column(name = "Tipo_envio")
    private String tipoenvio;
    @Basic(optional = false)
    @Column(name = "Costo_total")
    private Double costototal;
    @Basic(optional = false)
    @Column(name = "Estado_actual")
    private String estadoactual;
    @Basic(optional = false)
    @Column(name = "Fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecharegistro;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPaquete")
    private Collection<Historial> historialCollection;
    @JoinColumn(name = "idCiudad", referencedColumnName = "idCiudad")
    @ManyToOne(optional = false)
    private Ciudad idCiudad;
    @JoinColumn(name = "idRemitente", referencedColumnName = "idCliente")
    @ManyToOne(optional = false)
    private Cliente idRemitente;
    @JoinColumn(name = "idDestinatario", referencedColumnName = "idCliente")
    @ManyToOne(optional = false)
    private Cliente idDestinatario;

    public Paquete() {
    }

    public Paquete(Integer idPaquete) {
        this.idPaquete = idPaquete;
    }

    public Paquete(Integer idPaquete, String codigoseguimiento, String direccionentrega, Double peso, String tipoenvio, Double costototal, String estadoactual, Date fecharegistro) {
        this.idPaquete = idPaquete;
        this.codigoseguimiento = codigoseguimiento;
        this.direccionentrega = direccionentrega;
        this.peso = peso;
        this.tipoenvio = tipoenvio;
        this.costototal = costototal;
        this.estadoactual = estadoactual;
        this.fecharegistro = fecharegistro;
    }

    public Integer getIdPaquete() {
        return idPaquete;
    }

    public void setIdPaquete(Integer idPaquete) {
        this.idPaquete = idPaquete;
    }

    public String getCodigoseguimiento() {
        return codigoseguimiento;
    }

    public void setCodigoseguimiento(String codigoseguimiento) {
        this.codigoseguimiento = codigoseguimiento;
    }

    public String getDireccionentrega() {
        return direccionentrega;
    }

    public void setDireccionentrega(String direccionentrega) {
        this.direccionentrega = direccionentrega;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public String getTipoenvio() {
        return tipoenvio;
    }

    public void setTipoenvio(String tipoenvio) {
        this.tipoenvio = tipoenvio;
    }

    public Double getCostototal() {
        return costototal;
    }

    public void setCostototal(Double costototal) {
        this.costototal = costototal;
    }

    public String getEstadoactual() {
        return estadoactual;
    }

    public void setEstadoactual(String estadoactual) {
        this.estadoactual = estadoactual;
    }

    public Date getFecharegistro() {
        return fecharegistro;
    }

    public void setFecharegistro(Date fecharegistro) {
        this.fecharegistro = fecharegistro;
    }

    public Collection<Historial> getHistorialCollection() {
        return historialCollection;
    }

    public void setHistorialCollection(Collection<Historial> historialCollection) {
        this.historialCollection = historialCollection;
    }

    public Ciudad getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(Ciudad idCiudad) {
        this.idCiudad = idCiudad;
    }

    public Cliente getIdRemitente() {
        return idRemitente;
    }

    public void setIdRemitente(Cliente idRemitente) {
        this.idRemitente = idRemitente;
    }

    public Cliente getIdDestinatario() {
        return idDestinatario;
    }

    public void setIdDestinatario(Cliente idDestinatario) {
        this.idDestinatario = idDestinatario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPaquete != null ? idPaquete.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paquete)) {
            return false;
        }
        Paquete other = (Paquete) object;
        if ((this.idPaquete == null && other.idPaquete != null) || (this.idPaquete != null && !this.idPaquete.equals(other.idPaquete))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Clases.Paquete[ idPaquete=" + idPaquete + " ]";
    }
    
}
