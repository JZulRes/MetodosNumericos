/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodosnumericos;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
/**
 * @author Juan Fernando Zuluaga <jzulua50@eafit.edu.co>
 * @author Daniel Arango Pelaez <darang24@eafit.edu.co>
 */
public class Metodos {

    //Arraylist de todos los metodos:
    //ArrayList resultados busquedas incrementales
    private ArrayList<BigDecimal> busquedaFx = new ArrayList<BigDecimal>();  
    private ArrayList<BigDecimal> busquedax = new ArrayList<BigDecimal>();  
    
    //ArrayList resultados biseccion
    private ArrayList<BigDecimal> biseccionXi = new ArrayList<BigDecimal>();
    private ArrayList<BigDecimal> biseccionXs = new ArrayList<BigDecimal>();
    private ArrayList<BigDecimal> biseccionXm = new ArrayList<BigDecimal>();
    private ArrayList<BigDecimal> biseccionFxm = new ArrayList<BigDecimal>();
    private ArrayList<BigDecimal> biseccionEr = new ArrayList<BigDecimal>();
    private ArrayList<BigDecimal> biseccionEa = new ArrayList<BigDecimal>();

    //ArrayList resultados regla falsa
    private ArrayList<BigDecimal> reglaFalsaXi = new ArrayList<BigDecimal>();
    private ArrayList<BigDecimal> reglaFalsaXs = new ArrayList<BigDecimal>();
    private ArrayList<BigDecimal> reglaFalsaXm = new ArrayList<BigDecimal>();
    private ArrayList<BigDecimal> reglaFalsaFxm = new ArrayList<BigDecimal>();
    private ArrayList<BigDecimal> reglaFalsaEr = new ArrayList<BigDecimal>();
    private ArrayList<BigDecimal> reglaFalsaEa = new ArrayList<BigDecimal>();

    //ArrayList resultados Punto Fijo
    private ArrayList<BigDecimal> puntoFijoXn = new ArrayList<BigDecimal>();
    private ArrayList<BigDecimal> puntoFijoFxn = new ArrayList<BigDecimal>();
    private ArrayList<BigDecimal> puntoFijoEa = new ArrayList<BigDecimal>();
    private ArrayList<BigDecimal> puntoFijoEr = new ArrayList<BigDecimal>();
    
    //ArrayList resultados Newton
    private ArrayList<BigDecimal> newtonXn = new ArrayList<BigDecimal>();
    private ArrayList<BigDecimal> newtonFx = new ArrayList<BigDecimal>();
    private ArrayList<BigDecimal> newtonFdx = new ArrayList<BigDecimal>();
    private ArrayList<BigDecimal> newtonEa = new ArrayList<BigDecimal>();
    private ArrayList<BigDecimal> newtonEr = new ArrayList<BigDecimal>();
    
    //ArrayList resultados secante
    private ArrayList<BigDecimal> secanteXn = new ArrayList<BigDecimal>();
    private ArrayList<BigDecimal> secanteFxn = new ArrayList<BigDecimal>();
    private ArrayList<BigDecimal> secanteEa = new ArrayList<BigDecimal>();
    private ArrayList<BigDecimal> secanteEr = new ArrayList<BigDecimal>();
    
    //ArrayList resultados Raices Multiples
    private ArrayList<BigDecimal> raicesXn = new ArrayList<BigDecimal>();
    private ArrayList<BigDecimal> raicesFx = new ArrayList<BigDecimal>();
    private ArrayList<BigDecimal> raicesFdx = new ArrayList<BigDecimal>();
    private ArrayList<BigDecimal> raicesFddx = new ArrayList<BigDecimal>();
    private ArrayList<BigDecimal> raicesEa = new ArrayList<BigDecimal>();
    private ArrayList<BigDecimal> raicesEr = new ArrayList<BigDecimal>();
    
    
    private double funcion(String s, double x){
        Evaluador funcion = new Evaluador();
        return funcion.Evaluador2(s, x);
    }
    
    
    public String Busqueda(double x0, double delta, int iter, String f) {
	
        BigDecimal fxo = new BigDecimal(funcion(f,x0));
        BigDecimal x0b = new BigDecimal(x0);
        BigDecimal deltab = new BigDecimal(delta);
	busquedax.add(x0b);
        busquedaFx.add(fxo);
	if (x0 == 0) {
		return x0+" es raiz";
	}
	else {
                BigDecimal x1 = x0b.add(deltab);
		//double x1 = x0 + delta;
		int contador = 1;
		BigDecimal fx1 = new BigDecimal(funcion(f,x1.doubleValue()));
                //while ((fxo*fx1 > 0) && (contador < iter)) {
                while ((fxo.multiply(fx1).compareTo(BigDecimal.ZERO)>0) && (contador < iter)) {
                        busquedax.add(x1);
                        busquedaFx.add(fx1);
			x0b = x1;
			fxo = fx1;
			x1 = x0b.add(deltab);
			fx1 = new BigDecimal(funcion(f,x1.doubleValue()));
			contador++;
                }
                busquedax.add(x1);
                busquedaFx.add(fx1);
		if (fx1.equals(BigDecimal.ZERO)) {
			return x1+" es raiz";
		}
		else if(fxo.multiply(fx1).compareTo(BigDecimal.ZERO)<0){
			return "Hay una raiz entre "+x0b+" y "+x1;
		}
		else {
			return "Fracaso en "+iter+" iteraciones";
		}
	}
    }
            
