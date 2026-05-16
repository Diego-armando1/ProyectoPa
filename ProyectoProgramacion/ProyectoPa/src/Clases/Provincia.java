/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

public class Provincia {
    private int idProvincia;
    private String nombre;

    public Provincia() {
    }

    public Provincia(int idProvincia, String nombre) {
        this.idProvincia = idProvincia;
        this.nombre = nombre;
    }

    public int getIdProvincia() {
        return idProvincia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setIdProvincia(int idProvincia) {
        this.idProvincia = idProvincia;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Provincia{" + "idProvincia=" + idProvincia + ", nombre=" + nombre + '}';
    }
     
}
