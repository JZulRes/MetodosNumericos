/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodosnumericos;
import java.util.ArrayList;
/**
 * @author Juan Fernando Zuluaga <jzulua50@eafit.edu.co>
 * @author Daniel Arango Pelaez <darang24@eafit.edu.co>
 */
public class Metodos {

    //Arraylist de todos los metodos:
    //ArrayList resultados busquedas incrementales
    private ArrayList<Double> busquedaFx = new ArrayList<Double>();  
    private ArrayList<Double> busquedax = new ArrayList<Double>();  
    
    //ArrayList resultados biseccion
    private ArrayList<Double> biseccionXi = new ArrayList<Double>();
    private ArrayList<Double> biseccionXs = new ArrayList<Double>();
    private ArrayList<Double> biseccionXm = new ArrayList<Double>();
    private ArrayList<Double> biseccionFxm = new ArrayList<Double>();
    private ArrayList<Double> biseccionEr = new ArrayList<Double>();
    private ArrayList<Double> biseccionEa = new ArrayList<Double>();

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
    
    private double funcion(String s, double x){
        Evaluador funcion = new Evaluador();
        return funcion.Evaluador1(s, x);
    }
    
    
    public String Busqueda(double x0, double delta, int iter, String f) {
	double fxo = funcion(f,x0);
	busquedax.add(x0);
        busquedaFx.add(fxo);
	if (x0 == 0) {
		return x0+" es raiz";
	}
	else {
		double x1 = x0 + delta;
		int contador = 1;
		double fx1 = funcion(f,x1);
                while ((fxo*fx1 > 0) && (contador < iter)) {
                        busquedax.add(x1);
                        busquedaFx.add(fx1);
			x0 = x1;
			fxo = fx1;
			x1 = x0 + delta;
			fx1 = funcion(f,x1);
			contador++;
                }
                busquedax.add(x1);
                busquedaFx.add(fx1);
		if (fx1 == 0) {
			return x1+" es raiz";
		}
		else if((fxo * fx1) < 0){
			return "Hay una raiz entre "+x0+" y "+x1;
		}
		else {
			return "Fracaso en "+iter+" iteraciones";
		}
	}
    }
            
    public String Biseccion(double xs, double xi, double tolerancia, int iter,
                            String f, boolean errorAbs) {
        
	double fxi = funcion(f,xi);
	double fxs = funcion(f,xs);
	
        if (fxi == 0) {
		return xi+" es raiz";
	}
	else if (fxs == 0) {
		return xs+" es raiz";
	}
	else if (fxi * fxs < 0) {
		double xm = (xi + xs) / 2;
		double fxm = funcion(f,xm);
		int contador = 1;
		double error = tolerancia + 1;
                biseccionXi.add(xi);
                biseccionXs.add(xs);
                biseccionXm.add(xm);
                biseccionFxm.add(fxm);
                biseccionEa.add(0.0);
                biseccionEr.add(0.0);
                
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
			xm = (xi + xs) / 2;
			fxm = funcion(f,xm);
			if(errorAbs){
                            error = Math.abs(xm - xaux);
                        }else{
                            error = Math.abs((xm-xaux)/xm);
                        }
			contador++;
                        biseccionXi.add(xi);
                        biseccionXs.add(xs);
                        biseccionXm.add(xm);
                        biseccionFxm.add(fxm);
                        biseccionEa.add(Math.abs(xm - xaux));
                        biseccionEr.add(Math.abs((xm-xaux)/xm));

                }
		if (fxm == 0) {
			return xm+" es raiz";
		}
		else if (error < tolerancia) {
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
		return xi+" es raiz";
	}
	else if (fxs == 0) {
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

    
    
    
    public ArrayList<Double> getBusquedaFx() {
        return busquedaFx;
    }

    public ArrayList<Double> getBusquedax() {
        return busquedax;
    }

    public ArrayList<Double> getBiseccionXi() {
        return biseccionXi;
    }

    public ArrayList<Double> getBiseccionXs() {
        return biseccionXs;
    }

    public ArrayList<Double> getBiseccionXm() {
        return biseccionXm;
    }

    public ArrayList<Double> getBiseccionFxm() {
        return biseccionFxm;
    }

    public ArrayList<Double> getBiseccionEr() {
        return biseccionEr;
    }

    public ArrayList<Double> getBiseccionEa() {
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


    
}