    public String Biseccion(double xs1, double xi1, double tolerancia1, int iter,
                            String f, boolean errorAbs) {
        
	BigDecimal fxi = new BigDecimal(funcion(f,xi1));
	BigDecimal fxs = new BigDecimal(funcion(f,xs1));
	BigDecimal xs = new BigDecimal(xs1);
        BigDecimal xi = new BigDecimal(xi1);
        BigDecimal tolerancia = new BigDecimal(tolerancia1);
        if (fxi.equals(BigDecimal.ZERO)) {
                biseccionXi.add(xi);
                biseccionXs.add(xs);
                biseccionXm.add(BigDecimal.ZERO);
                biseccionFxm.add(BigDecimal.ZERO);
                biseccionEa.add(BigDecimal.ZERO);
                biseccionEr.add(BigDecimal.ZERO);
		return xi+" es raiz";
	}
	else if (fxs.equals(BigDecimal.ZERO)) {
                biseccionXi.add(xi);
                biseccionXs.add(xs);
                biseccionXm.add(BigDecimal.ZERO);
                biseccionFxm.add(BigDecimal.ZERO);
                biseccionEa.add(BigDecimal.ZERO);
                biseccionEr.add(BigDecimal.ZERO);
		return xs+" es raiz";
	}
	else if (fxi.multiply(fxs).compareTo(BigDecimal.ZERO) < 0) {
                BigDecimal xm = xi.add(xs);
                xm = xm.divide(new BigDecimal(2));   //xi + xs/2
                BigDecimal fxm = new BigDecimal(funcion(f,xm.floatValue()));
		int contador = 1;
		BigDecimal error = tolerancia.add(BigDecimal.ONE);
                biseccionXi.add(xi);
                biseccionXs.add(xs);
                biseccionXm.add(xm);
                biseccionFxm.add(fxm);
                biseccionEa.add(BigDecimal.ZERO);
                biseccionEr.add(BigDecimal.ZERO);
                
                while ((error.compareTo(tolerancia)>0) && (!fxm.equals(BigDecimal.ZERO)) && contador < iter) {
		    
                    if ((fxi.multiply(fxm)).compareTo(BigDecimal.ZERO) < 0) {
				xs = xm;
				fxs = fxm;
			}
			else {
				xi = xm;
				fxi = fxm;
			}
			BigDecimal xaux = xm;
			xm = (xi.add(xs)).divide(new BigDecimal(2));
			fxm = new BigDecimal(funcion(f,xm.doubleValue()));
			if(errorAbs){
                            error = xm.subtract(xaux).abs();
                        }else{
                            error = ((xm.subtract(xaux)).divide(xm,MathContext.DECIMAL128).abs());
                        }
			contador++;
                        biseccionXi.add(xi);
                        biseccionXs.add(xs);
                        biseccionXm.add(xm);
                        biseccionFxm.add(fxm);
                        biseccionEa.add(xm.subtract(xaux).abs());
                        biseccionEr.add(((xm.subtract(xaux)).divide(xm,MathContext.DECIMAL128)).abs());
                      //  biseccionEr.add(BigDecimal.ZERO);
                }
		if (fxm.equals(BigDecimal.ZERO)) {
			return xm+" es raiz";
		}
		else if ( error.compareTo(tolerancia) < 0) {
			return xm+" es una aproximacion a una raiz con una tolerancia = "+tolerancia;
		}
		else {
			return "Fracaso en "+iter+" iteraciones";
		}
	}
	else {
            return "El intervalo es inadecuado";
	}
}
    
