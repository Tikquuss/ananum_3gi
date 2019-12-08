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
public class Lagrange extends LagrangeChebyshev{
    int p;
    public Lagrange(double a, double b, int n, int qui) {
        super(a, b, n);
        if(qui == 1) Lagrange1(); // pour f1
        if(qui == 2) Lagrange2(); // pour f2
        calculCoefPasSurPlace(); 
    }

    /**
     * lagrange sur 1/(1+x^2)
     */
    private void Lagrange1() {
        double tmp;
        for (int i = 0; i <= this.getN()-1; i++) {
            tmp = this.getA() + i * (this.getB() - this.getA()) / this.getN();
            this.getSet().add(new Point(tmp, 1.0 / (1 + Math.pow(tmp, 2))));
        }
    }

    /**
     * lagrange sur 1/(1+exp(-x^2))
     */
    private void Lagrange2() {
        double tmp;
        for (int i = 0; i <= this.getN() - 1; i++) {
            tmp = this.getA() + i * (this.getB() - this.getA()) / this.getN();
            double tmp2 = !Point.negligeable(tmp, 0) ? Math.exp(-Math.pow(tmp, 2)) : 1;
            this.getSet().add(new Point(tmp, 1.0 / (1 + tmp2))); 
        }
    }
    
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        //*
        double a = -10, b = 10, c;
        int n = 10;
        Lagrange l1 = new Lagrange(a, b, n, 1);
        Lagrange l2 = new Lagrange(a, b, n, 2);
        
        for(int i = (int)a; i <= (int)b; i++){
            b = 1.0/(1+Math.pow(i, 2));
            System.out.println("f1("+i+") = "+b);
            
            a = l1.calculValueDirect(i);
            c = b - a;
            System.out.println("Lagrangef1("+ i + ") = " + a+":  erreur = "+ c);
            
            
            b = 1.0/(1+Math.exp(-Math.pow(i, 2)));
            System.out.println("f2("+i+") = "+b);
            
            a = l2.calculValueDirect(i);
            c = b - a;
            System.out.println("Lagrangef2("+ i + ") = " + a+":  erreur = "+ c);
            
            System.out.println();
        }
        //*/
    }
    
}
