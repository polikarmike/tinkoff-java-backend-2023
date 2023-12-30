package edu.hw9.task3;

import edu.project2.Generators.RecursiveBacktrackingMazeGenerator;
import edu.project2.Solvers.DFSMazeSolver;
import edu.project2.Types.Cell;
import edu.project2.Types.Maze;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class MultiThreadedDFSMazeSolverTest {
    @Test
    @DisplayName("Проверка многопоточного солвера")
    void multiThreadedDFSMazeSolverTest() {
        // Given
        int MAZE_WIDTH = 51;
        int MAZE_HEIGHT = 51;
        Maze recBackMaze = new RecursiveBacktrackingMazeGenerator(MAZE_WIDTH, MAZE_HEIGHT).generateMaze();
        recBackMaze.setRandomStartExit();
        List<Cell> expectedResult = new DFSMazeSolver().solveMaze(recBackMaze);

        // When
        List<Cell> result = new MultiThreadedDFSMazeSolver().solveMaze(recBackMaze);

        // Then
        assertEquals(expectedResult, result);
    }
}
