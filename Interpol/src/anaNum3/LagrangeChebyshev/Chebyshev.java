/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anaNum3.LagrangeChebyshev;

import Util.Point;

/**
 *
 * @author HP
 */
public class Chebyshev extends LagrangeChebyshev{
    public Chebyshev(double a, double b, int n, int qui) {
        super(a, b, n);
        if(qui == 1) Chebyshev1(); // pour f1
        if(qui == 2) Chebyshev1(); // pour f2
        calculCoefPasSurPlace(); 
    }
    /**
     * chebyshev sur 1/(1+x^2)
     */
    private void Chebyshev1() {
        double tmp;
        for (int i = 0; i < this.getN(); i++) {
            tmp = (this.getB() - this.getA()) * Math.cos((2 * i + 1) * Math.PI / (2 * (this.getN() - 1))) / 2;
            this.getSet().add(new Point(tmp, 1.0 / (1 + Math.pow(tmp, 2))));
        }
    }

    /**
     * chebyshev 1/(1+exp(-x^2))
     */
    private void Chebyshev2() {
        double tmp;
        for (int i = 0; i < this.getN(); i++) {
            tmp = (this.getB() - this.getA()) * Math.cos((2 * i + 1) * Math.PI / (2 * (this.getN() - 1))) / 2;
            //double tmp2 = !negligeable(tmp, 0) ? Math.exp(Math.pow(tmp, -2)) : 1;
            double tmp2 = !Point.negligeable(tmp, 0) ? Math.exp(-Math.pow(tmp, 2)) : 1;
            this.getSet().add(new Point(tmp, 1.0 / (1 + tmp2)));
        }
    }
    
    public static void main(String[] args) {
        //*
        double a = -10, b = 10, c;
        int n = 10;
        Chebyshev c1 = new Chebyshev(a, b, n, 1);
        Chebyshev c2 = new Chebyshev(a, b, n, 2);
        
        for(int i = (int)a; i <= (int)b; i++){
            b = 1.0/(1+Math.pow(i, 2));
            a = c1.calculValueDirect(i);
            c = b - a;
            System.out.println("f1("+i+") = "+b);
            System.out.println("Chebyshevf1("+ i + ") = " + a+":  erreur = "+ c);
            
            b = 1.0/(1+Math.pow(i, 2));
            System.out.println("f1("+i+") = "+b);
            a = c2.calculValueDirect(i);
            c = b - a;
            System.out.println("Chebyshevf2("+ i + ") = " + a+":  erreur = "+ c);
            
            System.out.println();   
        }
        //*/
    }
}
