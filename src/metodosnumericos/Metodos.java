/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodosnumericos;
import java.util.ArrayList;
import java.math.BigDecimal;
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
    private ArrayList<Double> reglaFalsaXi = new ArrayList<Double>();
    private ArrayList<Double> reglaFalsaXs = new ArrayList<Double>();
    private ArrayList<Double> reglaFalsaXm = new ArrayList<Double>();
    private ArrayList<Double> reglaFalsaFxm = new ArrayList<Double>();
    private ArrayList<Double> reglaFalsaEr = new ArrayList<Double>();
    private ArrayList<Double> reglaFalsaEa = new ArrayList<Double>();

    //ArrayList resultados Punto Fijo
    private ArrayList<Double> puntoFijoXn = new ArrayList<Double>();
    private ArrayList<Double> puntoFijoFxn = new ArrayList<Double>();
    private ArrayList<Double> puntoFijoEa = new ArrayList<Double>();
    private ArrayList<Double> puntoFijoEr = new ArrayList<Double>();
    
    //ArrayList resultados Newton
    private ArrayList<Double> newtonXn = new ArrayList<Double>();
    private ArrayList<Double> newtonFx = new ArrayList<Double>();
    private ArrayList<Double> newtonFdx = new ArrayList<Double>();
    private ArrayList<Double> newtonEa = new ArrayList<Double>();
    private ArrayList<Double> newtonEr = new ArrayList<Double>();
    
    //ArrayList resultados secante
    private ArrayList<Double> secanteXn = new ArrayList<Double>();
    private ArrayList<Double> secanteFxn = new ArrayList<Double>();
    private ArrayList<Double> secanteEa = new ArrayList<Double>();
    private ArrayList<Double> secanteEr = new ArrayList<Double>();
    
    //ArrayList resultados Raices Multiples
    private ArrayList<Double> raicesXn = new ArrayList<Double>();
    private ArrayList<Double> raicesFx = new ArrayList<Double>();
    private ArrayList<Double> raicesFdx = new ArrayList<Double>();
    private ArrayList<Double> raicesFddx = new ArrayList<Double>();
    private ArrayList<Double> raicesEa = new ArrayList<Double>();
    private ArrayList<Double> raicesEr = new ArrayList<Double>();
    
    
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
                            error = ((xm.subtract(xaux)).divide(xm)).abs();
                        }
			contador++;
                        biseccionXi.add(xi);
                        biseccionXs.add(xs);
                        biseccionXm.add(xm);
                        biseccionFxm.add(fxm);
                        biseccionEa.add(xm.subtract(xaux).abs());
                      //  biseccionEr.add(((xm.subtract(xaux)).divide(xm)).abs());
                        biseccionEr.add(BigDecimal.ZERO);
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
    
    public String ReglaFalsa(double xs, double xi, double tolerancia, int iter,
                            String f, boolean errorAbs) {
	double fxi = funcion(f,xi);
	double fxs = funcion(f,xs);
	if (fxi == 0) {
		reglaFalsaXi.add(xi);
                reglaFalsaXs.add(xs);
                reglaFalsaXm.add(0.0);
                reglaFalsaFxm.add(0.0);
                reglaFalsaEa.add(0.0);
                reglaFalsaEr.add(0.0);
                return xi+" es raiz";
	}
	else if (fxs == 0) {
                reglaFalsaXi.add(xi);
                reglaFalsaXs.add(xs);
                reglaFalsaXm.add(0.0);
                reglaFalsaFxm.add(0.0);
                reglaFalsaEa.add(0.0);
                reglaFalsaEr.add(0.0);
		return xs+" es raiz";
	}
	else if (fxi * fxs < 0) {
		double xm = xi - ((fxi*(xs-xi))/(fxs-fxi));
		double fxm = funcion(f,xm);
		int contador = 1;
		double error = tolerancia + 1;
                reglaFalsaXi.add(xi);
                reglaFalsaXs.add(xs);
                reglaFalsaXm.add(xm);
                reglaFalsaFxm.add(fxm);
                reglaFalsaEa.add(0.0);
                reglaFalsaEr.add(0.0);
		while ((error > tolerancia) && (fxm != 0) && contador < iter) {
			if (fxi*fxm < 0) {
				xs = xm;
				fxs = fxm;
			}
			else {
				xi = xm;
				fxi = fxm;
			}
			double xaux = xm;
			xm = xi - ((fxi*(xs - xi)) / (fxs - fxi));
			fxm = funcion(f,xm);
			if(errorAbs){
                            error = Math.abs(xm - xaux);
                        }else{
                            error = Math.abs((xm - xaux)/xm);
                        }
                        reglaFalsaXi.add(xi);
                        reglaFalsaXs.add(xs);
                        reglaFalsaXm.add(xm);
                        reglaFalsaFxm.add(fxm);
                        reglaFalsaEa.add(Math.abs(xm - xaux));
                        reglaFalsaEr.add(Math.abs((xm - xaux)/xm));
                        contador++;
		}
		if (fxm == 0) {
			return xm+" es raiz";
		}
		else if (error < tolerancia) {
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
    
    public String PuntoFijo(double tolerancia, double xa, int iter, String f,
                            String g, boolean errorAbs) {
	double fx = funcion(f,xa);
	int contador = 0;
	double error = tolerancia + 1;
        puntoFijoXn.add(xa);
        puntoFijoFxn.add(fx);
        puntoFijoEa.add(0.0);
        puntoFijoEr.add(0.0);
	while ((fx != 0) && (error > tolerancia) && contador < iter) {
		double xn = funcion(g,xa);
		fx = funcion(f,xn);
		if(errorAbs){
                    error = Math.abs(xn - xa); 
                }else{
                    error = Math.abs((xn - xa)/xn);
                }
		xa = xn;
		contador++;
                puntoFijoXn.add(xn);
                puntoFijoFxn.add(fx);
                puntoFijoEa.add(Math.abs(xn - xa));
                puntoFijoEr.add(Math.abs((xn - xa)/xn));
	}
	if (fx == 0) {
		return xa+" es raiz";
	}
	else if (error < tolerancia) {
		return xa+" es una aproximacion con una tolerancia = "+tolerancia;
	}
	else {
		return "El metodo fracaso en "+iter+"iteraciones";
	}
}
    
    public String Newton(double tolerancia, double x0, int iter, String f,
                        String df, boolean errorAbs){
        double x1 = 0;
        double fx = funcion(f,x0);//F(x1)
	double dfx = funcion(df,x0);//F'(x1)
	int contador = 0;
	double error = tolerancia + 1;
        newtonXn.add(x0);
        newtonFx.add(fx);
        newtonFdx.add(dfx);
        newtonEa.add(0.0);
        newtonEr.add(0.0);
	while ((error > tolerancia) && (fx != 0) && (dfx != 0) && (contador < iter)) {
		x1 = x0 - (fx / dfx);
		fx = funcion(f,x1);//F(x1)
		dfx = funcion(df,x1);//F'(x1)
                if(errorAbs){
                    error = Math.abs(x1 - x0);
                }else{
                    error = Math.abs((x1 - x0)/x1);
                }
		x0 = x1;
		contador++;
                newtonXn.add(x0);
                newtonFx.add(fx);
                newtonFdx.add(dfx);
                newtonEa.add(Math.abs(x1 - x0));
                newtonEr.add(Math.abs((x1 - x0)/x1));
        }
	if (fx == 0) {
		return x0+" es raiz";
	}
	else if (error < tolerancia) {
		return x1+" es una aproximacion a una raiz con una tolerancia = "+tolerancia;
	}
	else if (dfx == 0) {
		return x1+" es una posible raiz multiple";
	}
	else {
		return "Fracaso en "+iter+" iteraciones";
	}

    }


    public String MetodoDeLaSecante(double tolerancia, double x0, double x1,
                                    int iter, String f, boolean errorAbs) {
	double fx0 = funcion(f,x0);
        secanteXn.add(x0);
        secanteFxn.add(fx0);
        if (fx0 == 0) {
		return x0+" es raiz";
	}
	else {
		double fx1 = funcion(f,x1);
		int contador = 0;
		double error = tolerancia + 1;
		double den = fx1 - fx0;
		secanteXn.add(x1);
                secanteFxn.add(fx1);
                secanteEa.add(0.0);
                secanteEr.add(0.0);
		while ((error < tolerancia) && (fx1 != 0) && (den != 0) && (contador < iter)) {
			double x2 = x1 - (fx1 * (x1 - x0) / den);
			if(errorAbs){
                            error = Math.abs(x2 - x1);   
                        }else{
                            error = Math.abs((x2 - x1)/x2);
                        }
			x0 = x1;
			fx0 = fx1;
			x1 = x2;
			fx1 = funcion(f,x1);
			den = fx1 - fx0;
			contador++;
                        secanteXn.add(x1);
                        secanteFxn.add(fx1);
                        secanteEa.add(Math.abs(x2 - x1));
                        secanteEr.add(Math.abs((x2 - x1)/x2));
                        
		}
		if (fx1 == 0) {
			return x1+" es raiz";
		}
		else if (error < tolerancia) {
			return x1+" es aproximacion a una raiz con una tolerancia = "+tolerancia;
		}
		else if (den == 0) {
			return "Hay una posible raiz multiple";
		}
		else {
                        return "Fracaso en "+iter+" iteraciones";
		}
	}
}


    public String RaicesMultiples(double tolerancia, double x0, int iter, String f,
                        String df, String ddf, boolean errorAbs){
        double x1 = 0;
        double fx = funcion(f,x0);//F(x1)
	double dfx = funcion(df,x0);//F'(x1)
        double ddfx = funcion(ddf,x0);
	int contador = 0;
	double error = tolerancia + 1;
        raicesXn.add(x0);
        raicesFx.add(fx);
        raicesFdx.add(dfx);
        raicesFddx.add(ddfx);
        raicesEa.add(0.0);
        raicesEr.add(0.0);
        double den = Math.pow(dfx, 2)-(fx*ddfx);
	while ((error > tolerancia) && (fx != 0) && (den != 0) && (contador < iter)) {
		x1 = x0 - ((fx*dfx)/den);
		fx = funcion(f,x1);
		dfx = funcion(df,x1);
                ddfx = funcion(ddf,x1);
                if(errorAbs){
                    error = Math.abs(x1 - x0);
                }else{
                    error = Math.abs((x1 - x0)/x1);
                }
		x0 = x1;
		contador++;
                den = Math.pow(dfx, 2)-(fx*ddfx);
                raicesXn.add(x0);
                raicesFx.add(fx);
                raicesFdx.add(dfx);
                raicesFddx.add(ddfx);
                raicesEa.add(Math.abs(x1 - x0));
                raicesEr.add(Math.abs((x1 - x0)/x1));
        }
	if (fx == 0) {
		return x0+" es raiz";
	}
	else if (error < tolerancia) {
		return x1+" es una aproximacion a una raiz con una tolerancia = "+tolerancia;
	}
	else if (den == 0) {
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

    public ArrayList<Double> getReglaFalsaXi() {
        return reglaFalsaXi;
    }

    public ArrayList<Double> getReglaFalsaXs() {
        return reglaFalsaXs;
    }

    public ArrayList<Double> getReglaFalsaXm() {
        return reglaFalsaXm;
    }

    public ArrayList<Double> getReglaFalsaFxm() {
        return reglaFalsaFxm;
    }

    public ArrayList<Double> getReglaFalsaEr() {
        return reglaFalsaEr;
    }

    public ArrayList<Double> getReglaFalsaEa() {
        return reglaFalsaEa;
    }

    public ArrayList<Double> getPuntoFijoXn() {
        return puntoFijoXn;
    }

    public ArrayList<Double> getPuntoFijoFxn() {
        return puntoFijoFxn;
    }

    public ArrayList<Double> getPuntoFijoEa() {
        return puntoFijoEa;
    }

    public ArrayList<Double> getPuntoFijoEr() {
        return puntoFijoEr;
    }

    public ArrayList<Double> getNewtonXn() {
        return newtonXn;
    }

    public ArrayList<Double> getNewtonFx() {
        return newtonFx;
    }

    public ArrayList<Double> getNewtonFdx() {
        return newtonFdx;
    }

    public ArrayList<Double> getNewtonEa() {
        return newtonEa;
    }

    public ArrayList<Double> getNewtonEr() {
        return newtonEr;
    }

    public ArrayList<Double> getSecanteXn() {
        return secanteXn;
    }

    public ArrayList<Double> getSecanteFxn() {
        return secanteFxn;
    }

    public ArrayList<Double> getSecanteEa() {
        return secanteEa;
    }

    public ArrayList<Double> getSecanteEr() {
        return secanteEr;
    }

    public ArrayList<Double> getRaicesXn() {
        return raicesXn;
    }

    public ArrayList<Double> getRaicesFx() {
        return raicesFx;
    }

    public ArrayList<Double> getRaicesFdx() {
        return raicesFdx;
    }

    public ArrayList<Double> getRaicesFddx() {
        return raicesFddx;
    }

    public ArrayList<Double> getRaicesEa() {
        return raicesEa;
    }

    public ArrayList<Double> getRaicesEr() {
        return raicesEr;
    }
}
