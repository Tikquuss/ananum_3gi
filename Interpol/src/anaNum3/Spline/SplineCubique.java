/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anaNum3.Spline;
import Util.Polynome;

/**
 *
 * @author HP
 */
public class SplineCubique extends Spline{
    
    public static double b0 = 0;
    private double[] derivéSeconde; // Les dérivés secondes en xi
    
    public SplineCubique(double a, double b, int n, int qui) {// Spline naturelle
        super(a, b, n , qui);
        if(qui == 1) derivéSecondef1(); // pour f1
        if(qui == 2) derivéSecondef2(); // pour f2
        calculS();
    }
    
    public SplineCubique(double a, double b, int n, int qui, double b0) {
        this(a, b, n, qui);
        SplineCubique.b0 = b0;
    }
    
    public void derivéSecondef1(){
        //f'(x) = -2xf²(x) => f"(x) = -2f²(x)-4xf'(x)f(x)
        derivéSeconde = new double[this.getN()]; 
        double fprime, fcarré, x, fx;
        for (int i = 0; i <= this.getN()-1; i++) {
            x = set.get(i).getX();
            fx = set.get(i).getY();
            fcarré = Math.pow(fx, 2);
            fprime = -2*x*fcarré;
            derivéSeconde[i] = -2*fcarré-4*x*fprime*fx;
        }
    }
    
    public final void derivéSecondef2(){
        // f'(x) = -2xf²(x)e(-x²), or e(-x²) = 1/f(x) - 1, d'ou f"(x) = 2f(x)+2xf'(x)-2f²(x)-4xf'(x)f(x)
        derivéSeconde = new double[this.getN()]; 
        double expx2, fprime, fcarré, x, fx;
        for (int i = 0; i <= this.getN()-1; i++) {
            x = set.get(i).getX();
            fx = set.get(i).getY(); 
            expx2 = Math.exp(-Math.pow(x, 2));
            fcarré = Math.pow(fx, 2);
            fprime = -2*x*fcarré*expx2;
            derivéSeconde[i] = 2*fx+2*x*fprime-2*fcarré-4*x*fprime*fx;
        }
    }
    
    public final void calculS() {
        this.setS(new Polynome[this.getN()+1]);
        /* hi = xi+1 - xi
        Si(x) = ai0 + ai1*x + ai2*x2 + ai3*x3  = di + ci*x + bi*x² + ai*x3
        ai0 = di = yi
        ai1 = ci = (yi+1 - yi)/hi - hi*(y"i+1 +2y"i)/6
        ai2 = bi = y"i/2;
        ai3 = ai = (y"i+1 - y"i)/6hi;
        //*/
        
        double[] a = new double[this.getN()-1], 
                 b = new double[this.getN()-1], 
                 c = new double[this.getN()-1],
                 d = new double[this.getN()-1];
        
        double hi;
        for(int i = 0; i <= this.getN()-2; i++){
            hi = set.get(i+1).getX() - set.get(i).getX();
            d[i] = set.get(i).getY();
            c[i] = (set.get(i+1).getY() - set.get(i).getY())/hi - hi*(derivéSeconde[i+1] + 2*derivéSeconde[i]);
            b[i] = derivéSeconde[i]/2;
            a[i] = (derivéSeconde[i+1] - derivéSeconde[i])/(6*hi);
        }
             
        for(int i = 0; i <= this.getN()-2; i++){
            double[] coef = new double[4];
            coef[0] = d[i];
            coef[1] = c[i];
            coef[2] = b[i];
            coef[2] = a[i];
            this.getS()[i] = new Polynome(2, coef);
        }   
    }
    
    public static void main(String[] args) {
        //*
        double a = -10, b = 10, c;
        int n = 1000000;
        SplineCubique sq1 = new SplineCubique(a, b, n, 1);
        SplineCubique sq2 = new SplineCubique(a, b, n, 2);
        
        for(int i = (int)a; i <= (int)b; i++){
            b = 1.0/(1+Math.pow(i, 2));
            a = sq1.S(i);
            c = b - a;
            System.out.println("f1("+i+") = "+b);
            System.out.println("SplineCubiquef1("+ i + ") = " + a+":  erreur = "+ c);
            
            b = 1.0/(1+Math.exp(-Math.pow(i, 2)));
            a = sq2.S(i);
            c = b - a;
            System.out.println("f2("+i+") = "+b);
            System.out.println("SplineCubiquef2("+ i + ") = " + a+":  erreur = "+ c);
            
            System.out.println();   
        }
        //*/
    }
}
