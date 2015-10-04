/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodosnumericos;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author SKP
 */
public class GeneradorTablas {

    public JTable tablaBusquedas(ArrayList<Double> busquedax, ArrayList<Double> busquedaFx){
        
        String col[]={"n", "x", "fx"};
        DefaultTableModel modelo = new DefaultTableModel(col,0);
        for(int i = 0; i<busquedax.size();i++){
            Object[] fila = {i,busquedax.get(i),busquedaFx.get(i)};
            //modelo.addRow(fila);
            modelo.addRow(new Object[]{i,busquedax.get(i),busquedaFx.get(i)});
        }
        JTable tabla = new JTable(modelo); 
        return tabla;
    }
    
}
