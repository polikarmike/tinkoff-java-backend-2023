package edu;

import com.sun.source.tree.AssertTree;
import edu.project2.Generators.PrimsMazeGenerator;
import edu.project2.Generators.RecursiveBacktrackingMazeGenerator;
import edu.project2.Main;
import edu.project2.Render;
import edu.project2.Solvers.BFSMazeSolver;
import edu.project2.Solvers.DFSMazeSolver;
import edu.project2.Types.Cell;
import edu.project2.Types.Maze;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.ThrowingSupplier;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Project2Test {

    @Test
    @DisplayName("Тест четной размерности лабиринта (создание рамки)")
    public void testEvenMazeDimensions() {
        //Given
        int mazeWidth = 20;
        int mazeHeight = 20;

        // When
        Maze maze = new RecursiveBacktrackingMazeGenerator(mazeWidth, mazeHeight).generateMaze();
        Render.printMaze(maze);

        // Then
        assertTrue(maze.cells[0][0].isWall());
        assertTrue(maze.cells[0][20].isWall());
        assertTrue(maze.cells[20][0].isWall());
        assertTrue(maze.cells[20][20].isWall());
    }

    @Test
    @DisplayName("Тест решения лабиринта Прима с использованием DFS")
    public void testPrimsMazeWithDFSSolver() {
        // Given
        int mazeWidth = 20;
        int mazeHeight = 20;
        Maze primsMaze = new PrimsMazeGenerator(mazeWidth, mazeHeight).generateMaze();
        primsMaze.setRandomStartExit();

        // When
        List<Cell> solvedPrimsMaze = new DFSMazeSolver(primsMaze).solveMaze();

        // Then
        assertNotNull(solvedPrimsMaze);
        System.out.println("Алгоритм Прима + DFS:");
        Render.printMaze(primsMaze, solvedPrimsMaze);
    }

    @Test
    @DisplayName("Тест решения лабиринта Рекурсивного возврата с использованием BFS")
    public void testRecursiveBacktrackingMazeWithBFSSolver() {
        // Given
        int mazeWidth = 20;
        int mazeHeight = 20;
        Maze recBackMaze = new RecursiveBacktrackingMazeGenerator(mazeWidth, mazeHeight).generateMaze();
        recBackMaze.setRandomStartExit();

        // When
        List<Cell> solvedRecBackMaze = new BFSMazeSolver(recBackMaze).solveMaze();

        // Then
        assertNotNull(solvedRecBackMaze);
        System.out.println("Рекурсивный возврат + BFS:");
        Render.printMaze(recBackMaze, solvedRecBackMaze);
    }

    @Test
    @DisplayName("Тест недопустимых размеров маза (4x4)")
    public void testInvalidMazeDimensions() {
        // Given
        int mazeWidth = 4;
        int mazeHeight = 4;

        // When
        assertThrows(IllegalArgumentException.class, () -> {
            new PrimsMazeGenerator(mazeWidth, mazeHeight).generateMaze();
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new RecursiveBacktrackingMazeGenerator(mazeWidth, mazeHeight).generateMaze();
        });
    }

    @Test
    @DisplayName("Тест решателей без установленных стартовой и конечной ячеек")
    public void testSolverWithMissingStartAndExit() {
        //Given
        Maze maze = new Maze(10, 10);

        // When
        List<Cell> solvedDFSMaze = new DFSMazeSolver(maze).solveMaze();
        List<Cell> solvedBFSMaze = new BFSMazeSolver(maze).solveMaze();

        // Then
        assertNull(solvedDFSMaze);
        assertNull(solvedBFSMaze);
    }

    @Test
    @DisplayName("Тест корректного запуска и завершения класса Main")
    public void testMainClassExecution() {
        // Given
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // When
        ThrowingSupplier<Void> mainMethod = () -> {
            Main.main(new String[0]);
            return null;
        };

        // Then
        assertDoesNotThrow(mainMethod);

        String output = outputStream.toString();
        assertTrue(output.contains("Алгоритм Прима + DFS:"));
        assertTrue(output.contains("Рекурсивный возврат + BFS:"));


        System.setOut(originalOut);
    }

}


