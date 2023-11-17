package edu.project2.Solvers;

import edu.project2.Types.Cell;
import edu.project2.Types.Maze;
import java.util.List;

public interface MazeSolver {
    List<Cell> solveMaze(Maze maze);
}