    public String ReglaFalsa(double xs1, double xi1, double tolerancia1, int iter,
                            String f, boolean errorAbs) {
	BigDecimal xs = new BigDecimal(xs1);
        BigDecimal xi = new BigDecimal(xi1);
        BigDecimal tolerancia = new BigDecimal(tolerancia1);       
        BigDecimal fxi = new BigDecimal(funcion(f,xi.doubleValue()));
	BigDecimal fxs = new BigDecimal(funcion(f,xs.doubleValue()));
	if (fxi.equals(BigDecimal.ZERO)) {
		reglaFalsaXi.add(xi);
                reglaFalsaXs.add(xs);
                reglaFalsaXm.add(BigDecimal.ZERO);
                reglaFalsaFxm.add(BigDecimal.ZERO);
                reglaFalsaEa.add(BigDecimal.ZERO);
                reglaFalsaEr.add(BigDecimal.ZERO);
                return xi+" es raiz";
	}
	else if (fxs.equals(BigDecimal.ZERO)) {
                reglaFalsaXi.add(xi);
                reglaFalsaXs.add(xs);
                reglaFalsaXm.add(BigDecimal.ZERO);
                reglaFalsaFxm.add(BigDecimal.ZERO);
                reglaFalsaEa.add(BigDecimal.ZERO);
                reglaFalsaEr.add(BigDecimal.ZERO);
		return xs+" es raiz";
	}
        else if ((fxi.multiply(fxs)).compareTo(BigDecimal.ZERO) < 0) {
                
		BigDecimal xm = xi.subtract((fxi.multiply(xs.subtract(xi))).divide((fxs.subtract(fxi)),MathContext.DECIMAL128));
		
                BigDecimal fxm = new BigDecimal(funcion(f,xm.doubleValue()));
		int contador = 1;
		BigDecimal error = tolerancia.add(BigDecimal.ONE);
                reglaFalsaXi.add(xi);
                reglaFalsaXs.add(xs);
                reglaFalsaXm.add(xm);
                reglaFalsaFxm.add(fxm);
                reglaFalsaEa.add(BigDecimal.ZERO);
                reglaFalsaEr.add(BigDecimal.ZERO);
		while ((error.compareTo(tolerancia) > 0) && (!fxm.equals(0)) 
                        && contador < iter) {
			
                        if ((fxi.multiply(fxm)).compareTo(BigDecimal.ZERO) < 0) {
				xs = xm;
				fxs = fxm;
			}
			else {
				xi = xm;
				fxi = fxm;
			}
			BigDecimal xaux = xm;
			//xm = xi - ((fxi*(xs - xi)) / (fxs - fxi));
			xm = xi.subtract((fxi.multiply(xs.subtract(xi))).divide((fxs.subtract(fxi)),MathContext.DECIMAL128));
                        
                        fxm = new BigDecimal(funcion(f,xm.doubleValue()));
			if(errorAbs){
                            error = xm.subtract(xaux).abs();
                        }else{
                            error = ((xm.subtract(xaux)).divide(xm,MathContext.DECIMAL128).abs());
                        }
                        reglaFalsaXi.add(xi);
                        reglaFalsaXs.add(xs);
                        reglaFalsaXm.add(xm);
                        reglaFalsaFxm.add(fxm);
                        reglaFalsaEa.add(xm.subtract(xaux).abs());
                        reglaFalsaEr.add((xm.subtract(xaux)).divide(xm,MathContext.DECIMAL128).abs());
                        contador++;
		}
		if (fxm.equals(BigDecimal.ZERO)) {
			return xm+" es raiz";
		}
		else if (error.compareTo(tolerancia) < 0) {
			return xm+" es una aproximacion a una raiz con una tolerancia = "+tolerancia;
		}
		else {
			return "Fracaso en iter iteraciones";
		}
	}
	else {
		return "El intervalo es inadecuado";
	}
}
    
