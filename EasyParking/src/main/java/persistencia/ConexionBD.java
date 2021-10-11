/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.*;
import java.util.Set;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
/**
 *
 * @author maurosebastianruiz
 */
public class ConexionBD {
    
    public String DB_driver ="";
    public String url = "";
    public String db = "";
    public String host = "";
    public String username = "";
    public String password = "";
    public ResultSet resultset = null;
    public Connection conexion = null;
    public Statement stmt = null;
    
    public ConexionBD(){
        DB_driver = "com.mysql.jdcb.Driver";
        host = "localhost:3306";
        db = "parqueaderoEasyParking";
        url = "jdbc:mysql://"+ host + "/" + db;
        username = "root";
        password = "12345678";
        
        try{
            //Asignación del driver
            Class.forName(DB_driver);
        }catch(ClassNotFoundException ex){
            System.out.println("Error en la asignación del driver");
        }
        try {
            //Crear conexión
            conexion = DriverManager.getConnection(url,username,password);
            System.out.println("Se ha conectado a la base de datos");
        } catch (SQLException e) {
            System.out.println("Error en la conexión");
        }
    }
    //Retornar conexion
    public Connection getConenction(){
       return conexion;

    }
    //Cerrar conexion
    public void closeConnection(){
        if (conexion != null){
            try {
                conexion.close();
            } catch(SQLException e) {
                System.out.println("Error al cerrar conexión");
            }
        }
    }
    
    //Consultar a BD
    public ResultSet consultarBD(String sentencia){
        try {
            stmt = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultset =stmt.executeQuery(sentencia);
            
        } catch (SQLException e) {
            System.out.println("Error al consultar");
        }
        return resultset;   
    }
    //Insertar datos
    public boolean insertBD(String sentencia){
        try {
            stmt = conexion.createStatement();
            stmt.execute(sentencia);
            return true;
            
        }catch (SQLException e) {
            System.out.println("Error al insertar datos");
            return false;
        }
        
    }
    //Borrar dato
    public boolean borrarBD(String sentencia){
        try {
            stmt = conexion.createStatement();
            stmt.execute(sentencia);
            return true;
            
        }catch (SQLException e) {
            System.out.println("Error al borrar datos");
            return false;
        }
    }
    
    //Actualizar base de datos
    public boolean actualizarBD(String sentencia){
        try {
            stmt = conexion.createStatement();
            stmt.execute(sentencia);
            return true;
            
        }catch (SQLException e) {
            System.out.println("Error al actualizar datos");
            return false;
        }
    }
    
    public boolean setAutoCommitBD(boolean commit){
        try {
            conexion.setAutoCommit(commit);
           return true;
        } catch (SQLException e) {
            System.out.println("Error al realizar setAutoCommitBD");
            return false;
        }   
    }
     
    //Confirmar que lo que se está haciendo ya va a quedar
    public boolean commitBD(){
        try {
            conexion.commit();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al hacer commit");
            return false;
        }
    }
    
    public boolean rollback(){
        try {
            conexion.rollback();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al hacer rollback");
            return false;
        }
    }
    
}
