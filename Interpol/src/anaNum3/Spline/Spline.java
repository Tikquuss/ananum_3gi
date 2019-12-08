/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anaNum3.Spline;

import Util.*;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public abstract class Spline {
    private double a; //borne inf
    private double b; //borne sup
    private int n; // nombre de point d'interpolation, y compri a et b
    ArrayList<Point> set; // n points (xi, yi)
    Polynome[] S; // (n-1) Si, S(x) = Si(x) si x € [xi, xi+1]
    
    public Spline(double a, double b, int n, int qui){
        if (n < 2) {
            throw new IllegalArgumentException();
        }
        this.a = a;
        this.b = b;
        this.n = n;
        if(qui == 1) setf1(a, b); // pour f1
        if(qui == 2) setf2(a, b); // pour f2
        S = new Polynome[n-1];
    }
    
    // Methode appelée par le gabage_collector (ramasse-miettes) 
    protected void finalize() throws Throwable {
        super.finalize();
    }
    
    // On subdivise [a, b] en n intervalles de memes longueur (b-a)/n
    // Point-image pour f1
    private void setf1(double a, double b) {
        double xi;
        set = new ArrayList<>(n); 
        for (int i = 0; i <= n-1; i++) {
            xi =  a + i * (b - a) / (n-1);
            set.add(new Point(xi , 1.0 / (1 + Math.pow(xi, 2))));
        }
    }
    
    private void setf2(double a, double b) {
        double xi, tmp;
        set = new ArrayList<>(n);
        for (int i = 0; i <= n-1; i++) {
            xi =  a + i * (b - a) / (n-1);
            tmp = Point.negligeable(xi, 0) ? 1 : Math.exp(-Math.pow(xi, 2));
            set.add(new Point(xi, 1.0 / (1 + tmp)));
        }
    }    
    
    // Calcul de S(x) dans le base de Taylor
    public double S(double x){ 
        int i = trouve(x);// x € [xi-1, xi[ si i=1(1)n-1, x = xn-1 si i = n
        if(x == this.getSet().get(i-1).getX())
            return this.getSet().get(i-1).getY();
        else // On évalue dans le base de Taylor centré en x0 avec Horner
            return this.getS()[i-1].HornerTaylor(x, this.getSet().get(i-1).getX()); // Si-1(x)
    };
    
    // Trouve i tel que x € [xi-1, xi[
    public int trouve(double x){
        if (x < a || x > b) {
            throw new IllegalArgumentException();
        }
        int i = 1;
        while(x >= this.getSet().get(i).getX())
            i++;
        return i;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public ArrayList<Point> getSet() {
        return set;
    }

    public void setSet(ArrayList<Point> set) {
        this.set = set;
    }

    public Polynome[] getS() {
        return S;
    }

    public void setS(Polynome[] S) {
        this.S = S;
    }
}
