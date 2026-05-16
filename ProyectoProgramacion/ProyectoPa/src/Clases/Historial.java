/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author hp
 */
public class Historial {
    private int idHistorial;
    private String estado;
    private String fechaHora;
    private String observacion;
    private String nombreReceptor;

    private Paquete paquete;
    private Trabajador trabajador;

    public Historial() {
    }

    public Historial(int idHistorial, String estado, String fechaHora, String observacion, String nombreReceptor, Paquete paquete, Trabajador trabajador) {
        this.idHistorial = idHistorial;
        this.estado = estado;
        this.fechaHora = fechaHora;
        this.observacion = observacion;
        this.nombreReceptor = nombreReceptor;
        this.paquete = paquete;
        this.trabajador = trabajador;
    }

    public int getIdHistorial() {
        return idHistorial;
    }

    public String getEstado() {
        return estado;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public String getObservacion() {
        return observacion;
    }

    public String getNombreReceptor() {
        return nombreReceptor;
    }

    public Paquete getPaquete() {
        return paquete;
    }

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public void setIdHistorial(int idHistorial) {
        this.idHistorial = idHistorial;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public void setNombreReceptor(String nombreReceptor) {
        this.nombreReceptor = nombreReceptor;
    }

    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }
    
}
