package moii_kursrab;

import static java.lang.Math.abs;
import java.util.Arrays;

public class TableState {
    
    public static enum MoveDirection {
        UP, RIGHT, DOWN, LEFT
    }
    
    public  int[][] table;
    private int     emptyRow;
    private int     emptyCol;
    public  int     heuristicDistance;
    public  int     heuristicDepth;
    
    public TableState(int[][] table) {
        this.table = table;
        this.heuristicDistance = 0;
        this.heuristicDepth = 0;
        for (int i = 0; i < this.table.length; i++)
            for (int j = 0; j < this.table.length; j++)
                if (this.table[i][j] == 0) {
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
        this.table = currentState.table.clone();
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
        this.table[currentState.emptyRow][currentState.emptyCol] = 
            this.table[this.emptyRow][this.emptyCol];
        this.table[this.emptyRow][this.emptyCol] = 0;
    }
    private void calculateHeuristicDistance(TableState goalState) {
        this.heuristicDistance = 0;
        int n = this.table.length;
        for (int iCurrent = 0; iCurrent < n; iCurrent++)
            for (int jCurrent = 0; jCurrent < n; jCurrent++)
                for (int iGoal = 0; iGoal < n; iGoal++)
                    for (int jGoal = 0; jGoal < n; jGoal++)
                        if (this.table[iCurrent][jCurrent] ==
                                goalState.table[iGoal][jGoal]) {
                            this.heuristicDistance += abs(iGoal - iCurrent) + 
                                    abs(jGoal - jCurrent);
                        }
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TableState other = (TableState) obj;
        if (this.emptyRow != other.emptyRow) {
            return false;
        }
        if (this.emptyCol != other.emptyCol) {
            return false;
        }
        if (!Arrays.deepEquals(this.table, other.table)) {
            return false;
        }
        return true;
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Arrays.deepHashCode(this.table);
        hash = 37 * hash + this.emptyRow;
        hash = 37 * hash + this.emptyCol;
        return hash;
    }
    
}