/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_structure;

/**
 *
 * @author HP
 */
public class Util {
    public static void afficheTab(int[] T, int l){	
	for(int i = 0; i < l; i++){
            System.out.printf(T[i] + " | ");
	}
	System.out.println("\n\n");
    }
    
    public static void afficheTab(int[][] T, int L, int C){	
	for(int i = 0; i < L; i++){
            for(int j = 0; j < C; j++)
		System.out.printf(T[i][j] + " ");
            System.out.println("");
	}
	System.out.println("");
    }
    
    public static int[][] copie(int[][] T, int L, int C){
        int[][] tmp = new int[L][C];
        for(int i = 0; i < L; i++){
            for(int j = 0; j < C; j++)
		tmp[i][j] = T[i][j];
	}
        return tmp;
    }
}
