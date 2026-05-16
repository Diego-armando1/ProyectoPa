/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;
import BaseDatos.BDProvincia;
import Clases.Provincia;
import java.sql.SQLException;
/**
 *
 * @author hp
 */
public class LogProvincia {
    BDProvincia objBDCliente = new BDProvincia();

    public boolean InsertarProvincia(Provincia objProvincia) throws ClassNotFoundException, SQLException {
        if (!objProvincia.getNombre().isEmpty()) {
            objBDCliente.InsertarProvincia(objProvincia);
        } else {
            return false;
        }
        return true;
    }
}
