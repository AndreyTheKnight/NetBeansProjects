package moii_kursrab;

public class MOII_KursRab {

    public static void main(String[] args) {
        int[][] init =  {{1, 2, 3}, 
                         {5, 6, 4}, 
                         {7, 8, 0}};
        int[][] goal =  {{1, 2, 3}, 
                         {4, 5, 6}, 
                         {7, 8, 0}};
        Puzzle puzzle = new Puzzle(init, goal);
        TableState[] solution = puzzle.solve();
        if (solution == null) 
            System.out.println("Нерешаемо!");
        else {
            String outString = "";
            for (int line = 0; line < init.length; line++) {
                for (TableState state : solution) {
                    for (int cell : state.table[line])
                        outString += cell + " ";
                    outString += " >  ";
                }
                outString = outString.substring(0, outString.length()-4) + "\n";
            }
            System.out.print(outString);
        }
    }
    
}
