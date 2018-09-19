package moii_kursrab;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
        //return (sum % 2 == 0); нечет n
        //return ((sum + this.initState.emptyRow + 1) % 2 == 0); чет n
        return (this.initState.table.length % 2 != 0) ? (sum % 2 == 0) : 
                ((sum + this.initState.emptyRow + 1) % 2 == 0);
    }
    public TableState[] solve() {
        if (!this.isSolvable()) return null;
        PriorityQueue<TableState> open = new PriorityQueue<>((state1, state2) -> 
                        Integer.compare(state1.getHeuristic(), state2.getHeuristic()));
        ArrayList<TableState> closed = new ArrayList<>();
        try {
            for (open.add(this.initState); !open.peek().equals(this.goalState); ) {
                LinkedList<TableState> newStates = new LinkedList<>();
                for (TableState.MoveDirection moveDirection : TableState.MoveDirection.values())
                    try {
                        newStates.addFirst(new TableState(open.peek(), moveDirection, this.goalState));
                        int idx = closed.indexOf(newStates.peekFirst());
                        if (newStates.peekFirst().hasBetterHeuristic(closed.get(idx))) {
                            closed.set(idx, closed.get(closed.size()-1));
                            closed.remove(closed.size()-1);
                        } else
                            newStates.removeFirst();
                    } catch (ArrayIndexOutOfBoundsException e) {}
                closed.add(open.poll());
                open.addAll(newStates);
            }
        } catch (NullPointerException e) {
            return null;
        }
        LinkedList<TableState> solution = new LinkedList<>();
        for (TableState cursor = open.peek(); cursor != null; cursor = cursor.previousState)
            solution.addFirst(cursor);
        return solution.toArray(new TableState[solution.size()]);
    }
}
