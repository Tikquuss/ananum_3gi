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
public class SplineQuadratique extends Spline{

    public static double b0 = 0; 
    
    public SplineQuadratique(double a, double b, int n, int qui) { // Spline naturelle
        super(a, b, n, qui);
        calculS();
    }
    
    public SplineQuadratique(double a, double b, int n, int qui, double b0) {
        this(a, b, n, qui);
        SplineQuadratique.b0 = b0;
    }
    
    public final void calculS() {
        this.setS(new Polynome[this.getN()-1]);
        /*
        Si(x) = ai0 + ai1*x + ai2*x2 = ci + bi*x + ai*x²
        ai0 = ci = yi
        b0 fixé
        a(i+1)1 = 2*(yi+1 - yi)/(xi+1 - xi) - a(i)1 ou bien bi+1 = 2*(yi+1 - yi)/(xi+1 - xi) - bi1
        
        ai0 = (a(i+1)1 - a(i)1)/(xi+1 - xi) ou ai = (bi+1 - bi)/(2*(xi+1 - xi))
        i = 0(1)n-2
        //*/
        
        double[] a = new double[this.getN()-1], 
                 b = new double[this.getN()-1], 
                 c = new double[this.getN()-1];
        
        for(int i = 0; i <= this.getN()-2; i++){
            c[i] = set.get(i).getY();
        }
        b[0] = b0;
        for(int i = 1; i <= this.getN()-2; i++){
            b[i] = 2*(set.get(i).getY() - set.get(i-1).getY())/(set.get(i).getX() - set.get(i-1).getX())
                   - b[i-1];
        }
 
        for(int i = 0; i <= this.getN()-3; i++){
            a[i] = (b[i+1]-b[i])/(2*(set.get(i+1).getX() - set.get(i).getX()));
        }
        a[this.getN()-2] = 0; // Probleme
                
        for(int i = 0; i <= this.getN()-2; i++){
            double[] coef = new double[3];
            coef[0] = c[i];
            coef[1] = b[i];
            coef[2] = a[i];
            this.getS()[i] = new Polynome(2, coef);
        }   
    }
    
    public static void main(String[] args) {
        //*
        double a = -10, b = 10, c;
        int n = 2;
        SplineQuadratique sq1 = new SplineQuadratique(a, b, n, 1);
        SplineQuadratique sq2 = new SplineQuadratique(a, b, n, 2);
        
        for(int i = (int)a; i <= (int)b; i++){
            b = 1.0/(1+Math.pow(i, 2));
            a = sq1.S(i);
            c = b - a;
            System.out.println("f1("+i+") = "+b);
            System.out.println("SplineQuadratiquef1("+ i + ") = " + a+":  erreur = "+ c);
            
            b = 1.0/(1+Math.exp(-Math.pow(i, 2)));
            a = sq2.S(i);
            c = b - a;
            System.out.println("f2("+i+") = "+b);
            System.out.println("SplineQuadratiquef2("+ i + ") = " + a+":  erreur = "+ c);
            
            System.out.println();   
        }
        //*/
    }
}
