package edu.project2;

import edu.project2.Generators.PrimsMazeGenerator;
import edu.project2.Generators.RecursiveBacktrackingMazeGenerator;
import edu.project2.Solvers.BFSMazeSolver;
import edu.project2.Solvers.DFSMazeSolver;
import edu.project2.Types.Cell;
import edu.project2.Types.Maze;
import java.util.List;

public class Main {
    private static final int MAZE_WIDTH = 21;
    private static final int MAZE_HEIGHT = 21;

    private Main() {

    }

    @SuppressWarnings("RegexpSinglelineJava")
    public static void main(String[] args) {

        Maze primsMaze = new PrimsMazeGenerator(MAZE_WIDTH, MAZE_HEIGHT).generateMaze();
        primsMaze.setRandomStartExit();
        List<Cell> solvedPrimsMaze = new DFSMazeSolver().solveMaze(primsMaze);
        System.out.println("Алгоритм Прима + DFS:");
        Render.printMaze(primsMaze, solvedPrimsMaze);
        System.out.println();

        Maze recBackMaze = new RecursiveBacktrackingMazeGenerator(MAZE_WIDTH, MAZE_HEIGHT).generateMaze();
        recBackMaze.setRandomStartExit();
        List<Cell> solvedRecBackMaze = new BFSMazeSolver().solveMaze(recBackMaze);
        System.out.println("Рекурсивный возврат + BFS:");
        Render.printMaze(recBackMaze, solvedRecBackMaze);

    }
}
