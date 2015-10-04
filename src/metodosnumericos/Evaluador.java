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
      
   
    public void Evaluador1(String f) throws Exception{
         try{
        Expression e = new ExpressionBuilder(f)
        .variables("x")
        .build()
        .setVariable("x", 1);
        double result = e.evaluate();
      
        System.out.println(result);
        
        }catch(Exception e){
            System.out.println("Expresion Vacia");
        }
        
        
    }
    
    public double Evaluador2(String f, double x){
        Expression e = new ExpressionBuilder(f)
        .variables("x")
        .build()
        .setVariable("x", x);
        double result = e.evaluate();
        return result;
    }
    
}



