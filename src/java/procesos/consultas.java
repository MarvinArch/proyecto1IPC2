/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procesos;

import Objetos.MuebleVendido;
import Objetos.mueble;
import Objetos.muebleEnsamblado;
import Objetos.pieza;
import config.ConnectionsJDBC;
import java.sql.Connection;
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
    private Connection conn;
    private final ArrayList<pieza> piezaInventario=new ArrayList<pieza>();
    private final ArrayList<pieza> tipoPiezas=new ArrayList<pieza>();
    private final ArrayList<String> tipoMueble=new ArrayList<String>();
    private final ArrayList<String> codigo=new ArrayList<String>();
    private final ArrayList<muebleEnsamblado> muebleInventario=new ArrayList<muebleEnsamblado>();
    private String[] informacion;
    private mueble mueble;
    
    public consultas() {
        
    }
    public ArrayList<String> getCodigo() {
        return codigo;
    }
    /**
    *Genera un arreglo de todas las piezas existentes en la base de datos 
    * la informacion incluye nombre mueble codigo y precio    
    */
    public void Pieza(){
        Connection conn;
        PreparedStatement pst;
        ResultSet rs;
        int cont=0;
        String sql= "select * from mprima";
        try{
//            Class.forName(this.driver);
//            conn = DriverManager.getConnection(url,uss,contra);
            conn=ConnectionsJDBC.getConnection();
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                piezaInventario.add(new pieza(rs.getString(1),rs.getString(2),rs.getDouble(3)));
            }
            conn.close();
        }catch(SQLException e){
            
        }
        
    }
    
    /**
    *Elimina una fila de la base de datos recibe como parametro el nombre de la tabla en donde debe buscar 
    * Codigo de objeto a eliminar y columna
    */
    public void EliminarPieza(String codigo, String tabla, String columna){
        Connection conn;
        Statement sta=null;
        ResultSet rs;
        String sql= "delete from "+tabla+" where "+columna+"='"+codigo+"'";
               
        try{
//            Class.forName(this.driver);
//            conn = DriverManager.getConnection(url,uss,contra);
            conn=ConnectionsJDBC.getConnection();
            sta=conn.createStatement();
            sta.executeUpdate(sql);
            conn.close();
        }catch(SQLException e){
        }        
    }
    
    /**
    *Crea un pieza nueva en la base de datos 
    * parte de la funcion incertar piezas
    */
    public void AgregarPieza(String nombre, int minimo, String mueble, int necesario){
        Connection conn;
        Statement sta=null;
        ResultSet rs;
        String sql= "INSERT INTO pieza VALUES('"+nombre+"', "+minimo+", '"+mueble+"', "+necesario+")";
               
        try{
            conn = ConnectionsJDBC.getConnection();
            sta=conn.createStatement();
            sta.executeUpdate(sql);
            conn.close();
        }catch(SQLException e){
        }        
    }
    /**
    *Crea un mueble nueva en la base de datos 
    * parte de la funcion incertar piezas
    */
    public void AgregarMueble(String nombre, float Precio){
        Connection conn;
        Statement sta=null;
        ResultSet rs;
        String sql= "INSERT INTO tipomueble VALUES('"+nombre+"', "+Precio+")";
               
        try{
            conn = ConnectionsJDBC.getConnection();
            sta=conn.createStatement();
            sta.executeUpdate(sql);
            conn.close();
        }catch(SQLException e){
        }        
    }
    
    /**
    *Agrega un mueble a la base de datos luego de ser ensamblado por el usuario 
    */
    public boolean CrearMueble(String usuario, String fecha, String mueble, float costo){
        boolean exito;
        Connection conn;
        Statement sta=null;
        ResultSet rs;
        String sql= "INSERT INTO mueble_ensamblado VALUES(0,'"+usuario+"', '"+fecha+"', '"+mueble+"', "+costo+")";
               
        try{
            conn = ConnectionsJDBC.getConnection();
            sta=conn.createStatement();
            sta.executeUpdate(sql);
            conn.close();
            exito=true;
        }catch(SQLException e){
            exito=false;
        }
        return exito;
    }
    
    /**
    * Genera un arreglo de String
    * Si el parametro recibido es pieza el arreglo sera de nombres de los tipos de piezas que existan
    * En cambio si el parametro es tipomueble creara el arreglo de nombres de los muebles existentes
    */
    public void TipoPieza(String tabla){
        PreparedStatement pst;
        ResultSet rs;
        int cont=0;
        String sql= "select * from "+tabla;
        tipoMueble.clear();
        try{
            pst=consul(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                tipoMueble.add(rs.getString(1));
                if (tabla.equals("tipomueble")) {
                    muebleInventario.add(new muebleEnsamblado(rs.getString(1), rs.getFloat(2), 0));
                }
            }
            conn.close();
        }catch(SQLException e){
            
        }        
    }
    
    
    /**
    * genera un arreglo de cantidad de piezas necesaria para ensamblar mueble
    */
    public void CantidadPieza(String mueble){
       PreparedStatement pst;
        ResultSet rs;
        int cont=0;
        String sql= "select * from pieza where mueble='"+mueble+"'";
        tipoMueble.clear();
        try{
            pst=consul(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                tipoPiezas.add(new pieza(rs.getString(1),rs.getInt(4)));
            }
            conn.close();
        }catch(SQLException e){
            
        }
        
    }
    
    /**
    * Agrega una pieza a la base de datos recibe de parametro el nombre de la pieza, el precio y la cantidad que se va agregar
    */
    public void AgregarInventarioPieza(String nombre, float precio, int cantidad){
        Connection conn;
        Statement sta=null;
        ResultSet rs;
        String sql= "INSERT INTO mprima VALUES('"+nombre+"', 0, '"+precio+"')";
               
        try{
            conn = ConnectionsJDBC.getConnection();
            sta=conn.createStatement();
            for (int i = 0; i < cantidad; i++) {
                sta.executeUpdate(sql);
            }
            conn.close();
        }catch(SQLException e){
        }        
    }
    
    /**
    *Cambia el nombre y el precio de una pieza
    */
    public void ModificarPieza(String nombre, String precio, String codigo){
        Connection conn;
        Statement sta=null;
        ResultSet rs;
        String sql= "UPDATE mprima SET tipo='"+nombre+"' WHERE codigo='"+codigo+"'";
        String sql2= "UPDATE mprima SET precio='"+precio+"' WHERE codigo='"+codigo+"'";
        try{
            conn = ConnectionsJDBC.getConnection();
            sta=conn.createStatement();
            sta.executeUpdate(sql);
            sta.executeUpdate(sql2);
            conn.close();
        }catch(SQLException e){
            System.out.println("esa madre fallo");
        }        
    }
    
    /**
    * Crea un arreglo de los tipos de piezas y las cantidades existentes en la base de datos
    */
    public void InforPieza(){
        PreparedStatement pst;
        ResultSet rs;
        int cont=0;
        String sql= "select tipo, mueble, minimo from mprima , pieza where tipo=nombre";
        tipoMueble.clear();
        try{
            String tipo= "xx";
            int posicion=-1;
            pst= consul(sql);
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
        }catch(SQLException e){
            
        }
    }
    /**
    * Crea un arreglo de muebles ensamblados y lo almacena dentro del arrayList "muebleInventario" si recibe not
    * si recibe un texto distinto de not devera recivir el codigo de un mueble a buscar
    * y luego devuelve un objeto del tipo mueble con los datos del mueble buscado
    */
    public void InfoMueble(String codigo){
        PreparedStatement pst;
        ResultSet rs;
        int cont=0;
        String sql= "select * from mueble_ensamblado, tipomueble where nombre_mueble=nombremueble;";
        try{
           pst=consul(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                muebleInventario.add(new muebleEnsamblado(rs.getString(1),rs.getString(4),rs.getString(2),rs.getFloat(7),rs.getFloat(5), rs.getDate(3)));
            }
            if (!codigo.equals("not")) {
                for (int i = 0; i < muebleInventario.size(); i++) {
                    if (muebleInventario.get(i).getIdentificador().equals(codigo)) {
                        mueble = muebleInventario.get(i);
                    }
                }
            }
            conn.close();
        }catch(SQLException e){
            
        }    
    }
    /**
    * Recibe el arreglo de muebles que desea comprar el cliente 
    * Registra cada mueble en la BD de muebles vendidos
    * Borra de BD de muebles ensamblados
    */
    public void RegistrarVenta(ArrayList<mueble> vendidos, String cliente, String vendedor, String fecha, int factura){
        Connection conn;
        Statement sta=null;
        ResultSet rs;
        try{
            conn=ConnectionsJDBC.getConnection();
            sta=conn.createStatement();
            for (int i = 0; i < vendidos.size(); i++) {
                int identificador=Integer.parseInt(vendidos.get(i).getIdentificador());
                String sql= "INSERT INTO mueble_vendido  VALUES("+identificador+", '"+vendedor+"', '"+fecha+"', '"
                        +vendidos.get(i).getNombre()+"', "+vendidos.get(i).getPrecioEnsamble()+", "
                        +vendidos.get(i).getPrecioVenta()+", '"+cliente+"', "+factura+")";
                sta.executeUpdate(sql);
            }
            
            conn.close();
        }catch(SQLException e){
        }
    }
    /**
    * Crea y devulve un arreglo de muebles vendidos con todas sus variables
    */
    
    public ArrayList<MuebleVendido> ArregloVendido(){
        ArrayList<MuebleVendido> vendidos = new ArrayList<MuebleVendido>();
        PreparedStatement pst;
        ResultSet rs;
        int cont=0;
        String sql= "select * from mueble_vendido";
        try{
            pst=consul(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                vendidos.add(new MuebleVendido(rs.getString(1),rs.getString(2),rs.getDate(3),rs.getString(4),rs.getFloat(5),rs.getFloat(6),rs.getString(7),rs.getInt(8)));
            }
            conn.close();
        }catch(SQLException e){
            
        }
        return vendidos;
    }
    
    private PreparedStatement consul(String sql) throws SQLException{
        conn = ConnectionsJDBC.getConnection();
        PreparedStatement pst;
        return pst=conn.prepareStatement(sql);
    }
    
    public String[] getInformacion() {
        return informacion;
    }
    public ArrayList<String> getTipoMueble() {
        return tipoMueble;
    }
    public ArrayList<pieza> getPiezaInventario() {
        return piezaInventario;
    }

    public ArrayList<pieza> getTipoPiezas() {
        return tipoPiezas;
    }
    public ArrayList<muebleEnsamblado> getMuebleInventario() {
        return muebleInventario;
    }

    public mueble getMueble() {
        return mueble;
    }
    
}
