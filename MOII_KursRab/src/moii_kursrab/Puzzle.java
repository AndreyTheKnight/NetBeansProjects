package moii_kursrab;

import java.util.ArrayList;

public class Puzzle {
    
    private final   TableState  initState;
    private final   TableState  goalState;
    private         ArrayList   open;
    private         ArrayList   closed;
    
    public Puzzle(int[][] initTable, int[][] goalTable) {
        this.initState = new TableState(initTable);
        this.goalState = new TableState(goalTable);
    }
    public void solve() {
        if (this.initState.isSolvable()) {
            this.open.add(this.initState);
            while (!this.open.get(0).equals(this.goalState)) {
                
            }
        }
    }
    
}
