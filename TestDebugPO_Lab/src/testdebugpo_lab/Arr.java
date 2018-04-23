
package testdebugpo_lab;

import java.util.ArrayList;

public class Arr {
    
    private final double c1;
    private final double c2;
    private final ArrayList<Double> items;
    
    Arr(double c1, double c2) {
        this.items = new ArrayList<>();
        this.c1 = c1;
        this.c2 = c2;
    }
    
    public void add(double item) {
        this.items.add(item);
    }
    
    public void findAndReplace() {
        double min = this.c2;
        ArrayList<Integer> markedItems = new ArrayList<>();
        for (int i = 0; i < this.items.size(); i++) {
            if ((this.c1 <= this.items.get(i)) &&
                    (this.items.get(i) <= this.c2)) {
                markedItems.add(i);
                min = (min > this.items.get(i)) ? this.items.get(i) : min;
            }
        }
        for (int curMarkedItem : markedItems)
            this.items.set(curMarkedItem, min);
    }
    
    public void printItems() {
        this.items.forEach((currentItem) -> {
            System.out.print(currentItem + " ");
        });
        System.out.println();
    }
    
}
