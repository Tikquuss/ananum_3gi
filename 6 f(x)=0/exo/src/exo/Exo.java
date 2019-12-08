/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exo;

/**
 *
 * @author HP
 */
public class Exo {
    
    public static final double precision = 0.001;
    public static final double pas = 100;
    
    public static  double f(double x){
        //return  Math.pow(x,3)+x+1;
        return x;
    }
    
    public static double[] trouver(){
        double[] ab = new double[2];
        double fnegatif = -1, fpositif = Double.MAX_VALUE;
        while(f(fnegatif) > 0){
            System.out.println("f("+fnegatif+") = " + f(fnegatif));
            fnegatif+=pas;
        }
        while(f(fpositif) < 0){
            fpositif-=pas;
            System.out.println(fpositif);
        }
        if(fpositif < fnegatif){
            ab[0]=fpositif;
            ab[1]=fnegatif;
        }else{
            ab[0]=fnegatif;
            ab[1]=fpositif;
        }
        return ab;
    }
    
    public static double dichotomie(double a, double b){
        double x = (a+b)/2;
        if(f(x)<precision){
            return x;
        }else{
            if(f(x)*f(a) < 0)
                return dichotomie(a, x);
            else
                return dichotomie(x, b);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        double[] ab = trouver();
        System.out.println(dichotomie(ab[0], ab[1]));
    }
    
}
