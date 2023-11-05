package edu.project2.Solvers;

import edu.project2.Types.Cell;
import edu.project2.Types.Maze;
import java.util.ArrayList;
import java.util.List;

public class DFSMazeSolver {
    private final Maze maze;

    public DFSMazeSolver(Maze maze) {
        this.maze = maze;
    }

    public List<Cell> solveMaze() {
        Cell start = maze.start;
        Cell exit = maze.exit;

        if (start == null || exit == null) {
            return null;
        }

        List<Cell> result = new ArrayList<>();
        boolean success = depthFirstSearch(start, exit, result);

        if (!success) {
            return null;
        } else {
            return result;
        }
    }

    private boolean depthFirstSearch(Cell currentCell, Cell exit, List<Cell> path) {
        path.add(currentCell);
        currentCell.setVisited(true);

        if (currentCell == exit) {
            return true;
        }

        List<Cell> neighborsList = maze.getNeighbors(currentCell, 1);


        for (Cell neighborCell : neighborsList) {
            if (isValidMove(neighborCell)) {
                if (depthFirstSearch(neighborCell, exit, path)) {
                    return true;
                }
            }
        }

        if (!path.isEmpty()) {
            path.remove(path.size() - 1);
        }
        return false;
    }

    private boolean isValidMove(Cell cell) {
        return !cell.isWall() && !cell.isVisited();
    }

}
