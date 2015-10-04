/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodosnumericos;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author SKP
 */
public class Graficador {
    
    public ChartPanel series(String f, double xi, double xs){
        
        Evaluador func = new Evaluador();
        XYSeries series = new XYSeries("Funcion");
        
        double iter = xi;
        while(iter<xs){
            double y = func.Evaluador2(f, iter);
            series.add(iter,y);
            System.out.println(iter+" "+y);
            iter=iter+0.2;
        }
        XYSeriesCollection collection = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart("Grafica", "X", "Y", collection);
        ChartPanel panel = new ChartPanel(chart);
        panel.setPreferredSize(new java.awt.Dimension(400,300));
        return panel;
    }
    
}
