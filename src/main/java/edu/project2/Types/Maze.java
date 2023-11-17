package edu.project2.Types;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Maze {
    public static final int MIN_MAZE_SIDE_SIZE = 5;
    public final Cell[][] cells;
    private final int width;
    private final int height;
    public Cell start = null;
    public Cell exit = null;

    public Maze(int width, int height) {
        if (width < MIN_MAZE_SIDE_SIZE || height < MIN_MAZE_SIDE_SIZE) {
            throw new IllegalArgumentException("Ширина и высота лабиринта должны быть больше или равны 5.");
        }

        this.width = width / 2 * 2 + 1;
        this.height = height / 2 * 2 + 1;
        this.cells = initializeCells(this.width, this.height);
    }

    private Cell[][] initializeCells(int width, int height) {
        Cell[][] cellsArray = new Cell[height][width];
        for (int col = 0; col < height; col++) {
            for (int row = 0; row < width; row++) {
                Cell cell = new Cell(col, row);
                cellsArray[col][row] = cell;
            }
        }
        return cellsArray;
    }

    public void setMazeUnvisited() {
        for (int col = 0; col < height; col++) {
            for (int row = 0; row < width; row++) {
                this.cells[col][row].setVisited(false);
                this.cells[col][row].setParent(null);
            }
        }
    }

    public void fillWithWalls() {
        clearStartAndExit();

        for (int col = 0; col < this.height; col++) {
            for (int row = 0; row < this.width; row++) {
                this.cells[col][row].setType(Cell.CellType.WALL);
            }
        }

    }

    private void clearStartAndExit() {
        if (start != null) {
            this.start = null;
        }

        if (exit != null) {
            this.exit = null;
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


    public void setStart(int row, int col) {
        this.start = cells[row][col];
    }

    public void setExit(int row, int col) {
        this.exit = cells[row][col];
    }

    public void setRandomStartExit() {
        Random random = new Random();
        int numRows = height;
        int numCols = width;

        int startRow;
        int startCol;
        int exitRow;
        int exitCol;


        do {
            startRow = random.nextInt(numRows / 2);
            startCol = random.nextInt(numCols / 2);
        } while (cells[startRow][startCol].isWall());


        do {
            exitRow = random.nextInt(numRows);
            exitCol = random.nextInt(numCols);
        } while (cells[exitRow][exitCol].isWall() || (startRow == exitRow && startCol == exitCol));


        cells[startRow][startCol].setType(Cell.CellType.START);
        setStart(startRow, startCol);
        cells[exitRow][exitCol].setType(Cell.CellType.EXIT);
        setExit(exitRow, exitCol);
    }

    public void removeWallsBetweenCells(Cell currentCell, Cell neighborCell) {
        int nx = neighborCell.getColumn();
        int ny = neighborCell.getRow();
        int cx = currentCell.getColumn();
        int cy = currentCell.getRow();
        this.cells[(ny + cy) / 2][(nx + cx) / 2].setType(Cell.CellType.EMPTY);
    }



    public List<Cell> getNeighbors(Cell cell, int steps) {
        int currentX = cell.getRow();
        int currentY = cell.getColumn();
        List<Cell> neighborsList = new ArrayList<>();

        int[][] directions = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
        };

        for (int[] direction : directions) {
            int neighborX = currentX + direction[0] * steps;
            int neighborY = currentY + direction[1] * steps;

            if (inBounds(neighborX, neighborY)) {
                neighborsList.add(cells[neighborX][neighborY]);
            }
        }

        return neighborsList;
    }

    private boolean inBounds(int row, int col) {
        return row >= 0 && row < height && col >= 0 && col < width;
    }

}
