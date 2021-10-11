/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import persistencia.ConexionBD;

/**
 *
 * @author maurosebastianruiz
 */
public class Cliente {
   
    private String placa;
    private String tipoVe;
    private String idpark;
    private String cliente;
    private String identificacion;
    private String estado;
    
   

    public Cliente() {

    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getTipoVe() {
        return tipoVe;
    }

    public void setTipoVe(String tipoVe) {
        this.tipoVe = tipoVe;
    }

    public String getIdpark() {
        return idpark;
    }

    public void setIdpark(String idpark) {
        this.idpark = idpark;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
    public boolean guardarCliente(){
        ConexionBD conexion = new ConexionBD();
        
        String sentencia = "INSERT INTO parqueadero(placa, tipoVe, idpark, cliente, identificacion, estado)"
                + "VALUES ( '" + this.placa + "','" + this.tipoVe + "','" + this.idpark + "','" + this.cliente + "','" + this.identificacion + "','" + this.estado + "'); ";
        
        if(conexion.setAutoCommitBD(false)){
            if (conexion.insertBD(sentencia)){
                conexion.commitBD();
                conexion.closeConnection();
                return true;
            }else {
                conexion.rollback();
                conexion.closeConnection();
                return false;
            }
        }else{
            conexion.closeConnection();
            return false;
        }
    }
    
    public boolean borrarCliente(String placa){
        ConexionBD conexion = new ConexionBD();
        
        String sentencia = "DELETE FROM parqueadero WHERE placa ='" + this.placa +"'"; 
        if(conexion.setAutoCommitBD(false)){
            if (conexion.borrarBD(sentencia)){
                conexion.commitBD();
                conexion.closeConnection();
                return true;
            }else {
                conexion.rollback();
                conexion.closeConnection();
                return false;
            }
        }else{
            conexion.closeConnection();
            return false;
        }
        
    }
    
    //2021-10-10 16:10:15
    
    public boolean actualizarCliente(String placa){
        ConexionBD conexion = new ConexionBD();
        /*
        String sentencia = "UPDATE clietes SET placa='" + this.placa + "', tipoVehiculo='"+ this.tipoVehiculo + "', nombreCliente='" + this.nombreCliente + "', identificacion='" + this.identificacion + "',"
                +  "idParqueadero='" + this.idParqueadero + "',horaIngreso='" + this.horaIngreso + "',horaSalida='" + this.horaSalida + "',estado='" + this.estado + "',valorPagar'" + this.valorPagar + "' WHERE placa ='" + this.placa + "';";
       */
        String sentencia = "UPDATE parqueadero SET estado='" + this.estado + "' WHERE placa ='" + this.placa + "';";
        
        if(conexion.setAutoCommitBD(false)){
            if (conexion.actualizarBD(sentencia)){
                conexion.commitBD();
                conexion.closeConnection();
                return true;
            }else {
                conexion.rollback();
                conexion.closeConnection();
                return false;
            }
        }else{
            conexion.closeConnection();
            return false;
        }
    }
    
    public List<Cliente> listarCliente() throws SQLException{
        ConexionBD conexion = new ConexionBD();
        
        String sentincia = "SELECT * FROM parqueadero";
        List<Cliente> listaClientes = new ArrayList<>();
        ResultSet rs = conexion.consultarBD(sentincia);
        
        while(rs.next()){
            Cliente c = new Cliente();
            
            c.setPlaca(rs.getString("placa"));
            c.setTipoVe(rs.getString("tipoVe"));
            c.setIdpark(rs.getString("idpark"));
            c.setCliente(rs.getString("cliente"));
            c.setIdentificacion(rs.getString("identificacion"));   
            c.setEstado(rs.getString("estado"));

            listaClientes.add(c);
            
        }
        conexion.closeConnection();
        return listaClientes;
    }

    public Cliente obtenerCliente() throws SQLException{
        ConexionBD conexion = new ConexionBD();
        
            String sentencia = "SELECT * FROM parqueadero WHERE placa='" + this.placa +"'";
            ResultSet rs = conexion.consultarBD(sentencia);
            
            if (rs.next()){
                Cliente c = new Cliente();
            
                c.setPlaca(rs.getString("placa"));
                c.setTipoVe(rs.getString("tipoVe"));
                c.setIdpark(rs.getString("idpark"));
                c.setCliente(rs.getString("cliente"));
                c.setIdentificacion(rs.getString("identificacion"));
                c.setEstado(rs.getString("estado"));
                conexion.closeConnection();
                return c;
            }else{
                conexion.closeConnection();
                return null;
            }
    }


}
    