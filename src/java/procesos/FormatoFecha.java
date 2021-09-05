/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procesos;

import java.util.StringTokenizer;

/**
 *
 * @author alpha
 */
public class FormatoFecha {
    
    public static String CambiarFormatoFecha(String fecha){
        String ordenado;
        StringTokenizer tokens=new StringTokenizer(fecha,"-");
        String año=tokens.nextToken();
        String mes=tokens.nextToken();
        String dia=tokens.nextToken();
        ordenado=dia+"-"+mes+"-"+año;

        return ordenado;
    }
}
