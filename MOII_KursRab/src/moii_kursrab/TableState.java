package moii_kursrab;

enum MoveDirection {
    UP, RIGHT, DOWN, LEFT
}

class Table {
    public byte[][] value;
    public Table(byte[][] value) {
        this.value = value;
    }
}

public class TableState {
    
    public Table table;
    private byte emptyRow;
    private byte emptyCol;
    public byte heuristicDistance;
    public byte heuristicDepth;
    
    public TableState(final Table table, final byte heuristicDistance,
            final byte heuristicDepth) {
        this.table = table;
        this.heuristicDistance = heuristicDistance;
        this.heuristicDepth = heuristicDepth;
        for (byte i = 0; i < this.table.value.length; i++)
            for (byte j = 0; j < this.table.value.length; j++)
                if (this.table.value[i][j] == 0) {
                    this.emptyRow = i;
                    this.emptyCol = j;
                }
    }

    public TableState(TableState currentState, MoveDirection moveDirection) 
            throws ArrayIndexOutOfBoundsException {
        try {
            this.table = currentState.move(moveDirection);
            
        } catch (ArrayIndexOutOfBoundsException e) {
            throw e;
        }
    }
    private Table move(MoveDirection moveDirection)
            throws ArrayIndexOutOfBoundsException {
        Table newTable = new Table(this.table.value.clone());
        byte newEmptyRow = this.emptyRow;
        byte newEmptyCol = this.emptyCol;
        switch (moveDirection) {
            case UP:
                newEmptyRow--;
                break;
            case RIGHT:
                newEmptyCol++;
                break;
            case DOWN:
                newEmptyRow++;
                break;
            case LEFT:
                newEmptyCol--;
                break;
        }
        try {
            newTable.value[this.emptyRow][this.emptyCol] = 
                newTable.value[newEmptyRow][newEmptyCol];
            newTable.value[newEmptyRow][newEmptyCol] = 0;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw e;
        }        
        return newTable;
    }
    
}
