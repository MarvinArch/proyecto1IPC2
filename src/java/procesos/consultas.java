/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procesos;

import Objetos.mueble;
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
    private final ArrayList<String> codigo=new ArrayList<String>();
    private final ArrayList<mueble> muebleInventario=new ArrayList<mueble>();
    private String[] informacion;
    private mueble mueble;
    
    public consultas() {
        this.driver = "com.mysql.jdbc.Driver";
        this.url = "jdbc:mysql://localhost:3306/proyecto1";
        this.uss = "alpha23";
        this.contra = "f3fxbv12";
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
    
    /**
    *Elimina una fila de la base de datos recibe como paramtro el nombre de la tabla en donde debe buscar 
    */
    public void EliminarPieza(String codigo, String tabla, String columna){
        Connection conn;
        Statement sta=null;
        ResultSet rs;
        String sql= "delete from "+tabla+" where "+columna+"='"+codigo+"'";
               
        try{
            Class.forName(this.driver);
            conn = DriverManager.getConnection(url,uss,contra);
            sta=conn.createStatement();
            sta.executeUpdate(sql);
            conn.close();
        }catch(ClassNotFoundException | SQLException e){
        }        
    }
    
    /**
    *Crea un pieza nueva en la base de datos
    */
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
    
    /**
    *Agrega un mueble a la base de datos 
    */
    public boolean CrearMueble(String usuario, String fecha, String mueble, float costo){
        boolean exito;
        Connection conn;
        Statement sta=null;
        ResultSet rs;
        String sql= "INSERT INTO mueble_ensamblado VALUES(0,'"+usuario+"', '"+fecha+"', '"+mueble+"', "+costo+")";
               
        try{
            Class.forName(this.driver);
            conn = DriverManager.getConnection(url,uss,contra);
            sta=conn.createStatement();
            sta.executeUpdate(sql);
            conn.close();
            exito=true;
        }catch(ClassNotFoundException | SQLException e){
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
                if (tabla.equals("tipomueble")) {
                    muebleInventario.add(new mueble(rs.getString(1), rs.getFloat(2), 0));
                }
            }
            conn.close();
        }catch(ClassNotFoundException | SQLException e){
            
        }        
    }
    
    
    /**
    * genera un arreglo de cantidad de piezas necesaria para ensamblar mueble
    */
    public void CantidadPieza(String mueble){
        Connection conn;
        PreparedStatement pst;
        ResultSet rs;
        int cont=0;
        String sql= "select * from pieza where mueble='"+mueble+"'";
        tipoMueble.clear();
        try{
            Class.forName(this.driver);
            conn = DriverManager.getConnection(url,uss,contra);
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                tipoPiezas.add(new pieza(rs.getString(1),rs.getInt(4)));
            }
            conn.close();
        }catch(ClassNotFoundException | SQLException e){
            
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
    
    /**
    * Crea un arreglo de los tipos de piezas y las cantidades existentes en la base de datos
    */
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
    }
    /**
    * Genera un arreglo con la informacin completa de los muebles ensamblados en area 1
    * devolviendo un arreglo con todos los datos de los muebles en forma de String para ser imprimido en el jsp que lo llame
    */
    public ArrayList<String> infomueble(String mueble){
        ArrayList<pieza> piezasImprimir=new ArrayList<pieza>();
        ArrayList<String> lineaTexto=new ArrayList<String>();
        lineaTexto.clear();
        piezasImprimir.clear();
        codigo.clear();
        tipoPiezas.clear();
        piezaInventario.clear();
        double precio=0;
        CantidadPieza(mueble);
        int noPieza=tipoPiezas.size();
        int totalPiezas=0;
        int contadorPieza=0;
        double precioensamble=0;
        Pieza();
        lineaTexto.add("El mueble "+mueble);
        lineaTexto.add("Para su ensamble necesita de "+noPieza+" piezas <br>");
        //Busca las piezas y su informacion
        for (int i = 0; i < noPieza; i++) {
            lineaTexto.add(tipoPiezas.get(i).getCantidad()+" "+tipoPiezas.get(i).getNombre()+" ");
            totalPiezas+=tipoPiezas.get(i).getCantidad();
            int contador=-1;
            int contador2=0;
            int limite = tipoPiezas.get(i).getCantidad();
            for (int j = 0; j < piezaInventario.size(); j++) {
                if (contador2<limite) {
                    if (piezaInventario.get(j).getNombre().equals(tipoPiezas.get(i).getNombre())) {
                        if (precio!=piezaInventario.get(j).getPrecio()) {
                            piezasImprimir.add(new pieza(piezaInventario.get(j).getNombre(),piezaInventario.get(j).getCodigo(), piezaInventario.get(j).getPrecio(),1));
                            contador++;
                            precio=piezasImprimir.get(contador).getPrecio();
                            
                            codigo.add(piezaInventario.get(j).getCodigo());
                        }else if (precio==piezaInventario.get(j).getPrecio()) {
                            piezasImprimir.get(contador).setCantidad(piezasImprimir.get(contador).getCantidad()+1);
                            codigo.add(piezaInventario.get(j).getCodigo());
                        }
                        
                        contador2++;
                        contadorPieza++;
                    }
                }else{
                    j=piezaInventario.size()+1;
                }
            }
            
        }
        lineaTexto.add(";<br>");
        if (contadorPieza==totalPiezas) {
            for (int i = 0; i < piezasImprimir.size(); i++) {
                lineaTexto.add("&#10143; "+piezasImprimir.get(i).getCantidad()+" "+piezasImprimir.get(i).getNombre()+" con valor de Q."+piezasImprimir.get(i).getPrecio()+" <br>");
                precioensamble+=(piezasImprimir.get(i).getPrecio()*piezasImprimir.get(i).getCantidad());
            }
            for (int i = 0; i < muebleInventario.size(); i++) {
                if (muebleInventario.get(i).getNombre().equals(mueble)) {
                    muebleInventario.get(i).setPrecioEnsamble((float)precioensamble);
                }
            }
        }else{
            lineaTexto.add("&#9888; La cantidad de piezas en inventario para este mueble no es suficiente <br>");
        }
        return lineaTexto;
    }
    
    
    public void InfoMueble(String codigo){
        Connection conn;
        PreparedStatement pst;
        ResultSet rs;
        int cont=0;
        String sql= "select * from mueble_ensamblado, tipomueble where nombre_mueble=nombremueble;";
        try{
            Class.forName(this.driver);
            conn = DriverManager.getConnection(url,uss,contra);
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                muebleInventario.add(new mueble(rs.getString(1),rs.getString(4),rs.getString(2),rs.getFloat(7),rs.getFloat(5), rs.getDate(3)));
            }
            if (!codigo.equals("not")) {
                for (int i = 0; i < muebleInventario.size(); i++) {
                    if (muebleInventario.get(i).getIdentificador().equals(codigo)) {
                        mueble = muebleInventario.get(i);
                    }
                }
            }
            conn.close();
        }catch(ClassNotFoundException | SQLException e){
            
        }    
    }
    
    public void RegistrarVenta(ArrayList<mueble> vendidos, String cliente, String vendedor, String fecha, int factura){
        Connection conn;
        Statement sta=null;
        ResultSet rs;
        try{
            Class.forName(this.driver);
            conn = DriverManager.getConnection(url,uss,contra);
            sta=conn.createStatement();
            for (int i = 0; i < vendidos.size(); i++) {
                int identificador=Integer.parseInt(vendidos.get(i).getIdentificador());
                String sql= "INSERT INTO mueble_vendido  VALUES("+identificador+", '"+vendedor+"', '"+fecha+"', '"
                        +vendidos.get(i).getNombre()+"', "+vendidos.get(i).getPrecioEnsamble()+", "
                        +vendidos.get(i).getPrecioVenta()+", '"+cliente+"', "+factura+")";
                sta.executeUpdate(sql);
            }
            
            conn.close();
        }catch(ClassNotFoundException | SQLException e){
        }
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
    public ArrayList<mueble> getMuebleInventario() {
        return muebleInventario;
    }

    public mueble getMueble() {
        return mueble;
    }
    
}
