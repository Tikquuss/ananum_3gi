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
public class DATA_STRUCTURE {
    
    public static int[][] nil = {{0}};
    
    public static int[][] CRS(int[][] A, int L, int C){
        int N = Nb_NonNull(A, L, C);
        if(N == 0){
            return nil;
        }else{
            int i, j, k = 0, nenn = 0;
            int[][] tmp = new int[3][N];
            tmp[2][0] = 1;
            for(i = 0; i < L; i++){
                nenn = Nb_NonNullLigne(A, L, C, i);
                //System.out.println(nenn);
                if(nenn == 0){
                }else{
                    for(j = 0; j < C; j++){
                        if(A[i][j] != 0){
                            tmp[0][k] = A[i][j];
                            tmp[1][k] = j+1;
                            k++;
                        }
                    } 
                    if(i < N-1){
                        tmp[2][i+1] = tmp[2][i]+nenn;  
                    }else if(i == N-1){
                        tmp[2][i] = N;  
                    }
                }
            }
            return tmp; 
        }   
    }
    
    public static int[][] CCS(int[][] A, int L, int C){
        return CRS(transposer(A, L, C), C, L);
    }

    public static int Nb_NonNull(int[][] A, int L, int C){
        int n = 0;
        for(int i = 0; i < L; i++){
            for(int j = 0; j < C; j++){
                if(A[i][j] != 0){
                    //System.out.println(A[i][j]);
                    n++;
                }
            }
        }
        return n;
    }
    
    public static int Nb_NonNullLigne(int[][] A, int L, int C, int i){
        int n = 0;
        for(int j = 0; j < C; j++){
            if(A[i][j] != 0){
                n++;
            }
        }
        return n;
    }
    
    public static int Nb_NonNullColonne(int[][] A, int L, int C, int j){
        int n = 0;
        for(int i = 0; i < L; i++){
            if(A[i][j] != 0){
                n++;
            }
        }
        return n;
    }
    
    public static int[][] transposer(int[][] A, int L, int C){
        int[][] tmp = new int[L][C];
        for(int i = 0; i < L; i++){
            for(int j = 0; j < C; j++){
                tmp[i][j] = A[j][i];   
            }
        }
        return tmp;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        int[][] A = {{10, 0, 0, 0, -2, 0},
                     {3,  9, 0, 0,  0, 3},
                     {0,  7, 8, 7,  0, 0},
                     {3,  0, 8, 7,  5, 0},
                     {0,  8, 0, 9,  9, 13},
                     {0,  4, 0, 0,  2, -1}
        };
        
        int[][] B = {{0, 0},
                     {0, 4}
        };
        
        int[][] O = {{0}};
        
        //Util.afficheTab(CRS(A, 6, 6), 3, 19);
        //Util.afficheTab(CCS(A, 6, 6), 3, 19);
        
        Util.afficheTab(CRS(B, 2, 2), 3, 1);
        Util.afficheTab(CCS(B, 2, 2), 3, 1);
        
        //Util.afficheTab(CRS(O, 1, 1), 3, 0);
    }
}
