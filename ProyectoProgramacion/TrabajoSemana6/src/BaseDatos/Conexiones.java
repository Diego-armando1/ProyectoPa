
package BaseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexiones {
        public Connection con;
    public Connection getConnection () throws ClassNotFoundException, SQLException {
        String driver = "com.mysql.cj.jdbc.Driver";//"com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/tallersemana5";
        Class.forName(driver);
        return DriverManager.getConnection(url,"root","root");
    }
    public Connection AbrirConexion() throws ClassNotFoundException, SQLException{
        con = getConnection();
        return con;
    }
    public void CerrarConexion() throws SQLException{
       con.close();
    }

}
