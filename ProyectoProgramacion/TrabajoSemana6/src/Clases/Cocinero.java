/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

public class Cocinero {
    private int idCocinero;
    private String nombre;
    private int platosRealizados;

    public Cocinero() {
    }

    public Cocinero(int idCocinero, String nombre, int platosRealizados) {
        this.idCocinero = idCocinero;
        this.nombre = nombre;
        this.platosRealizados = platosRealizados;
    }

    public int getIdCocinero() {
        return idCocinero;
    }

    public void setIdCocinero(int idCocinero) {
        this.idCocinero = idCocinero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPlatosRealizados() {
        return platosRealizados;
    }

    @Override
    public String toString() {
        return "Cocinero{" + "idCocinero=" + idCocinero + ", nombre=" + nombre + ", platosRealizados=" + platosRealizados + '}';
    }

    public void setPlatosRealizados(int platosRealizados) {
        this.platosRealizados = platosRealizados;
    }

    
    
}
