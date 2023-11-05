package edu.project2;

import edu.project2.Types.Cell;
import edu.project2.Types.Maze;
import java.util.List;

public class Render {
    private static final String WALL = "███";
    private static final String PATH = " • ";
    private static final String START = " S ";
    private static final String EXIT = " E ";
    private static final String EMPTY = "   ";

    private Render() {

    }

    @SuppressWarnings("RegexpSinglelineJava")
    public static void printMaze(Maze maze) {
        for (int i = 0; i < maze.getHeight(); i++) {
            for (int j = 0; j < maze.getWidth(); j++) {
                printCell(maze.cells[i][j]);
            }

            System.out.println();
        }


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

            System.out.println();
        }
    }

    @SuppressWarnings("RegexpSinglelineJava")
    private static void printCell(Cell cell) {
        if (cell.isStart()) {
            System.out.print(START);
        } else if (cell.isExit()) {
            System.out.print(EXIT);
        } else if (cell.isClear()) {
            System.out.print(EMPTY);
        } else if (cell.isWall()) {
            System.out.print(WALL);
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
