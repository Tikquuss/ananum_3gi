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
public class exo1_9 {
    
    private double a = 64.1;
    private double b = 63.1;
    
    public exo1_9(){}
    
    public exo1_9(double a, double b){
        this.a = a;
        this.b = b;
    }
    
    public void xxx(){
        // pour d’autres valeurs de a et b
        // la suite des x est (1, 1, 1, ...
        double x = 1.0;
        for (int i=1; i <= 14; i++){
            System.out.println(""+i+" "+x);
            x = a*x - b;
            // b = a-1 donc x = b*(x-1)+x
            // x initial = 1, donc x = x = 1
        }
    }
     
    public static void main(String[] args) {
        exo1_9 e = new exo1_9();
        e.xxx();
    }
}
