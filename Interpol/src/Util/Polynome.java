/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

/**
 *
 * @author HP
 */
public class Polynome {
    // P(x) = coef[i] * x^i,  i = 0(1)degre
    int degre;
    double[] coef; // degre+1 coef
    
    public Polynome(){
        this.degre = -1;
        coef = new double[1];
        coef[0] = 0;
    }
    
    public Polynome(int degre){
        coef = new double[degre+1];
    }
    
    public Polynome(double[] coef){
        this.degre = coef.length-1;
        this.coef = coef;
    }
    
    public Polynome(int degre, double[] coef){
        this.degre = degre;
        this.coef = coef;
    }

    // Cout : 3n avec n = degre(P)
    public double evalDirectCanonique(double x){
        if(degre == -1)
            return 0;
        double t = 1, y = this.getCoef()[0];
        for(int k = 1; k <= this.getDegre(); k++){
            t = t * x;
            y = y + this.getCoef()[k]*t;
        }
        return y;
    }
    
    public double evalDirectTaylor(double x, double x0){
        if(degre == -1)
            return 0;
        double t = 1, y = this.getCoef()[0];
        for(int k = 1; k <= this.getDegre(); k++){
            t = t * (x-x0);
            y = y + this.getCoef()[k]*t;
        }
        return y;
    }
    
    // Cout : 2n avec n = degre(P)
    public double HornerCanonique(double x){
        if(degre == -1)
            return 0;
        double y = this.getCoef()[this.degre];
        for(int k = this.degre - 1; k >= 0; k--){
            y = this.getCoef()[k] + x*y;
        }
        return y;
    }
    
    public double HornerTaylor(double x, double x0){
        if(degre == -1)
            return 0;
        double y = this.getCoef()[this.degre];
        for(int k = this.degre - 1; k >= 0; k--){
            y = this.getCoef()[k] + (x-x0)*y;
        }
        return y;
    }
    
    public Polynome derivé(){
        if(this.degre == 0){
            return new Polynome();
        }
        Polynome pol = new Polynome(this.getDegre()-1);
        ///
        return pol;
    }
    
    public int getDegre(){
        return degre;
    }

    public void setDegre(int degre) {
        this.degre = degre;
    }

    public double[] getCoef() {
        return coef;
    }

    public void setCoef(double[] coef) {
        this.coef = coef;
    }
}
