package moii_kursrab;

import static java.lang.Math.abs;

public class TableState {
    
    public static enum MoveDirection {
        UP, RIGHT, DOWN, LEFT
    }
    public static class Table {
        public int[][] value;
        public Table(int[][] value) {
            this.value = value;
        }
    }
    
    public  Table   table;
    private int     emptyRow;
    private int     emptyCol;
    public  int     heuristicDistance;
    public  int     heuristicDepth;
    
    public TableState(final Table table, final int heuristicDistance,
            final int heuristicDepth) {
        this.table = table;
        this.heuristicDistance = heuristicDistance;
        this.heuristicDepth = heuristicDepth;
        for (int i = 0; i < this.table.value.length; i++)
            for (int j = 0; j < this.table.value.length; j++)
                if (this.table.value[i][j] == 0) {
                    this.emptyRow = i;
                    this.emptyCol = j;
                }
    }

    public TableState(TableState currentState, MoveDirection moveDirection, 
            TableState goalState)
            throws ArrayIndexOutOfBoundsException {
        try {
            this.move(currentState, moveDirection);
            this.calculateHeuristicDistance(goalState);
            this.heuristicDepth = currentState.heuristicDepth + 1;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw e;
        }
    }
    private void move(TableState currentState, MoveDirection moveDirection)
            throws ArrayIndexOutOfBoundsException {
        this.table = new Table(currentState.table.value.clone());
        this.emptyRow = currentState.emptyRow;
        this.emptyCol = currentState.emptyCol;
        switch (moveDirection) {
            case UP:
                this.emptyRow--;
                break;
            case RIGHT:
                this.emptyCol++;
                break;
            case DOWN:
                this.emptyRow++;
                break;
            case LEFT:
                this.emptyCol--;
                break;
        }
        this.table.value[currentState.emptyRow][currentState.emptyCol] = 
            this.table.value[this.emptyRow][this.emptyCol];
        this.table.value[this.emptyRow][this.emptyCol] = 0;
    }
    private void calculateHeuristicDistance(TableState goalState) {
        this.heuristicDistance = 0;
        int n = this.table.value.length;
        for (int iCurrent = 0; iCurrent < n; iCurrent++)
            for (int jCurrent = 0; jCurrent < n; jCurrent++)
                for (int iGoal = 0; iGoal < n; iGoal++)
                    for (int jGoal = 0; jGoal < n; jGoal++)
                        if (this.table.value[iCurrent][jCurrent] ==
                                goalState.table.value[iGoal][jGoal]) {
                            this.heuristicDistance += abs(iGoal - iCurrent) + 
                                    abs(jGoal - jCurrent);
                        }
    }
}
