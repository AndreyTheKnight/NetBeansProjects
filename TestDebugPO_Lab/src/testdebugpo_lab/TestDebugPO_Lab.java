
package testdebugpo_lab;

import java.util.Random;
import java.util.Scanner;

public class TestDebugPO_Lab {
    
    public static Arr randArr(double c1, double c2, int count, double min,
            double max) {
        Arr arr = new Arr(c1, c2);
        Random random = new Random(System.currentTimeMillis());        
        for (int i = 0; i < count; i++)
            arr.add(min + random.nextFloat()*(max - min));
        return arr;
    }
    
    public static Arr manualArr() {
        int count = 0;
        Double[] c = new Double[2];
        Scanner scan = new Scanner(System.in);
        do {
            c[0] = c[1] = Double.NaN;
            for (int i = 0; i < c.length; i++)
                while (c[i].isNaN() || c[i].isInfinite())
                    try {
                        System.out.print("Введите c" + (i + 1) + ": ");
                        String buff = scan.nextLine();
                        c[i] = new Double(buff);
                    } catch (NumberFormatException e) {
                        System.out.println("Введено недопустимое значение!");
                    }
            if (c[0] > c[1])
                System.out.println("с1 не может быть больше с2!");
            else
                break;
        } while (true);
        Arr arr = new Arr(c[0], c[1]);
        String buff = "";
        while (true)
            try {
                count++;
                System.out.print("Введите " + count + "-ый элемент или пустую строку для завершнеия: ");
                buff = scan.nextLine();
                arr.add(new Double(buff));
            } catch (NumberFormatException e) {
                if (buff.isEmpty()) return arr;
                System.out.println("Введено недопустимое значение!");
                count--;
            }
    }
    
    public static void main(String[] args) {
        
        //Arr arr = randArr(-3, 3, 10, -10, 10);
        Arr arr = manualArr();
        System.out.println("Исходный массив");
        arr.printItems();
        System.out.println("Обработанный массив");
        arr.findAndReplace();
        arr.printItems();
        
    }
    
}