    public String PuntoFijo(double tolerancia1, double xa1, int iter, String f,
                            String g, boolean errorAbs) {
	
        BigDecimal tolerancia = new BigDecimal(tolerancia1);
        BigDecimal xa = new BigDecimal(xa1);
        BigDecimal fx = new BigDecimal(funcion(f,xa.longValue()));
	int contador = 0;
	BigDecimal error = tolerancia.add(BigDecimal.ONE);
        puntoFijoXn.add(xa);
        puntoFijoFxn.add(fx);
        puntoFijoEa.add(BigDecimal.ZERO);
        puntoFijoEr.add(BigDecimal.ZERO);
        
	while ((!fx.equals(BigDecimal.ZERO)) && (error.compareTo(tolerancia) > 0)
                && contador < iter) {
		
                BigDecimal xn = new BigDecimal(funcion(g,xa.doubleValue()));
		fx = new BigDecimal(funcion(f,xn.doubleValue()));
		if(errorAbs){
                    error = (xn.subtract(xa)).abs();
                }else{
                    error = ((xn.subtract(xa)).divide(xn,MathContext.DECIMAL128)).abs();
                }
                puntoFijoXn.add(xn);
                puntoFijoFxn.add(fx);
                puntoFijoEa.add((xn.subtract(xa)).abs());
                puntoFijoEr.add(((xn.subtract(xa)).divide(xn,MathContext.DECIMAL128)).abs());
		xa = xn;
		contador++;
	}
	if (fx.equals(BigDecimal.ZERO)) {
		return xa+" es raiz";
	}
	else if (error.compareTo(tolerancia) < 0) {
		return xa+" es una aproximacion con una tolerancia = "+tolerancia;
	}
	else {
		return "El metodo fracaso en "+iter+"iteraciones";
	}
}
    
    public String Newton(double tolerancia1, double x01, int iter, String f,
                        String df, boolean errorAbs){
        BigDecimal x0 = new BigDecimal(x01);
        BigDecimal tolerancia = new BigDecimal(tolerancia1);
        
        BigDecimal x1 = BigDecimal.ZERO;
        BigDecimal fx = new BigDecimal(funcion(f,x0.doubleValue()));//F(x1)
	BigDecimal dfx = new BigDecimal(funcion(df,x0.doubleValue()));//F'(x1)
	int contador = 0;
	BigDecimal error = tolerancia.add(BigDecimal.ONE);
        newtonXn.add(x0);
        newtonFx.add(fx);
        newtonFdx.add(dfx);
        newtonEa.add(BigDecimal.ZERO);
        newtonEr.add(BigDecimal.ZERO);
        
	while ((error.compareTo(tolerancia) > 0) && (!fx.equals(BigDecimal.ZERO)) 
                && (!dfx.equals(BigDecimal.ZERO)) && (contador < iter)) {
                System.out.println(x0);
                System.out.println(fx);
                System.out.println(dfx);
                x1 = x0.subtract((fx.divide(dfx,MathContext.DECIMAL128))); 
		fx = new BigDecimal(funcion(f,x1.doubleValue()));//F(x1)
		dfx = new BigDecimal(funcion(df,x1.doubleValue()));//F'(x1)
                System.out.println("x1 "+x1);
                System.out.println("fx "+fx);
                System.out.println("dfx "+dfx);
                if(errorAbs){
                    error = (x1.subtract(x0)).abs();
                }else{
                    error = ((x1.subtract(x0)).divide(x1,MathContext.DECIMAL128)).abs();
                }
                newtonXn.add(x0);
                newtonFx.add(fx);
                newtonFdx.add(dfx);
                newtonEa.add((x1.subtract(x0)).abs());
                newtonEr.add(((x1.subtract(x0)).divide(x1,MathContext.DECIMAL128)).abs());
                x0 = x1;
		contador++;
        }
	if (fx.equals(BigDecimal.ZERO)) {
		return x0+" es raiz";
	}
	else if (error.compareTo(tolerancia)<0) {
		return x1+" es una aproximacion a una raiz con una tolerancia = "+tolerancia;
	}
	else if (dfx.equals(BigDecimal.ZERO)) {
		return x1+" es una posible raiz multiple";
	}
	else {
		return "Fracaso en "+iter+" iteraciones";
	}

    }


