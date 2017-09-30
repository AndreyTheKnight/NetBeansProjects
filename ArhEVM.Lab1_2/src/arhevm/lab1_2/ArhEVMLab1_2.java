package arhevm.lab1_2;

import static java.lang.Math.random;

public class ArhEVMLab1_2 {
    
    private class MyArr {
        
        private final int[][] values;
        
        public MyArr(int n, int m, int min, int max) {
            this.values = new int[n][m];
            for (int i = 0; i < n; i++)
                for (int j = 0; j < m; j++)
                    this.values[i][j] = min + (int)(random() * (max-min));
        }
        public void print() {
            for (int[] row : this.values) {
                for (int val : row)
                    System.out.print(val + " ");
                System.out.println();
            }
        }
                
    }

    public static void main(String[] args) {
        // TODO code application logic here
        
    }
    
}
