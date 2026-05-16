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
public class DATPlato {
    Conexiones BLcon = new Conexiones();
    public ResultSet RecuperarPlatosPorCocinero(
            int idCocinero)
            throws SQLException, ClassNotFoundException {

        String sql
                = "SELECT * FROM Plato WHERE idCocinero = ?";

        PreparedStatement ps
                = BLcon.getConnection()
                        .prepareStatement(sql);

        ps.setInt(1, idCocinero);

        return ps.executeQuery();
    }
//    public ResultSet RecuperarPlato() throws ClassNotFoundException, SQLException {   
//        String Sentencia = "SELECT * FROM Plato";
//        PreparedStatement ps= BLcon.getConnection().prepareStatement(Sentencia);
//        //ps.setInt(1, idCocina);
//        //ps.setString(2, nombre);
//        return ps.executeQuery();
//    }
}
