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
    private final ArrayList<String> tipoMueble=new ArrayList<String>();
    private String[] informacion;
    
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
    
    public void AgregarPieza(String nombre, int minimo, String mueble){
        Connection conn;
        Statement sta=null;
        ResultSet rs;
        String sql= "INSERT INTO pieza VALUES('"+nombre+"', "+minimo+", '"+mueble+"')";
               
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
    //reutilizar este codigo para crear los arreglos de pieza y mueble para poder reutilizar codigo
    public void TipoPieza(String tabla){
        Connection conn;
        PreparedStatement pst;
        ResultSet rs;
        int cont=0;
        String sql= "select * from "+tabla;
        tipoMueble.clear();
        try{
            Class.forName(this.driver);
            conn = DriverManager.getConnection(url,uss,contra);
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                tipoMueble.add(rs.getString(1));
            }
            conn.close();
        }catch(ClassNotFoundException | SQLException e){
            
        }
        int tamaño=tipoMueble.size();
        informacion= new String[tamaño];
        
    }
    

    public ArrayList<String> getTipoMueble() {
        return tipoMueble;
    }
    
    public void AgregarInventarioPieza(String nombre, float precio, int cantidad){
        Connection conn;
        Statement sta=null;
        ResultSet rs;
        String sql= "INSERT INTO mprima VALUES('"+nombre+"', 0, '"+precio+"')";
               
        try{
            Class.forName(this.driver);
            conn = DriverManager.getConnection(url,uss,contra);
            sta=conn.createStatement();
            for (int i = 0; i < cantidad; i++) {
                sta.executeUpdate(sql);
            }
            conn.close();
            
        }catch(ClassNotFoundException | SQLException e){
        }        
    }
    
    public void ModificarPieza(String nombre, String precio, String codigo){
        Connection conn;
        Statement sta=null;
        ResultSet rs;
        String sql= "UPDATE mprima SET tipo='"+nombre+"' WHERE codigo='"+codigo+"'";
        String sql2= "UPDATE mprima SET precio='"+precio+"' WHERE codigo='"+codigo+"'";
        try{
            Class.forName(this.driver);
            conn = DriverManager.getConnection(url,uss,contra);
            sta=conn.createStatement();
            sta.executeUpdate(sql);
            sta.executeUpdate(sql2);
            conn.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("esa madre fallo");
        }        
    }

    public String[] getInformacion() {
        return informacion;
    }
    
    public void InforPieza(){
        Connection conn;
        PreparedStatement pst;
        ResultSet rs;
        int cont=0;
        String sql= "select tipo, mueble, minimo from mprima , pieza where tipo=nombre";
        tipoMueble.clear();
        try{
            String tipo= "xx";
            int posicion=-1;
            Class.forName(this.driver);
            conn = DriverManager.getConnection(url,uss,contra);
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                if (!rs.getString(1).equals(tipo)) {
                    piezaInventario.add(new pieza(rs.getString(1),rs.getString(2),rs.getInt(3),1));
                    tipo = rs.getString(1);
                    posicion++;
                }else if (rs.getString(1).equals(tipo)) {
                    piezaInventario.get(posicion).setCantidad(piezaInventario.get(posicion).getCantidad()+1);
                }
            }
            conn.close();
        }catch(ClassNotFoundException | SQLException e){
            
        }
        int tamaño=tipoMueble.size();
        informacion= new String[tamaño];
        
    }
    
}
