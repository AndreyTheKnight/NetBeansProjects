package moii_kursrab;

public class MOII_KursRab {

    public static void main(String[] args) {
        /*int[][] init = {{1, 2, 3}, 
                        {4, 5, 8}, 
                        {7, 6, 0}};
        int[][] goal = {{1, 2, 3}, 
                        {4, 5, 6}, 
                        {7, 8, 0}};*/
        int[][] init = {{2, 3}, 
                        {1, 0}};
        int[][] goal = {{1, 2}, 
                        {3, 0}};
        Puzzle puzzle = new Puzzle(init, goal);
        puzzle.solve();
        System.out.println("Inint state:");
        for (int[] line : init) {
            for (int cell : line)
                System.out.print(cell + " ");
            System.out.println();
        }
        System.out.println();
        System.out.println("Goal state:");
        for (int[] line : goal) {
            for (int cell : line)
                System.out.print(cell + " ");
            System.out.println();
        }
        System.out.println();
        if (puzzle.solution != null)
            puzzle.solution.forEach((_item) -> {
                for (int[] line :_item.table) {
                    for (int cell : line)
                        System.out.print(cell + " ");
                    System.out.println();
                }
                System.out.println();
            });
        else
            System.out.println("Unsolvable!");
    }
    
}
