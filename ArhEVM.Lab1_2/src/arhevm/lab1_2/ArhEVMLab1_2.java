package arhevm.lab1_2;

public class ArhEVMLab1_2 {

    public static void main(String[] args) {
        
        MyArr arr = new MyArr(7, 5, -1, 1);
        System.out.println("   Массив:");
        arr.print();
        int[] find = arr.find();
        System.out.println();
        if (find == null)
            System.out.println("Строк, в которых элементы одинаковы, но могут "
                    + "стоять в различной последовательности не найдено!");
        else
            System.out.println("Строки, в которых элементы одинаковы, но могут "
                    + "стоять в различной последовательности: " + (find[0]+1) + " и " + (find[1]+1));
    }
    
}
