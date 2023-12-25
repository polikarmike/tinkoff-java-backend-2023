package edu.hw9.task3;

import edu.project2.Solvers.MazeSolver;
import edu.project2.Types.Cell;
import edu.project2.Types.Maze;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class MultiThreadedDFSMazeSolver implements MazeSolver {
    private final ForkJoinPool executor;

    public MultiThreadedDFSMazeSolver() {
        executor = new ForkJoinPool();
    }

    public List<Cell> solveMaze(Maze maze) {
        Cell start = maze.start;
        Cell exit = maze.exit;

        if (start == null || exit == null) {
            return null;
        }

        DFSRecursiveTask task = new DFSRecursiveTask(maze, start, exit, new ArrayList<>());
        List<Cell> result = executor.invoke(task);
        maze.setMazeUnvisited();
        executor.shutdown();
        return result;
    }
}
