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
public class SplineLineaire extends Spline{

    public SplineLineaire(double a, double b, int n, int qui) {
        super(a, b, n, qui);
        calculS();
    }
    
    public final void calculS(){
        this.setS(new Polynome[this.getN()-1]);
        /*
        Si(x) = ai0 + ai1*x = ai*x + bi
        ai1 = ai = (yi+1 - yi)/(xi+1 - xi)
        ai0 = bi = yi - ai1*xi = yi - ai*xi;
        i = 0(1)n
        //*/
        for(int i = 0; i <= this.getN() - 2; i++){
            double[] coef = new double[2];
            coef[1] = (set.get(i+1).getY() - set.get(i).getY())/(set.get(i+1).getX() - set.get(i).getX());
            coef[0] = set.get(i).getY() - coef[1] * set.get(i).getX();
            this.getS()[i] = new Polynome(1, coef);
        }      
    }
    
    @Override
    public double S(double x){
        System.out.println("sl HornerCanonique");
        if (x < this.getA() || x > this.getB()) {
            throw new IllegalArgumentException();
        }
        int i = 1;
        while(x >= this.getSet().get(i).getX())
            i++;
        // x € [xi-1, xi[ si i=1(1)n-1, x = xn-1 si i = n
        if(x == this.getSet().get(i-1).getX() /*&& i <= n-1*/)
            return this.getSet().get(i-1).getY();
        //else if(i == this.getN())
           // return this.getSet().get(n-1).getY();
        else // On évalue dans le base cononique centré en x0 avec Horner
            return this.getS()[i-1].HornerCanonique(x);
    };
    
    public static void main(String[] args) {
        //*
        double a = -10, b = 10, c;
        int n = 100000;
        SplineLineaire sl1 = new SplineLineaire(a, b, n, 1);
        SplineLineaire sl2 = new SplineLineaire(a, b, n, 2);
        
        for(int i = (int)a; i <= (int)b; i++){
            b = 1.0/(1+Math.pow(i, 2));
            a = sl1.S(i);
            c = b - a;
            System.out.println("f1("+i+") = "+b);
            System.out.println("SplineLineairef1("+ i + ") = " + a+":  erreur = "+ c);
            
            b = 1.0/(1+Math.exp(-Math.pow(i, 2)));
            a = sl2.S(i);
            c = b - a;
            System.out.println("f2("+i+") = "+b);
            System.out.println("SplineLineairef2("+ i + ") = " + a+":  erreur = "+ c);
            
            System.out.println();   
        }
        //*/
    }
}
