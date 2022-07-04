/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procesos;

import Objetos.Usuario;
import config.ConnectionsJDBC;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author alpha
 */
public class ComprobarUsuario {
    private Connection conn;

    public ComprobarUsuario() {
       
    }
    
    private PreparedStatement consul(String sql) throws SQLException{
        conn = ConnectionsJDBC.getConnection();
        PreparedStatement pst;
        return pst=conn.prepareStatement(sql);
    }
    
    public int BuscarUsuario(String usuario, String contras){
        
        PreparedStatement pst;
        ResultSet rs;
        int cont=0;
        int area=0;
        String sql= "select * from usuario where nombre='"+usuario+"'";// and contraseña='"+contras+"'";
               
        try{
            pst=consul(sql);            
            rs=pst.executeQuery();            
            while(rs.next()){
                if (rs.getString(2).equals(usuario) && rs.getString(3).equals(contras)) {
                    area=rs.getInt("tipo");
                }else{
                    area=-1;
                }
            }
            conn.close();
        }catch(SQLException e){
            
        }
        return area;    
    }
    
    /**
    *Genera un arreglo con los datos del cliente si es encontrado 
    */
    public String[] BuscarCliente(String nit) {
        String[] usuario = new String[0];
        PreparedStatement pst;
        ResultSet rs;
        int cont=0;
        int area=0;
        String sql= "select * from cliente where nit='"+nit+"'";
        
               
        try{
            pst=consul(sql);
            rs=pst.executeQuery();
            
            while(rs.next()){
                if (rs.getString(1)!=null) {
                    usuario= new String[3];
                    usuario[0]=rs.getString(1);
                    usuario[1]=rs.getString(2);
                    usuario[2]=rs.getString(3);
                }
                
            }
            conn.close();
        }catch(SQLException e){
            
        }
        return usuario;
    }
    
    public void AgregarCliente(String nit, String nombre, String direccion){
        Statement sta=null;
        ResultSet rs;
        String sql= "INSERT INTO cliente VALUES('"+nit+"', '"+nombre+"', '"+direccion+"')";
               
        try{
            conn = ConnectionsJDBC.getConnection();
            sta=conn.createStatement();
            sta.executeUpdate(sql);
            conn.close();
        }catch(SQLException e){
        }        
    }
    public void AgregarUsuario(String nombre, String contraseña, int nivel){
       Statement sta=null;
        ResultSet rs;
        String sql= "INSERT INTO usuario VALUES("+nivel+", '"+nombre+"', '"+contraseña+"')";
        try{
            conn = ConnectionsJDBC.getConnection();
            sta=conn.createStatement();
            sta.executeUpdate(sql);
            conn.close();
        }catch(SQLException e){
        }        
    }
    
    public void NoFactura(String vendedor, String precio, String cliente){
        int factura=0;
        Statement sta=null;
        ResultSet rs;
        String sql= "INSERT INTO factura VALUES( 0, "+precio+", '"+vendedor+"', '"+cliente+"')";
        try{
            conn = ConnectionsJDBC.getConnection();
            sta=conn.createStatement();
            sta.executeUpdate(sql);
            
            conn.close();
        }catch(SQLException e){
        }
    }
    
    public int BuscarFactura(){
        PreparedStatement pst;
        ResultSet rs;
        int cont=0;
        String sql= "select * from factura";// and contraseña='"+contras+"'";
        try{
            pst=consul(sql);
            
            rs=pst.executeQuery();
            
            while(rs.next()){
               cont=rs.getInt(1);
            }
            conn.close();
        }catch(SQLException e){
            
        }
        return cont;    
    }
    public ArrayList<Usuario> Usuarios() {
        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        PreparedStatement pst;
        ResultSet rs;
        int cont=0;
        int area=0;
        String sql= "select * from usuario";       
        try{
            pst=consul(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                lista.add(new Usuario(rs.getString(2), rs.getString(3), rs.getInt(1)));
            }
            conn.close();
        }catch(SQLException e){
            
        }
        return lista;
    }
}
