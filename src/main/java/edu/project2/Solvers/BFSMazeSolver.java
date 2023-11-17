package edu.project2.Solvers;

import edu.project2.Types.Cell;
import edu.project2.Types.Maze;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class BFSMazeSolver implements MazeSolver {
    public BFSMazeSolver() {
    }

    public List<Cell> solveMaze(Maze maze) {
        Cell start = maze.start;
        Cell exit = maze.exit;

        if (start == null || exit == null) {
            return null;
        }

        Queue<Cell> queue = new LinkedList<>();
        start.setVisited(true);
        queue.offer(start);

        try {
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
        } finally {
            maze.setMazeUnvisited();
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
