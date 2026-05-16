/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author hp
 */
public class Ciudad {
     private int idCiudad;
    private String nombre;
    private double tarifaBaseKg;
    private Provincia provincia;

    public Ciudad() {
    }

    public Ciudad(int idCiudad, String nombre, double tarifaBaseKg, Provincia provincia) {
        this.idCiudad = idCiudad;
        this.nombre = nombre;
        this.tarifaBaseKg = tarifaBaseKg;
        this.provincia = provincia;
    }

    public int getIdCiudad() {
        return idCiudad;
    }

    public String getNombre() {
        return nombre;
    }

    public double getTarifaBaseKg() {
        return tarifaBaseKg;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTarifaBaseKg(double tarifaBaseKg) {
        this.tarifaBaseKg = tarifaBaseKg;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    @Override
    public String toString() {
        return "Ciudad{" + "idCiudad=" + idCiudad + ", nombre=" + nombre + ", tarifaBaseKg=" + tarifaBaseKg + ", provincia=" + provincia + '}';
    }
    
}
