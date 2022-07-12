package dao;

import java.sql.Connection;
import java.sql.DriverManager;
//import com.sun.jdi.connect.spi.Connection;

public class Conexion {

    public Connection cnx = null;

    public Connection conectar() throws Exception{
       if (cnx != null) return cnx;
        try {
            String user="root";
            String pwd = "";
//            String driver = "com.mysql.jdbc.Driver";
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/bdhospital";
            Class.forName(driver).newInstance();
            cnx = DriverManager.getConnection(url,user,pwd);
        } catch (Exception e) {
            System.out.println("Error en conexión: " + e.getMessage());
        }
        return cnx;
    }
    
    public void desconectar() throws Exception{
        if (cnx != null) cnx.close();
    }
    
    public static void main(String[] args) throws Exception{
        Conexion conexion = new Conexion();
        conexion.conectar();
        if (conexion.cnx !=null) System.out.println("ok, conectado 😎");
        else System.out.println("Conexión cerrada 😢");
    }
    
}
