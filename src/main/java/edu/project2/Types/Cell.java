package edu.project2.Types;

public class Cell {
    private final int row;
    private final int column;
    private boolean isWall;
    private boolean isStart;
    private boolean isExit;
    private boolean visited;
    private Cell parent;

    public Cell(int row, int column) {
        this(row, column, false);
    }

    public Cell(int row, int column, boolean isWall) {
        this.row = row;
        this.column = column;
        this.isWall = isWall;
        this.isStart = false;
        this.isExit = false;
    }

    public boolean isWall() {
        return isWall;
    }

    public boolean isClear() {
        return !isWall && !isExit && !isStart;
    }

    public void setWall(boolean isWall) {
        this.isWall = isWall;
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

    public void setStart(boolean isStart) {
        this.isStart = isStart;
    }

    public boolean isStart() {
        return isStart;
    }

    public void setExit(boolean isExit) {
        this.isExit = isExit;
    }

    public boolean isExit() {
        return isExit;
    }
}
