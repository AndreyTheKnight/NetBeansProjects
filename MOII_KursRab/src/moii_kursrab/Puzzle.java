package moii_kursrab;

import java.util.LinkedList;

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
        LinkedList<TableState> open = new LinkedList(), 
                closed = new LinkedList();
        open.addLast(this.initState);
        while (!open.peekFirst().equals(this.goalState)) {
            for (TableState.MoveDirection moveDirection : TableState.MoveDirection.values())
                try {
                    TableState newState = new TableState(open.peekFirst(), moveDirection, this.goalState);
                    if (!open.removeIf((state) -> state.equals(newState) && state.heuristicDepth > newState.heuristicDepth))
                        closed.removeIf((state) -> state.equals(newState) && state.heuristicDepth > newState.heuristicDepth);
                    open.addLast(newState);
                } catch (ArrayIndexOutOfBoundsException e) {}
            closed.addLast(open.pollFirst());
            open.sort((state1, state2) -> Integer.compare(state1.heuristicDepth + state1.heuristicDistance, 
                    state2.heuristicDepth + state2.heuristicDistance));
        }
        int i = open.peekFirst().heuristicDepth; TableState[] solution = new TableState[i + 1];
        for (TableState cursor = open.peekFirst(); cursor != null; cursor = cursor.previousState)
            solution[i--] = cursor;
        return solution;
    }
}
