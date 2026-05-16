/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import BaseDatos.DATPlato;
import Clases.Plato;
import Clases.Cocinero;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author hp
 */
public class LogPlato {

//    DATPlato objDATPedido = new DATPlato();
//    public ArrayList<Plato> BuscarPlato()throws ClassNotFoundException, SQLException {
//    ArrayList<Plato> ArrayPlato =new ArrayList<>();
//
//    ResultSet rs = objDATPedido.RecuperarPlatosPorCocinero(idCocinero);
//
//    while (rs.next()) {
//        Plato objPlato = new Plato();
//        objPlato.setIdPlato(rs.getInt(1));
//        objPlato.setNombre(rs.getString(2));
//        objPlato.setTiempoPreparacion(rs.getInt(3));
//        objPlato.setEstado(rs.getString(4));
//        ArrayPlato.add(objPlato);
//    }
//
//    rs.close();
//
//    return ArrayPlato;
//}
    public ArrayList<Plato> BuscarPlato(ArrayList<Plato> ArrayPlato) throws ClassNotFoundException, SQLException {
        ArrayList<Pedido> ArrayPedidos = new ArrayList<Pedido>();
        Plato objPlato = new Plato();
        ResultSet rs = objDATPedido.RecuperarPlato();
        while (rs.next()) {

            objPlato = new Plato();

            objPlato.setIdPlato(rs.getInt(1));
            objPlato.setNombre(rs.getString(2));
            objPlato.setTiempoPreparacion(rs.getInt(3));
            objPlato.setEstado(rs.getString(4));

            Cocinero objCocinero = new Cocinero();
            objCocinero.setIdCocinero(rs.getInt(5));
            objPlato.setCocinero(objCocinero);
            ArrayPlato.add(objPlato);
        }
        rs.close();
        return ArrayPlato;
    }
}
