/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author hp
 */
@Entity
@Table(name = "historial")
@NamedQueries({
    @NamedQuery(name = "Historial.findAll", query = "SELECT h FROM Historial h"),
    @NamedQuery(name = "Historial.findByIdHistorial", query = "SELECT h FROM Historial h WHERE h.idHistorial = :idHistorial"),
    @NamedQuery(name = "Historial.findByEstado", query = "SELECT h FROM Historial h WHERE h.estado = :estado"),
    @NamedQuery(name = "Historial.findByFechahora", query = "SELECT h FROM Historial h WHERE h.fechahora = :fechahora"),
    @NamedQuery(name = "Historial.findByNombrereceptor", query = "SELECT h FROM Historial h WHERE h.nombrereceptor = :nombrereceptor")})
public class Historial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idHistorial")
    private Integer idHistorial;
    @Basic(optional = false)
    @Column(name = "Estado")
    private String estado;
    @Basic(optional = false)
    @Column(name = "Fecha_hora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechahora;
    @Basic(optional = false)
    @Lob
    @Column(name = "Observacion")
    private String observacion;
    @Basic(optional = false)
    @Column(name = "Nombre_receptor")
    private String nombrereceptor;
    @JoinColumn(name = "idPaquete", referencedColumnName = "idPaquete")
    @ManyToOne(optional = false)
    private Paquete idPaquete;
    @JoinColumn(name = "idTrabajador", referencedColumnName = "idTrabajador")
    @ManyToOne(optional = false)
    private Trabajador idTrabajador;

    public Historial() {
    }

    public Historial(Integer idHistorial) {
        this.idHistorial = idHistorial;
    }

    public Historial(Integer idHistorial, String estado, Date fechahora, String observacion, String nombrereceptor) {
        this.idHistorial = idHistorial;
        this.estado = estado;
        this.fechahora = fechahora;
        this.observacion = observacion;
        this.nombrereceptor = nombrereceptor;
    }

    public Integer getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(Integer idHistorial) {
        this.idHistorial = idHistorial;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechahora() {
        return fechahora;
    }

    public void setFechahora(Date fechahora) {
        this.fechahora = fechahora;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getNombrereceptor() {
        return nombrereceptor;
    }

    public void setNombrereceptor(String nombrereceptor) {
        this.nombrereceptor = nombrereceptor;
    }

    public Paquete getIdPaquete() {
        return idPaquete;
    }

    public void setIdPaquete(Paquete idPaquete) {
        this.idPaquete = idPaquete;
    }

    public Trabajador getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(Trabajador idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHistorial != null ? idHistorial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Historial)) {
            return false;
        }
        Historial other = (Historial) object;
        if ((this.idHistorial == null && other.idHistorial != null) || (this.idHistorial != null && !this.idHistorial.equals(other.idHistorial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Clases.Historial[ idHistorial=" + idHistorial + " ]";
    }
    
}
