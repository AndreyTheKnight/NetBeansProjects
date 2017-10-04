/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArhEVM;

/**
 *
 * @author andre
 */
public class MyArr {
    private int[] value;
    private int indexOfMax;
    
    public MyArr(int n, int min, int max) {
        this.value = new int[n];
        for (int i = 0; i < n; i++)
            this.value[i] = (int)Math.round(min + Math.random()*(max-min));
    }
    public MyArr(int[] arr) {
        this.value = new int[arr.length];
        this.indexOfMax = 0;
        int sum = 0;
        for (int i = 0; i < this.value.length; i++) {
            sum += arr[i];
            this.value[i] = sum;
            if (this.value[i] > this.value[this.indexOfMax])
                this.indexOfMax = i;
        }
    }
    public final void RemoveMax() {
        int[] newArr = new int[this.value.length - 1];
        System.arraycopy(this.value, 0, newArr, 0, this.indexOfMax);
        try {
            System.arraycopy(this.value, this.indexOfMax + 1, 
                    newArr, this.indexOfMax, this.value.length - this.indexOfMax + 1);
        } catch (IndexOutOfBoundsException e) {
            this.value = newArr;
        }
    }
}
