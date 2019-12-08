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
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println(inv_facto(24));
    }

        private static int inv_facto(int n) {
            if (n <= 0 || premier(n)) // 
                return -1;
            if(n == 1 || n == 2)
                return n;
            else{
                int i = 2, p = 2;
                while( i < n/2 && p < n){
                    p = p * i;
                    i++;
                }
                if(p == n)
                    return i - 1;
                else
                    return -1;
            }
        }  
        
    private static boolean premier(int n) {
        if(n == 1 || (n/2 == 0))
            return false;
        else
            return !((n/2 == 0) || (n/3 == 0) || (n/5 == 0) || (n/7 == 0) || (n/9 == 0)); 
    }
    
    
}
