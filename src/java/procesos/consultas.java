/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procesos;

import Objetos.pieza;
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
public class consultas {
    private String driver;
    private String url;
    private String uss;
    private String contra;
    private final ArrayList<pieza> piezaInventario=new ArrayList<pieza>();
    private final ArrayList<pieza> tipoPiezas=new ArrayList<pieza>();
    
    public consultas() {
        this.driver = "com.mysql.jdbc.Driver";
        this.url = "jdbc:mysql://localhost:3306/proyecto1";
        this.uss = "alpha23";
        this.contra = "f3fxbv12";
    }
    
       
    public void Pieza(){
        Connection conn;
        PreparedStatement pst;
        ResultSet rs;
        int cont=0;
        String []area= new String[5];
        String sql= "select * from mprima";
        try{
            Class.forName(this.driver);
            conn = DriverManager.getConnection(url,uss,contra);
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                piezaInventario.add(new pieza(rs.getString(1),rs.getString(2),rs.getDouble(3)));
            }
            conn.close();
        }catch(ClassNotFoundException | SQLException e){
            
        }
        
    }
    
    public void EliminarPieza(String codigo){
        Connection conn;
        Statement sta=null;
        ResultSet rs;
        String []area= new String[5];
        String sql= "delete from mprima where codigo='"+codigo+"'";
               
        try{
            Class.forName(this.driver);
            conn = DriverManager.getConnection(url,uss,contra);
            sta=conn.createStatement();
            sta.executeUpdate(sql);
            conn.close();
        }catch(ClassNotFoundException | SQLException e){
        }        
    }

    public ArrayList<pieza> getPiezaInventario() {
        return piezaInventario;
    }
    
    public void AgregarPieza(String nombre){
        Connection conn;
        Statement sta=null;
        ResultSet rs;
        String []area= new String[5];
        String sql= "INSERT INTO pieza VALUES('"+nombre+"')";
               
        try{
            Class.forName(this.driver);
            conn = DriverManager.getConnection(url,uss,contra);
            sta=conn.createStatement();
            sta.executeUpdate(sql);
            conn.close();
        }catch(ClassNotFoundException | SQLException e){
        }        
    }

    public ArrayList<pieza> getTipoPiezas() {
        return tipoPiezas;
    }
    
    
}
