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
public class exo1_6 {
    
    public long petit_frere_facto(long n){
        for(long i = n; i > 0; i--){
            if(inv_facto(i) != -1)
                return i;
        }
        return -1;
    }
    
    public long inv_facto(long n){
        if(n == 1 || n == 2)
            return n;
        if (n <= 0 || premier(n))
            return -1;   
        else{
            int i = 1, a = 1;
            while(true){
                a = a * i;
                if(a == n)
                    return i;
                if(a > n)
                    return -1;
                i++;
            }
        }
    }
  
    boolean premier(long n){
        if(n == 2) 
            return true;
        if(n == 1 || (n%2 == 0))
            return false;
        else
            return !((n%2 == 0) || (n%3 == 0) || (n%5 == 0) || (n%7 == 0) || (n%9 == 0)); 
    }
    
    int combinaison(int k, int n){
        if(k>n || 0>n || 0>k){
            return -1;
        }else if(k == 0 || k == n){
            return 1;
        }else{
            return combinaison(k, n-1) + combinaison(k-1, n-1) ;
        }
    }
    
    public static void main(String[] args) {
        exo1_6 e = new exo1_6();
        System.out.println(e.inv_facto(2));
        //System.out.println(e.petit_frere_facto(922337203));
        double d = 12345.6;
        byte b = (byte)d; // b vaut 57
        System.out.println(b);
        
        System.out.println(e.combinaison(0, 2));
        System.out.println(e.combinaison(2, 0));
        System.out.println(e.combinaison(2, 2));
        System.out.println(e.combinaison(3, 2));
    }
}