    public String MetodoDeLaSecante(double tolerancia1, double x01, double x11,
                                    int iter, String f, boolean errorAbs) {
	BigDecimal tolerancia = new BigDecimal(tolerancia1);
        BigDecimal x0 = new BigDecimal(x01);
        BigDecimal x1 = new BigDecimal(x11);
        BigDecimal fx0 = new BigDecimal(funcion(f,x0.doubleValue()));
        secanteXn.add(x0);
        secanteFxn.add(fx0);
        secanteEa.add(BigDecimal.ZERO);
        secanteEr.add(BigDecimal.ZERO);
                
        if (fx0.equals(BigDecimal.ZERO)) {
		return x0+" es raiz";
	}
	else {
		BigDecimal fx1 = new BigDecimal(funcion(f,x1.doubleValue()));
		int contador = 0;
		BigDecimal error = tolerancia.add(BigDecimal.ONE);
		BigDecimal den = fx1.subtract(fx0);
		secanteXn.add(x1);
                secanteFxn.add(fx1);
                secanteEa.add(BigDecimal.ZERO);
                secanteEr.add(BigDecimal.ZERO);
                
		while ((error.compareTo(tolerancia) > 0) && (!fx1.equals(BigDecimal.ZERO)) 
                        && (!den.equals(BigDecimal.ZERO)) && (contador < iter)) {
			
                        BigDecimal x2 = x1.subtract((fx1.multiply(x1.subtract(x0))).divide(den,MathContext.DECIMAL128));
                        //double x2 = x1 - (fx1 * (x1 - x0) / den);
			if(errorAbs){
                            error = (x2.subtract(x1)).abs();
                        }else{
                            error = ((x2.subtract(x1)).divide(x2,MathContext.DECIMAL128)).abs();
                        }
                        secanteXn.add(x1);
                        secanteFxn.add(fx1);
                        secanteEa.add((x2.subtract(x1)).abs());
                        secanteEr.add(((x2.subtract(x1)).divide(x2,MathContext.DECIMAL128)).abs());
                        
			x0 = x1;
			fx0 = fx1;
			x1 = x2;
			fx1 = new BigDecimal(funcion(f,x1.doubleValue()));
			den = fx1.subtract(fx0);
			contador++;
                        
		}
		if (fx1.equals(BigDecimal.ZERO)) {
			return x1+" es raiz";
		} 
		else if (error.compareTo(tolerancia)<0) {
			return x1+" es aproximacion a una raiz con una tolerancia = "+tolerancia;
		}
		else if (den.equals(BigDecimal.ZERO)) {
			return "Hay una posible raiz multiple";
		}
		else {
                        return "Fracaso en "+iter+" iteraciones";
		}
	}
}


    public String RaicesMultiples(double tolerancia1, double x01, int iter, String f,
                        String df, String ddf, boolean errorAbs){
        BigDecimal tolerancia = new BigDecimal(tolerancia1);
        BigDecimal x0 = new BigDecimal(x01);
        BigDecimal x1 = BigDecimal.ZERO;
        BigDecimal fx = new BigDecimal(funcion(f,x0.doubleValue()));//F(x1)
	BigDecimal dfx = new BigDecimal(funcion(df,x0.doubleValue()));//F'(x1)
        BigDecimal ddfx = new BigDecimal(funcion(ddf,x0.doubleValue()));
	int contador = 0;
	BigDecimal error = tolerancia.add(BigDecimal.ONE);
        raicesXn.add(x0);
        raicesFx.add(fx);
        raicesFdx.add(dfx);
        raicesFddx.add(ddfx);
        raicesEa.add(BigDecimal.ZERO);
        raicesEr.add(BigDecimal.ZERO);
        
        //double den = Math.pow(dfx, 2)-(fx*ddfx);
	BigDecimal den = (dfx.pow(2)).subtract((fx.multiply(ddfx)));
               
        while ((error.compareTo(tolerancia) > 0) && (!fx.equals(BigDecimal.ZERO))
                && (!den.equals(BigDecimal.ZERO)) && (contador < iter)) {
		
                x1 = x0.subtract((fx.multiply(dfx)).divide(den,MathContext.DECIMAL128));
                //x1 = x0 - ((fx*dfx)/den);
		fx = new BigDecimal(funcion(f,x1.doubleValue()));
		dfx = new BigDecimal(funcion(df,x1.doubleValue()));
                ddfx = new BigDecimal(funcion(ddf,x1.doubleValue()));
                if(errorAbs){
                    error = (x1.subtract(x0)).abs();
                }else{
                    error = ((x1.subtract(x0)).divide(x1,MathContext.DECIMAL128)).abs();
                }
                raicesXn.add(x0);
                raicesFx.add(fx);
                raicesFdx.add(dfx);
                raicesFddx.add(ddfx);
                raicesEa.add((x1.subtract(x0)).abs());
                raicesEr.add(((x1.subtract(x0)).divide(x1,MathContext.DECIMAL128)).abs());
               
                x0 = x1;
		contador++;
                den = (dfx.pow(2)).subtract((fx.multiply(ddfx)));
                //den = Math.pow(dfx, 2)-(fx*ddfx);
        }
	if (fx.equals(BigDecimal.ZERO)) {
		return x0+" es raiz";
	}
	else if (error.compareTo(tolerancia)<0) {
		return x1+" es una aproximacion a una raiz con una tolerancia = "+tolerancia;
	}
	else if (den.equals(BigDecimal.ZERO)) {
		return "Posible raiz multiple";
	}
	else {
		return "Fracaso en "+iter+" iteraciones";
	}

    }

