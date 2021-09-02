
import Objetos.mueble;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import procesos.consultas;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alpha
 */
public class CrearOrdenMuebles {
    
    ArrayList<mueble> mueble3= new ArrayList<mueble>();
    
    public void OrdenMuebles(int orden){
        consultas a1 = new consultas();
        a1.TipoPieza("tipomueble");
        
        ArrayList<String> mueble1= a1.getTipoMueble();
        a1.InfoMueble();
        ArrayList<mueble> muebleInventario=a1.getMuebleInventario();
        for (int i = 0; i < mueble1.size(); i++) {
            String mueble2=mueble1.get(i).toString();
            int contado=-1;
            mueble obj= new mueble();
            for (int j = 0; j < muebleInventario.size(); j++) {
                if (muebleInventario.get(j).getNombre().equals(mueble2)) {
                    contado++;
                    obj= muebleInventario.get(j);
                }
            }
            obj.setExistencia(contado);
            mueble3.add(obj);
        }
        if (orden==1) {
            Collections.sort(mueble3, new Comparator<mueble>() {
                @Override
                public int compare(mueble p1, mueble p2) {
                    return new Integer(p2.getExistencia()).compareTo(new Integer(p1.getExistencia()));
                }
                });
        }else{
            Collections.sort(mueble3, new Comparator<mueble>() {
                @Override
                public int compare(mueble p1, mueble p2) {
                    return new Integer(p1.getExistencia()).compareTo(new Integer(p2.getExistencia()));
                }
                });
        }
    }
}
