/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodosnumericos;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 *
 * @author SKP
 */
public class GeneradorTablas {

    DecimalFormat format = new DecimalFormat("#.##E0"); 
        
    public JTable tablaBusquedas(ArrayList<BigDecimal> busquedax, ArrayList<BigDecimal> busquedaFx){
        
        String col[]={"n", "x", "fx"};
        DefaultTableModel modelo = new DefaultTableModel(col,0);
        for(int i = 0; i<busquedax.size();i++){
            Object[] fila = {i,format.format(busquedax.get(i)),
                            format.format(busquedaFx.get(i))};
            modelo.addRow(fila);
            
            //modelo.addRow(new Object[]{i,busquedax.get(i),busquedaFx.get(i)});
            
        }
        JTable tabla = new JTable(modelo); 
        return tabla;
    }
    
    //Funciona para Biseccion y regla falsa
    public JTable tablaBiseccion(ArrayList<BigDecimal> biseccionXi, ArrayList<BigDecimal> biseccionXs,
                                ArrayList<BigDecimal> biseccionXm, ArrayList<BigDecimal> biseccionFxm,
                                ArrayList<BigDecimal> biseccionEa, ArrayList<BigDecimal> biseccionEr){
        
        String col[]={"n", "xi", "xs", "xm", "fxm", "Error absoluto", "Error relativo"};
        DefaultTableModel modelo = new DefaultTableModel(col,0);
        for(int i = 0; i<biseccionXi.size();i++){
            Object[] fila = {i,biseccionXi.get(i),biseccionXs.get(i),biseccionXm.get(i),
                            biseccionFxm.get(i), format.format(biseccionEa.get(i)), 
                            format.format(biseccionEr.get(i))};
            modelo.addRow(fila);
        }
        JTable tabla = new JTable(modelo); 
        return tabla;
    }
    
    //Funciona para punto fijo y secante
    public JTable tablaPuntoFijo(ArrayList<BigDecimal> puntoFijoXn, ArrayList<BigDecimal> puntoFijoFxn,
                                ArrayList<BigDecimal> puntoFijoEa, ArrayList<BigDecimal> puntoFijoEr){
        
        String col[]={"n", "Xn", "Fxn", "Error absoluto", "Error relativo"};
        DefaultTableModel modelo = new DefaultTableModel(col,0);
        for(int i = 0; i<puntoFijoXn.size();i++){
            Object[] fila = {i,puntoFijoXn.get(i),puntoFijoFxn.get(i),
                               format.format(puntoFijoEa.get(i)),
                               format.format(puntoFijoEr.get(i))};
            modelo.addRow(fila);
        }
        JTable tabla = new JTable(modelo); 
        return tabla;
    }
    
    public JTable tablaNewton(ArrayList<BigDecimal> newtonXn, ArrayList<BigDecimal> newtonFxn,
                              ArrayList<BigDecimal> newtonFdx, ArrayList<BigDecimal> newtonEa, 
                              ArrayList<BigDecimal> newtonEr){
        
        String col[]={"n", "Xn", "Fxn", "F'(xn)", "Error absoluto", "Error relativo"};
        DefaultTableModel modelo = new DefaultTableModel(col,0);
        for(int i = 0; i<newtonXn.size();i++){
            Object[] fila = {i,newtonXn.get(i),newtonFxn.get(i),
                               newtonFdx.get(i),format.format(newtonEa.get(i)),
                               format.format(newtonEr.get(i))};
            modelo.addRow(fila);
        }
        JTable tabla = new JTable(modelo); 
        return tabla;
    }
    
    public JTable tablaRaicesM(ArrayList<BigDecimal> rmXn, ArrayList<BigDecimal> rmFxn,
                               ArrayList<BigDecimal> rmFdx, ArrayList<BigDecimal> rmFddx, 
                               ArrayList<BigDecimal> rmEa, ArrayList<BigDecimal> rmEr){
        
        String col[]={"n", "Xn", "Fxn", "F'(xn)", "F''(xn)" ,"Error absoluto", "Error relativo"};
        DefaultTableModel modelo = new DefaultTableModel(col,0);
        for(int i = 0; i<rmXn.size();i++){
            Object[] fila = {i,rmXn.get(i),rmFxn.get(i), rmFdx.get(i), rmFddx.get(i),
                             format.format(rmEa.get(i)), format.format(rmEr.get(i))};
            modelo.addRow(fila);
        }
        JTable tabla = new JTable(modelo); 
        return tabla;
    }
    
}
