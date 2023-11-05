package edu.project2.Solvers;

import edu.project2.Types.Cell;
import edu.project2.Types.Maze;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class BFSMazeSolver {

    private final Maze maze;
    private final Queue<Cell> queue;

    public BFSMazeSolver(Maze maze) {
        this.maze = maze;
        this.queue = new LinkedList<>();
    }

    public List<Cell> solveMaze() {
        Cell start = maze.start;
        Cell exit = maze.exit;

        if (start == null || exit == null) {
            return null;
        }

        start.setVisited(true);
        queue.offer(start);

        while (!queue.isEmpty()) {
            Cell currentCell = queue.poll();

            if (currentCell == exit) {
                return reconstructPath(currentCell);
            }

            List<Cell> neighbors = maze.getNeighbors(currentCell, 1);
            for (Cell neighbor : neighbors) {
                if (!neighbor.isVisited() && !neighbor.isWall()) {
                    neighbor.setVisited(true);
                    neighbor.setParent(currentCell);
                    queue.offer(neighbor);
                }
            }
        }

        return null;
    }

    private List<Cell> reconstructPath(Cell finish) {
        List<Cell> path = new LinkedList<>();
        Cell currentCell = finish;
        while (currentCell != null) {
            path.add(currentCell);
            currentCell = currentCell.getParent();
        }
        Collections.reverse(path);
        return path;
    }

}
