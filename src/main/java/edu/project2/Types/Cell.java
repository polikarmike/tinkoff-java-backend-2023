package edu.project2.Types;

public class Cell {
    private final int row;
    private final int column;
    private boolean visited;
    private Cell parent;
    private CellType type;

    public enum CellType {
        EMPTY,
        START,
        EXIT,
        WALL
    }

    public Cell(int row, int column) {
        this(row, column, CellType.EMPTY);
    }

    public Cell(int row, int column, CellType type) {
        this.row = row;
        this.column = column;
        this.type = type;
    }

    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        this.type = type;
    }

    public boolean isWall() {
        return type == CellType.WALL;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Cell getParent() {
        return parent;
    }

    public void setParent(Cell parent) {
        this.parent = parent;
    }

    public boolean isStart() {
        return type == CellType.START;
    }

    public boolean isExit() {
        return type == CellType.EXIT;
    }
}
