
package testdebugpo_lab;

import static java.lang.System.currentTimeMillis;
import java.util.ArrayList;
import java.util.Random;

public class TestDebugPO_Lab {

    public static class Arr {
        private ArrayList<Float> items;
        private ArrayList<Float> newItems;
        private final float c1;
        private final float c2;
        
        Arr(int size, float min, float max, float c1, float c2) {
            this.c1 = c1;
            this.c2 = c2;
            Random random = new Random(currentTimeMillis());
            
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
    }
    
}
