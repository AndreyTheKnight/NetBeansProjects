package moii_kursrab;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Puzzle {
    
    private final   TableState  initState;
    private final   TableState  goalState;
    
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
    public TableState[] solve() {
        if (!this.isSolvable()) return null;
        PriorityQueue<TableState> open = new PriorityQueue<>((state1, state2) -> 
                        Integer.compare(state1.getHeuristic(), state2.getHeuristic()));
        ArrayList<TableState> closed = new ArrayList();
        for (open.add(this.initState); !open.peek().equals(this.goalState); closed.add(open.poll())) {
            ArrayList<TableState> newStates = new ArrayList();
            for (TableState.MoveDirection moveDirection : TableState.MoveDirection.values())
                try {
                    TableState newState = new TableState(open.peek(), moveDirection, this.goalState);
                    try {
                        int idx = closed.indexOf(newState);
                        if (newState.hasBetterHeuristic(closed.get(idx))) {
                            closed.set(idx, closed.get(closed.size()-1));
                            closed.remove(closed.size()-1);
                        }
                    } catch (ArrayIndexOutOfBoundsException e1) {}
                } catch (ArrayIndexOutOfBoundsException e) {}
            open.addAll(newStates);
        }
        ArrayList<TableState> solution = new ArrayList();
        for (TableState cursor = open.peek(); cursor != null; cursor = cursor.previousState)
            solution.add(cursor);
        return solution.toArray(new TableState[solution.size()]);
    }
}
