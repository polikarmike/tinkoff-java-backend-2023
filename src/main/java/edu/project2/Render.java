package edu.project2;

import edu.project2.Types.Cell;
import edu.project2.Types.Maze;
import java.util.Collections;
import java.util.List;

public class Render {
    private static final String WALL = "███";
    private static final String PATH = " • ";
    private static final String START = " S ";
    private static final String EXIT = " E ";
    private static final String EMPTY = "   ";

    private Render() {

    }


    public static void printMaze(Maze maze) {
        printMaze(maze, Collections.emptyList());
    }

    @SuppressWarnings("RegexpSinglelineJava")
    public static void printMaze(Maze maze, List<Cell> path) {
        if (path == null) {
            System.out.println("Путь не найден");
            return;
        }

        for (int i = 0; i < maze.getHeight(); i++) {
            for (int j = 0; j < maze.getWidth(); j++) {
                printCell(maze.cells[i][j], path);
            }
            System.out.print("\n");
        }
    }

    @SuppressWarnings("RegexpSinglelineJava")
    private static void printCell(Cell cell) {
        switch (cell.getType()) {
            case START:
                System.out.print(START);
                break;
            case EXIT:
                System.out.print(EXIT);
                break;
            case EMPTY:
                System.out.print(EMPTY);
                break;
            case WALL:
                System.out.print(WALL);
                break;
            default:
                break;
        }
    }

    @SuppressWarnings("RegexpSinglelineJava")
    private static void printCell(Cell cell, List<Cell> path) {
        if (path.contains(cell) && !cell.isStart() && !cell.isExit()) {
            System.out.print(PATH);
        } else {
            printCell(cell);
        }
    }
}
