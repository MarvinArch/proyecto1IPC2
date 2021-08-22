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
    
}
