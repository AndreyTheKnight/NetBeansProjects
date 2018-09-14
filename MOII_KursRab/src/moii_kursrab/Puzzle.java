package moii_kursrab;

import java.util.Stack;

public class Puzzle {
    
    private final   TableState          initState;
    private final   TableState          goalState;
    public          Stack<TableState>   solution;
    
    public Puzzle(int[][] initTable, int[][] goalTable) {
        this.initState = new TableState(initTable);
        this.goalState = new TableState(goalTable);
    }
    private boolean isSolvable() {
        int sum = 0; int index = 0;
        int[] tableAsVector = new int[this.initState.table.length*this.initState.table[0].length];
        for (int[] tableLine : this.initState.table)
            for (int tableCell : tableLine)
                tableAsVector[index++] = tableCell;
        for (int i1 = 0; i1 < tableAsVector.length - 1; i1++)
            for (int i2 = i1 + 1; i2 < tableAsVector.length; i2++)
                if ((tableAsVector[i1] > tableAsVector[i2]) && 
                        (tableAsVector[i2] != 0)) sum++;
        return ((sum + this.initState.emptyRow + 1) % 2 == 0);
    }
    public void solve() {
        if (this.isSolvable()) {
            Stack<TableState> open = new Stack();
            Stack<TableState> closed = new Stack();
            open.push(this.initState);
            while (!open.peek().equals(this.goalState)) {
                for (TableState.MoveDirection moveDirection : TableState.MoveDirection.values())
                    try {
                        TableState newState = new TableState(open.peek(), moveDirection, this.goalState);
                        try {
                            int idx = closed.indexOf(newState);
                            if (newState.heuristicDepth < closed.get(idx).heuristicDepth)
                                closed.set(idx, closed.pop());
                        } catch (ArrayIndexOutOfBoundsException e1) {
                            try {
                                int idx = open.indexOf(newState);
                                if (newState.heuristicDepth < open.get(idx).heuristicDepth)
                                    open.set(idx, newState);
                            } catch (ArrayIndexOutOfBoundsException e2) {
                                open.push(newState);
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {}
                closed.push(open.pop());
                open.sort((state1, state2) -> Integer.compare(state1.heuristicDepth, state2.heuristicDepth));
            }
            for (TableState cursor = open.peek(); cursor != null; cursor = cursor.previousState)
                this.solution.push(cursor);
            
        }
    }
}
