package moii_kursrab;

public class Puzzle {
    
    private final   TableState  initState;
    private final   TableState  goalState;
    
    public Puzzle(int[][] initTable, int[][] goalTable) {
        this.initState = new TableState(initTable);
        this.goalState = new TableState(goalTable);
    }
    public void solve() {
        
    }
    
}
