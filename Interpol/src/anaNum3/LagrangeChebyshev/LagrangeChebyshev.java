/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anaNum3.LagrangeChebyshev;

import Util.Point;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class LagrangeChebyshev {
    private double a; //borne inf
    private double b; //borne sup
    private int n; // nombre de point d'interpolation
    private ArrayList<Point> set; // n points (xi, yi)
    private double[] coef; // n coeficients f[X0,X1,X2...Xk], k = 0(1)n-1
    
    public LagrangeChebyshev(double a, double b, int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        this.a = a;
        this.b = b;
        this.n = n;  
    }
    
    /**
     * calcul de f[X0,X1,X2...Xn-1]
     */
    public void calculCoefSurPlace(){
        double[] x = new double[n];
        int i = 0;
        for (Point p : set) {
            coef[i] = p.getY();
            x[i++] = p.getX();
        }
        for (int j = 1; j < n; j++) {
            double tmp = coef[0];
            for (i = 0; i < n - j; i++) {
                coef[i] = (coef[i] - coef[i + 1]) / (x[i + j] - x[i]);
            }
            coef[n - j] = tmp;
        }
    }
    
    public final void calculCoefPasSurPlace(){
        this.coef = new double[n];
        double[][] T = new double[n][n];
        int i, j;
        
        for(i = 0; i<= n-1; i++){ //j = 0
            T[i][0] = set.get(i).getY();
        }
        for(j = 1; j<= n-1; j++){
            for(i = j; i<= n-1; i++){
                T[i][j] = (T[i][j-1]-T[i-1][j-1])/(set.get(i).getX()-set.get(i-j).getX());
            }
        }
        for(i = 0; i<= n-1; i++){ 
            coef[i] = T[i][i];
        }      
    }

    public double calculValueDirect(double val) {
        double[] value = new double[n];
        double[] x = new double[n];
        double[] delta = new double[n];
        int i = 0;
        for (Point p : set) {
            x[i] = p.getY();
            delta[i] = val - p.getX();
            value[i++] = p.getY();
        }
        for (int j = 1; j < n; j++) {
            double tmp = value[0];
            for (i = 0; i < n - j; i++) {
                value[i] = (delta[i] * value[i + 1] - delta[i + j] * value[i]) / (x[i] - x[i + j]);
            }
            value[n - j] = tmp;
        }
        return value[0];
    }

    public double calculValue(double x) {
        if (x < a || x > b) {
            throw new IllegalArgumentException();
        }
        double[] X = new double[n];
        int i = 0;
        for (Point p : set) {
            X[i++] = p.getX();
        }
        double tmp = coef[1] + coef[0] * (a - X[n - 2]);
        for (i = 2; i < n; i++) {
            tmp = tmp * (a - X[n - i - 1]) + coef[i];
        }
        return tmp;
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

    public double[] getCoef() {
        return coef;
    }

    public void setCoef(double[] coef) {
        this.coef = coef;
    }
}
