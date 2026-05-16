/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;
import BaseDatos.DATCocinero;
import Clases.Cocinero;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author hp
 */
public class LogCocinero {
    DATCocinero objDATPedido = new DATCocinero();
    public ArrayList<Cocinero> BuscarCocinero(ArrayList<Cocinero> ArrayCocinero) throws ClassNotFoundException, SQLException{
        //ArrayList<Pedido> ArrayPedidos = new ArrayList<Pedido>();
        Cocinero objCocinero = new Cocinero();
        ResultSet rs = objDATPedido.RecuperarCocinero();
        while (rs.next()){
                objCocinero = new Cocinero();
                objCocinero.setIdCocinero(rs.getInt(1));
                objCocinero.setNombre(rs.getString(2));
                objCocinero.setPlatosRealizados(rs.getInt(5));
                
                ArrayCocinero.add(objCocinero);
        }
        rs.close();
        return ArrayCocinero;
    }
}
