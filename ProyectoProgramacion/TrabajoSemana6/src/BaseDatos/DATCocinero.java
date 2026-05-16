/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BaseDatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author hp
 */
public class DATCocinero {

    Conexiones BLcon = new Conexiones();

    public ResultSet RecuperarCocinero() throws ClassNotFoundException, SQLException {   
        String Sentencia = "SELECT * FROM Cocinero WHERE idCocina = ? AND nombre = ?";
        PreparedStatement ps= BLcon.getConnection().prepareStatement(Sentencia);
        //ps.setInt(1, idCocina);
        //ps.setString(2, nombre);
        return ps.executeQuery();
    }
}
