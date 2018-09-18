package moii_kursrab;

public class MOII_KursRab {

    public static void main(String[] args) {
        int[][] init = {{1, 2, 3}, 
                        {5, 6, 4}, 
                        {7, 8, 0}};
        int[][] goal = {{1, 2, 3}, 
                        {4, 5, 6}, 
                        {7, 8, 0}};
        Puzzle puzzle = new Puzzle(init, goal);
        TableState[] solution = puzzle.solve();
        if (solution == null) 
            System.out.println("Нерешаемо!");
        else
            for (TableState state : solution) {
                for (int[] line : state.table) {
                    for (int cell : line)
                        System.out.print(cell + " ");
                    System.out.println();
                }
                System.out.println();
            }
    }
    
}
