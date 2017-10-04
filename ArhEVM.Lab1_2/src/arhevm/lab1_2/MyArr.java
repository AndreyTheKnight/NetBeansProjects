/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arhevm.lab1_2;

/**
 *
 * @author andre
 */
public class MyArr {
    private final Object[][] value;

    public MyArr(Object[][] value) {
        this.value = value;
    }
    public MyArr(int n, int m, int min, int max) {
        this.value = new Integer[n][m];
        for (Object[] val : this.value)
            for (int i = 0; i < val.length; i++)
                val[i] = (int)Math.round(min + Math.random()*(max-min));
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
                Object[] temp = new Object[this.value[j].length];
                System.arraycopy(this.value[j], 0, temp, 0, this.value[j].length);
                int tempCount = temp.length;
                for (Object val : this.value[i]) {
                    boolean foundElement = false;
                    for (int k = 0; k < tempCount; k++)
                        if (val == temp[k]) {
                            Object swap = temp[k];
                            temp[k] = temp[tempCount - 1];
                            temp[tempCount - 1] = swap;
                            tempCount--;                            
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
