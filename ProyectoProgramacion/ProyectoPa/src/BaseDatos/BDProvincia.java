/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BaseDatos;

import Clases.Provincia;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BDProvincia {
    conexion BLcon = new conexion();

    public int InsertarProvincia(Provincia objProvincia) throws ClassNotFoundException, SQLException {
        String Sentencia = "insert into provincia (nombre)"
                + "values (?)";
        PreparedStatement ps = BLcon.getConnection().prepareStatement(Sentencia);
        ps.setString(1, objProvincia.getNombre());
        return ps.executeUpdate();
    }

}
