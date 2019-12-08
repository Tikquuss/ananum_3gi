/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author HP
 */
public class exo1_3 {
    
    public double precision(double x){
        double  e = Double.MIN_NORMAL;
        while((x + e == x) || (x - e == x)){
            e*=2;
        }
        return e;
    }
    
    public double precision2(int x){
        double  h = 1.0, h1, h2, e = 0.1;
        while(x+h == x)
            h*=e;
        h1 = h;
        h = h/2;
        while(true){
            h2 = (h+h1)/2;
            if(x+h2==x)
                h = h2;
            else{
                h1 = h2;
                if(h+e > h1)
                    break;
            }
        }
        System.out.println(h);
        return h;
    }
    
    public double newton(double a, double precision){
        double x = 1;
        double s = (x + a/x)/2;
        if(a==0)
            return 0;
        while(precision < Math.abs((x-s)/s)){
            x = s;
            s = (s + a/s)/2;
        }
           
        System.out.println(x + " " + s);
        return s;
    }
    
    public static void main(String[] args) {
        exo1_3 e = new exo1_3();
        //e.precision(Math.pow(10, 30));  
        System.out.println(e.precision(Math.pow(10, 30)));
        //e.precision2(10^(30));
        
        //e.newton(49, 0.00000000000001);
        //System.out.println(e.newton(4, 0.00000001));
    }
}
