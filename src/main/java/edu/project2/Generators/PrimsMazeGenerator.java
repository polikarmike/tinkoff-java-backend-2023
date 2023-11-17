package edu.project2.Generators;

import edu.project2.Types.Cell;
import edu.project2.Types.Maze;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PrimsMazeGenerator implements MazeGenerator {
    private final Maze maze;
    private static final Random RANDOM = new Random();

    public PrimsMazeGenerator(int width, int height) {
        this.maze = new Maze(width, height);
    }

    public Maze generateMaze() {

        maze.fillWithWalls();

        Cell startCell = generateRandomStartCell();
        List<Cell> currentCells = new ArrayList<>();
        currentCells.add(startCell);

        while (!currentCells.isEmpty()) {
            int index = RANDOM.nextInt(currentCells.size());
            Cell currentCell = currentCells.get(index);
            currentCells.remove(index);

            List<Cell> neighborsList = maze.getNeighbors(currentCell, 2);
            Collections.shuffle(neighborsList);

            for (Cell neighborCell : neighborsList) {
                if (neighborCell.isWall()) {
                    neighborCell.setType(Cell.CellType.EMPTY);
                    maze.removeWallsBetweenCells(currentCell, neighborCell);
                    currentCells.add(neighborCell);
                }
            }
        }

        return maze;
    }

    private Cell generateRandomStartCell() {
        int startX = RANDOM.nextInt(maze.getWidth() / 2) * 2 + 1;
        int startY = RANDOM.nextInt(maze.getHeight() / 2) * 2 + 1;

        return maze.cells[startY][startX];
    }

}

