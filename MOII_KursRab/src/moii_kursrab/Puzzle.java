package moii_kursrab;

import java.util.List;

public class Puzzle {
    
    private final   TableState  initState;
    private final   TableState  goalState;
    private         List        open;
    private         List        closed;
    
    public Puzzle(int[][] initTable, int[][] goalTable) {
        this.initState = new TableState(initTable);
        this.goalState = new TableState(goalTable);
    }
    public void solve() {
        
    }
    
}
