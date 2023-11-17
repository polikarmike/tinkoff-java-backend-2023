package edu.project2.Generators;

import edu.project2.Types.Cell;
import edu.project2.Types.Maze;
import java.util.Collections;
import java.util.List;


public class RecursiveBacktrackingMazeGenerator implements MazeGenerator {
    private final Maze maze;

    public RecursiveBacktrackingMazeGenerator(int width, int height) {
        this.maze = new Maze(width, height);
    }

    public Maze generateMaze() {
        maze.fillWithWalls();

        Cell startCell = maze.cells[1][1];

        recursiveBacktracking(maze, startCell);

        return maze;
    }

    public static void recursiveBacktracking(Maze maze, Cell currnetCell) {
        currnetCell.setType(Cell.CellType.EMPTY);

        List<Cell> neighbors = maze.getNeighbors(currnetCell, 2);
        Collections.shuffle(neighbors);

        for (Cell neighborCell : neighbors) {
            if (neighborCell.isWall()) {
                maze.removeWallsBetweenCells(currnetCell, neighborCell);
                recursiveBacktracking(maze, neighborCell);
            }
        }
    }
}

