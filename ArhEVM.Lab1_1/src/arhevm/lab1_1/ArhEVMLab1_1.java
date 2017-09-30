package arhevm.lab1_1;

import java.io.PrintStream;

public class ArhEVMLab1_1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        class MyArr {
            private final int[] value;
            MyArr(int[] arr) {
                this.value = arr;
            }
            MyArr(int minRand, int maxRand) {
                this.value = new int[10];
                for (byte i = 0; i < this.value.length; ++i)
                    this.value[i] = (int)(minRand + Math.random() * (maxRand - minRand));
            }
            public final void print() {
                for (byte i = 0; i < this.value.length; ++i)
                    System.out.print("X[" + i + "] = " + this.value[i] + ";  ");
                System.out.println();
            }
            public final void swap() {
                byte cond;
                for (byte i = 0; i < this.value.length; ++i) {
                    //if ((this.value[i] & 0b101) == 0b101)
                    cond = (byte)((this.value[i] & 0b101)/0b101);
                    this.value[i] = this.value[i] *(cond ^ 0b1) + 
                            cond * ((this.value[i] << 24) | 
                            (((this.value[i] >>> 7) << 24) >>> 7) | 
                            (((this.value[i] << 8) >>> 23) << 8) | 
                            (this.value[i] >>> 23));
                }
            }
            public final byte count() {
                byte count = 0;
                for (int val : this.value)
                    //if ((val < 0) & ((val & 0b1) == 0b1))
                    //    ++count;
                    count += (byte)((val >>> 31) * (val & 0b1));
                return count;
            }
        }
        
        PrintStream cout = System.out;
        MyArr myArr = new MyArr(-100, 200);
        //MyArr myArr = new MyArr(new int[] {-41});
        cout.println("   Исходный массив:");
        myArr.print();
        myArr.swap();
        cout.println("\n   Обработанный массив:");
        myArr.print();
        cout.println("\nКоличество отрицательных чисел, у которых \"1\" " +
                "в первом бите младшего байта: " + myArr.count());
        
    }
    
}
