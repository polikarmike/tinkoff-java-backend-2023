package edu.hw9.task3;

import edu.project2.Types.Cell;
import edu.project2.Types.Maze;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class DFSRecursiveTask extends RecursiveTask<List<Cell>> {
    private Maze maze;
    private Cell currentCell;
    private Cell exit;
    private List<Cell> path;

    public DFSRecursiveTask(Maze maze, Cell currentCell, Cell exit, List<Cell> path) {
        this.maze = maze;
        this.currentCell = currentCell;
        this.exit = exit;
        this.path = new ArrayList<>(path);
    }

    @Override
    protected List<Cell> compute() {
        path.add(currentCell);
        currentCell.setVisited(true);

        if (currentCell == exit) {
            return path;
        }

        List<Cell> neighborsList = maze.getNeighbors(currentCell, 1);
        List<DFSRecursiveTask> tasks = new ArrayList<>();

        for (Cell neighborCell : neighborsList) {
            if (isValidMove(neighborCell)) {
                DFSRecursiveTask task = new DFSRecursiveTask(maze, neighborCell, exit, path);
                tasks.add(task);
                task.fork();
            }
        }

        for (DFSRecursiveTask task : tasks) {
            List<Cell> result = task.join();
            if (result != null) {
                return result;
            }
        }

        return null;
    }

    private boolean isValidMove(Cell cell) {
        return !cell.isWall() && !cell.isVisited();
    }
}
