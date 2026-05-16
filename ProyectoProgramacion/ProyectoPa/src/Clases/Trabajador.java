/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author hp
 */
public class Trabajador {
    private int idTrabajador;
    private String nombre;
    private String username;
    private String password;
    private String tipoTrabajador;

    public Trabajador() {
    }

    public Trabajador(int idTrabajador, String nombre, String username, String password, String tipoTrabajador) {
        this.idTrabajador = idTrabajador;
        this.nombre = nombre;
        this.username = username;
        this.password = password;
        this.tipoTrabajador = tipoTrabajador;
    }

    public int getIdTrabajador() {
        return idTrabajador;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getTipoTrabajador() {
        return tipoTrabajador;
    }

    public void setIdTrabajador(int idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTipoTrabajador(String tipoTrabajador) {
        this.tipoTrabajador = tipoTrabajador;
    }

    @Override
    public String toString() {
        return "Trabajador{" + "idTrabajador=" + idTrabajador + ", nombre=" + nombre + ", username=" + username + ", password=" + password + ", tipoTrabajador=" + tipoTrabajador + '}';
    }
    
}
