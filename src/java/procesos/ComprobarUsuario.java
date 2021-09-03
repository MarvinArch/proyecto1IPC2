/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procesos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author alpha
 */
public class ComprobarUsuario {
    String driver;
    String url;
    String uss;
    String contra;

    public ComprobarUsuario() {
        this.driver = "com.mysql.jdbc.Driver";
        this.url = "jdbc:mysql://localhost:3306/proyecto1";
        this.uss = "alpha23";
        this.contra = "f3fxbv12";
    }
    
    public int BuscarUsuario(String usuario, String contras){
        Connection conn;
        PreparedStatement pst;
        ResultSet rs;
        int cont=0;
        int area=0;
        String sql= "select * from usuario where nombre='"+usuario+"'";// and contraseña='"+contras+"'";
               
        try{
            Class.forName(this.driver);
            
            conn = DriverManager.getConnection(url,uss,contra);
            pst=conn.prepareStatement(sql);
            
            rs=pst.executeQuery();
            
            while(rs.next()){
                if (rs.getString(2).equals(usuario) && rs.getString(3).equals(contras)) {
                    area=rs.getInt("tipo");
                }else{
                    area=-1;
                }
            }
            conn.close();
        }catch(ClassNotFoundException | SQLException e){
            
        }
        return area;    
    }
    
    /**
    *Genera un arreglo con los datos del cliente si es encontrado 
    */
    public String[] BuscarCliente(String nit) {
        String[] usuario = new String[0];
        Connection conn;
        PreparedStatement pst;
        ResultSet rs;
        int cont=0;
        int area=0;
        String sql= "select * from cliente where nit='"+nit+"'";
        
               
        try{
            Class.forName(this.driver);
            conn = DriverManager.getConnection(url,uss,contra);
            pst=conn.prepareStatement(sql);
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
        }catch(ClassNotFoundException | SQLException e){
            
        }
        return usuario;
    }
    
    public void AgregarCliente(String nit, String nombre, String direccion){
        Connection conn;
        Statement sta=null;
        ResultSet rs;
        String sql= "INSERT INTO cliente VALUES('"+nit+"', '"+nombre+"', '"+direccion+"')";
               
        try{
            Class.forName(this.driver);
            conn = DriverManager.getConnection(url,uss,contra);
            sta=conn.createStatement();
            sta.executeUpdate(sql);
            conn.close();
        }catch(ClassNotFoundException | SQLException e){
        }        
    }
    public void NoFactura(String vendedor, String precio, String cliente){
        int factura=0;
        Connection conn;
        Statement sta=null;
        ResultSet rs;
        String sql= "INSERT INTO factura VALUES( 0, "+precio+", '"+vendedor+"', '"+cliente+"')";
        try{
            Class.forName(this.driver);
            conn = DriverManager.getConnection(url,uss,contra);
            sta=conn.createStatement();
            sta.executeUpdate(sql);
            
            conn.close();
        }catch(ClassNotFoundException | SQLException e){
        }
    }
    
    public int BuscarFactura(){
        Connection conn;
        PreparedStatement pst;
        ResultSet rs;
        int cont=0;
        String sql= "select * from factura";// and contraseña='"+contras+"'";
        try{
            Class.forName(this.driver);
            conn = DriverManager.getConnection(url,uss,contra);
            pst=conn.prepareStatement(sql);
            
            rs=pst.executeQuery();
            
            while(rs.next()){
               cont=rs.getInt(1);
            }
            conn.close();
        }catch(ClassNotFoundException | SQLException e){
            
        }
        return cont;    
    }
}
