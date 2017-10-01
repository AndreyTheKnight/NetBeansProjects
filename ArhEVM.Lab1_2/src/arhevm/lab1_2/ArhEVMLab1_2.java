package arhevm.lab1_2;

import java.util.Arrays;

public class ArhEVMLab1_2 {

    private static class MyArr {        
        private final Object[][] value;
        
        public MyArr(Object[][] value) {
            this.value = value;
        }
        public MyArr(int n, int m, int min, int max) {
            this.value = new Integer[n][m];
            for (Object[] val : this.value)
                for (int i = 0; i < val.length; i++)
                    val[i] = min + (int)(Math.random()*(max-min));
        }
        public void print() {
            for (Object[] row : this.value) {
                for (Object val : row)
                    System.out.print(val + " ");
                System.out.println();
            }
        }
        public int[] find() {            
            for (int i = 0; i < this.value.length - 1; i++)
                for (int j = i + 1; j < this.value.length; j++) {
                    Object[] temp = Arrays.copyOf(this.value[j], this.value[j].length);
                    int tempCount = temp.length;
                    for (Object val : this.value[i]) {
                        boolean foundElement = false;
                        for (int k = 0; k < tempCount; k++)
                            if (val == temp[k]) {
                                Object swap = temp[k];
                                temp[k] = temp[tempCount - 1];
                                temp[tempCount - 1] = swap;
                                tempCount--;
                                k--;
                                foundElement = true;
                                break;
                            }
                        if (!foundElement) break;
                    }
                    if (tempCount == 0) return new int[]{i, j};
                }
            return null;
        }
    }
    
    public static void main(String[] args) {
        
        MyArr arr = new MyArr(5, 6, -100, 100);
        
    }
    
}
