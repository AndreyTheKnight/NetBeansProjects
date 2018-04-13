
package testdebugpo_lab;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestDebugPO_Lab {

    public static class Arr {
        private final double[] items;
        
        Arr(double[] items) {
            this.items = items;
        }        
        Arr(int size, double min, double max) {
            this.items = new double[size];
            Random random = new Random(System.currentTimeMillis());
            for (int i = 0; i < size; i++)
                this.items[i] = min + random.nextFloat()*(max - min);
        }
        public void findAndReplace(double c1, double c2) {
            double min = c2;
            List<Integer> markedItems = new ArrayList<>();
            for (int i = 0; i < this.items.length; i++)
                if ((c1 <= this.items[i]) && (this.items[i] <= c2)) {
                    markedItems.add(i);
                    if (this.items[i] < min)
                        min = this.items[i];
                }
            for (int currentMarkedItem : markedItems)
                this.items[currentMarkedItem] = min;
        }
        public void printItems() {
            for (double currentItem : this.items)
                System.out.print(currentItem + " ");
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        Arr arr = new Arr(10, -5.2, 8.3);
        System.out.println("Исходный массив");
        arr.printItems();
        System.out.println("Обработанный массив");
        arr.findAndReplace(-1, 1);
        arr.printItems();
    }
    
}
