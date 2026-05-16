/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author hp
 */
public class Paquete {
    private int idPaquete;
    private String codigoSeguimiento;
    private String direccionEntrega;
    private double peso;
    private String tipoEnvio;
    private double costoTotal;
    private String estadoActual;
    private String fechaRegistro;

    private Cliente remitente;
    private Cliente destinatario;
    private Ciudad ciudad;

    public Paquete() {
    }

    public Paquete(int idPaquete, String codigoSeguimiento, String direccionEntrega, double peso, String tipoEnvio, double costoTotal, String estadoActual, String fechaRegistro, Cliente remitente, Cliente destinatario, Ciudad ciudad) {
        this.idPaquete = idPaquete;
        this.codigoSeguimiento = codigoSeguimiento;
        this.direccionEntrega = direccionEntrega;
        this.peso = peso;
        this.tipoEnvio = tipoEnvio;
        this.costoTotal = costoTotal;
        this.estadoActual = estadoActual;
        this.fechaRegistro = fechaRegistro;
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.ciudad = ciudad;
    }

    public int getIdPaquete() {
        return idPaquete;
    }

    public String getCodigoSeguimiento() {
        return codigoSeguimiento;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public double getPeso() {
        return peso;
    }

    public String getTipoEnvio() {
        return tipoEnvio;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    public String getEstadoActual() {
        return estadoActual;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public Cliente getRemitente() {
        return remitente;
    }

    public Cliente getDestinatario() {
        return destinatario;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setIdPaquete(int idPaquete) {
        this.idPaquete = idPaquete;
    }

    public void setCodigoSeguimiento(String codigoSeguimiento) {
        this.codigoSeguimiento = codigoSeguimiento;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setTipoEnvio(String tipoEnvio) {
        this.tipoEnvio = tipoEnvio;
    }

    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }

    public void setEstadoActual(String estadoActual) {
        this.estadoActual = estadoActual;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public void setRemitente(Cliente remitente) {
        this.remitente = remitente;
    }

    public void setDestinatario(Cliente destinatario) {
        this.destinatario = destinatario;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public String toString() {
        return "Paquete{" + "idPaquete=" + idPaquete + ", codigoSeguimiento=" + codigoSeguimiento + ", direccionEntrega=" + direccionEntrega + ", peso=" + peso + ", tipoEnvio=" + tipoEnvio + ", costoTotal=" + costoTotal + ", estadoActual=" + estadoActual + ", fechaRegistro=" + fechaRegistro + ", remitente=" + remitente + ", destinatario=" + destinatario + ", ciudad=" + ciudad + '}';
    }
    
}
