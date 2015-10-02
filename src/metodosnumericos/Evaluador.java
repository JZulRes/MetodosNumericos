/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package metodosnumericos;


import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

/**
 *
 * @author Juan Fernando Zuluaga <jzulua50@eafit.edu.co>
 * @author Daniel Arango Pelaez <darang24@eafit.edu.co>
 * 
 */

public class Evaluador {
      
    //String f;
    public void ObtenerValores(){
        VentanaUnaVariable ventanaunavariable = new VentanaUnaVariable();
       // f = ventanaunavariable.getEvaluador();
        
    } 
    public void Evaluador1() throws Exception{
        try{
            
        VentanaUnaVariable ventanaunavariable = new VentanaUnaVariable();
        String f = ventanaunavariable.getEvaluador();
        Expression e = new ExpressionBuilder(f)
        .variables("x")
        .build()
        .setVariable("x", 1);
        double result = e.evaluate();
      
        System.out.println(result);
        
        }catch(Exception e){
            System.out.println("No Importa");
        }
    }
    
}
