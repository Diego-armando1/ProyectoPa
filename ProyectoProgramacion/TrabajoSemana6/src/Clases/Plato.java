/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

public class Plato {
    private int idPlato;
    private String nombre;
    private int tiempoPreparacion;
    private String estado;
    private Cocinero cocinero;

    public Plato() {
    }

    public Plato(int idPlato, String nombre, int tiempoPreparacion, String estado, Cocinero cocinero) {
        this.idPlato = idPlato;
        this.nombre = nombre;
        this.tiempoPreparacion = tiempoPreparacion;
        this.estado = estado;
        this.cocinero = cocinero;
    }

    public int getIdPlato() {
        return idPlato;
    }

    public void setIdPlato(int idPlato) {
        this.idPlato = idPlato;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    public void setTiempoPreparacion(int tiempoPreparacion) {
        this.tiempoPreparacion = tiempoPreparacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Cocinero getCocinero() {
        return cocinero;
    }

    public void setCocinero(Cocinero cocinero) {
        this.cocinero = cocinero;
    }

    @Override
    public String toString() {
        return "Plato{" + "idPlato=" + idPlato + ", nombre=" + nombre + ", tiempoPreparacion=" + tiempoPreparacion + ", estado=" + estado + ", cocinero=" + cocinero + '}';
    }
    
}