    public ArrayList<BigDecimal> getBusquedaFx() {
        return busquedaFx;
    }

    public ArrayList<BigDecimal> getBusquedax() {
        return busquedax;
    }

    public ArrayList<BigDecimal> getBiseccionXi() {
        return biseccionXi;
    }

    public ArrayList<BigDecimal> getBiseccionXs() {
        return biseccionXs;
    }

    public ArrayList<BigDecimal> getBiseccionXm() {
        return biseccionXm;
    }

    public ArrayList<BigDecimal> getBiseccionFxm() {
        return biseccionFxm;
    }

    public ArrayList<BigDecimal> getBiseccionEr() {
        return biseccionEr;
    }

    public ArrayList<BigDecimal> getBiseccionEa() {
        return biseccionEa;
    }

    public ArrayList<BigDecimal> getReglaFalsaXi() {
        return reglaFalsaXi;
    }

    public ArrayList<BigDecimal> getReglaFalsaXs() {
        return reglaFalsaXs;
    }

    public ArrayList<BigDecimal> getReglaFalsaXm() {
        return reglaFalsaXm;
    }

    public ArrayList<BigDecimal> getReglaFalsaFxm() {
        return reglaFalsaFxm;
    }

    public ArrayList<BigDecimal> getReglaFalsaEr() {
        return reglaFalsaEr;
    }

    public ArrayList<BigDecimal> getReglaFalsaEa() {
        return reglaFalsaEa;
    }

    public ArrayList<BigDecimal> getPuntoFijoXn() {
        return puntoFijoXn;
    }

    public ArrayList<BigDecimal> getPuntoFijoFxn() {
        return puntoFijoFxn;
    }

    public ArrayList<BigDecimal> getPuntoFijoEa() {
        return puntoFijoEa;
    }

    public ArrayList<BigDecimal> getPuntoFijoEr() {
        return puntoFijoEr;
    }

    public ArrayList<BigDecimal> getNewtonXn() {
        return newtonXn;
    }

    public ArrayList<BigDecimal> getNewtonFx() {
        return newtonFx;
    }

    public ArrayList<BigDecimal> getNewtonFdx() {
        return newtonFdx;
    }

    public ArrayList<BigDecimal> getNewtonEa() {
        return newtonEa;
    }

    public ArrayList<BigDecimal> getNewtonEr() {
        return newtonEr;
    }

    public ArrayList<BigDecimal> getSecanteXn() {
        return secanteXn;
    }

    public ArrayList<BigDecimal> getSecanteFxn() {
        return secanteFxn;
    }

    public ArrayList<BigDecimal> getSecanteEa() {
        return secanteEa;
    }

    public ArrayList<BigDecimal> getSecanteEr() {
        return secanteEr;
    }

    public ArrayList<BigDecimal> getRaicesXn() {
        return raicesXn;
    }

    public ArrayList<BigDecimal> getRaicesFx() {
        return raicesFx;
    }

    public ArrayList<BigDecimal> getRaicesFdx() {
        return raicesFdx;
    }

    public ArrayList<BigDecimal> getRaicesFddx() {
        return raicesFddx;
    }

    public ArrayList<BigDecimal> getRaicesEa() {
        return raicesEa;
    }

    public ArrayList<BigDecimal> getRaicesEr() {
        return raicesEr;
    }
}
