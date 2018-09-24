package moii_kursrab;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Puzzle {
    
    private final   TableState  initState;
    private final   TableState  goalState;
    
    public Puzzle(int[][] initTable, int[][] goalTable) {
        this.initState = new TableState(initTable);
        this.goalState = new TableState(goalTable);
    }
    private boolean isSolvable() {
        int sum = 0;
        int dim = this.initState.table.length;
        int n = (int)Math.pow(dim, 2);
        for (int i = 0; i < n-1; i++)
            for (int j = i + 1; j < n; j++)
                if ((this.initState.table[i/dim][i%dim] > this.initState.table[j/dim][j%dim]) 
                        && (this.initState.table[j/dim][j%dim] > 0))
                    sum++;
        return (dim % 2 != 0) ? (sum % 2 == 0) : ((sum + this.initState.emptyRow + 1) % 2 == 0);
    }
    public TableState[] solve() {
        if (!this.isSolvable()) return null;
        PriorityQueue<TableState> open = new PriorityQueue<>((state1, state2) -> 
                        Integer.compare(state1.getHeuristic(), state2.getHeuristic()));
        ArrayList<TableState> closed = new ArrayList<>();
        try {
            for (open.add(this.initState); !open.peek().equals(this.goalState); ) {
                closed.add(open.poll());
                LinkedList<TableState> newStates = new LinkedList<>();
                for (TableState.MoveDirection moveDirection : TableState.MoveDirection.values())
                    try {
                        newStates.addFirst(new TableState(closed.get(closed.size()-1), moveDirection, this.goalState));
                        int idx = closed.indexOf(newStates.peekFirst());
                        if (newStates.peekFirst().hasBetterHeuristic(closed.get(idx))) {
                            closed.set(idx, closed.get(closed.size()-1));
                            closed.remove(closed.size()-1);
                        } else
                            newStates.removeFirst();
                    } catch (ArrayIndexOutOfBoundsException e) {}
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
