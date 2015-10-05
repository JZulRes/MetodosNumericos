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
/**
 *
 * @author SKP
 */
public class GeneradorTablas {

    public JTable tablaBusquedas(ArrayList<BigDecimal> busquedax, ArrayList<BigDecimal> busquedaFx){
        
        String col[]={"n", "x", "fx"};
        DefaultTableModel modelo = new DefaultTableModel(col,0);
        for(int i = 0; i<busquedax.size();i++){
            Object[] fila = {i,busquedax.get(i),busquedaFx.get(i)};
            modelo.addRow(fila);
            //modelo.addRow(new Object[]{i,busquedax.get(i),busquedaFx.get(i)});
            
        }
        JTable tabla = new JTable(modelo); 
        return tabla;
    }
    
    public JTable tablaBiseccion(ArrayList<BigDecimal> biseccionXi, ArrayList<BigDecimal> biseccionXs,
                                ArrayList<BigDecimal> biseccionXm, ArrayList<BigDecimal> biseccionFxm,
                                ArrayList<BigDecimal> biseccionEa, ArrayList<BigDecimal> biseccionEr){
        
        String col[]={"n", "xi", "xs", "xm", "fxm", "Error absoluto", "Error relativo"};
        DefaultTableModel modelo = new DefaultTableModel(col,0);
        for(int i = 0; i<biseccionXi.size();i++){
            Object[] fila = {i,biseccionXi.get(i),biseccionXs.get(i),biseccionXm.get(i),
                            biseccionFxm.get(i), biseccionEa.get(i), biseccionEr.get(i)};
            modelo.addRow(fila);
        }
        JTable tabla = new JTable(modelo); 
        return tabla;
    }
    
    
}
