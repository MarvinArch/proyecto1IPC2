/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procesos;

import Objetos.muebleEnsamblado;
import Objetos.pieza;
import java.util.ArrayList;

/**
 *
 * @author alpha
 */
public class CrearInfoEnsamble {
    
    private ArrayList<pieza> tipoPiezas=new ArrayList<pieza>();
    private ArrayList<pieza> piezaInventario=new ArrayList<pieza>();
    private ArrayList<String> codigo=new ArrayList<String>();
    private ArrayList<muebleEnsamblado> muebleInventario;
    private ArrayList<String> Listamuebles=new ArrayList<String>();
    private consultas cons = new consultas();
    
    
    public ArrayList<String> infoMueble(String mueble){
        ArrayList<pieza> piezasImprimir=new ArrayList<pieza>();
        ArrayList<String> lineaTexto=new ArrayList<String>();
        lineaTexto.clear();
        piezasImprimir.clear();
        codigo.clear();
        tipoPiezas.clear();
        piezaInventario.clear();
        cons.TipoPieza("tipomueble");
        muebleInventario=cons.getMuebleInventario();
        double precio=0;
        cons.CantidadPieza(mueble);
        tipoPiezas=cons.getTipoPiezas();
        int noPieza=tipoPiezas.size();
        int totalPiezas=0;
        int contadorPieza=0;
        double precioensamble=0;
        cons.Pieza();
        piezaInventario=cons.getPiezaInventario();
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

    public ArrayList<String> TipoMueble(String tabla) {
        cons.TipoPieza(tabla);
        Listamuebles=cons.getTipoMueble();
        return Listamuebles;
    }

    public ArrayList<muebleEnsamblado> getMuebleInventario() {
        return muebleInventario;
    }

    public ArrayList<String> getCodigo() {
        return codigo;
    }
    
}
