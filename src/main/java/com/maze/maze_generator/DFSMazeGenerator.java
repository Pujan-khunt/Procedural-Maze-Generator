package com.maze.maze_generator;

import com.maze.models.Maze;
import com.maze.models.MazeCell;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DFSMazeGenerator implements MazeGenerator {
  static class Neighbor {
    MazeCell cell;
    boolean isValid;

    public Neighbor(MazeCell cell, boolean isValid) {
      this.cell = cell;
      this.isValid = isValid;
    }
  }

  /**
   * Find a neighbor which is within the maze boundaries.
   *
   * @param maze Maze for checking boundaries.
   * @param cell cell of which neighbors are needed.
   */
  private MazeCell getRandomValidNeighbor(Maze maze, MazeCell cell) {
    int mazeWidth = maze.getWidth();
    int mazeHeight = maze.getHeight();

    // Represents neighbors in clockwise order.
    // 0 -> Left
    // 1 -> Top
    // 2 -> Right
    // 3 -> Bottom
    Neighbor[] neighbors = new Neighbor[4];

    // Directions in clockwise order.
    int[][] directions = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

    for (int i = 0; i < 4; i++) {
      MazeCell neighboringCell =
          maze.getMazeCell(cell.x + directions[i][0], cell.y + directions[i][1]);
      boolean isVaildNeighboringCell = isValidCell(cell, mazeWidth, mazeHeight);
      neighbors[i] = new Neighbor(neighboringCell, isVaildNeighboringCell);
    }

    return getRandomNeighbor(neighbors);
  }

  private MazeCell getRandomNeighbor(Neighbor[] neighbors) {
    // Stores the indices of all valid neighbors.
    List<MazeCell> validNeighbors = new ArrayList<>();

    // Determine the count of valid neighbors.
    int countOfValidNeighbors = 0;
    for (int i = 0; i < 4; i++) {
      if (neighbors[i].isValid) {
        countOfValidNeighbors++;
        validNeighbors.add(neighbors[i].cell);
      }
    }

    if (countOfValidNeighbors == 0) {
      return null;
    }

    double randomValue = Math.random();
    int index = 0;
    for (int i = 1; i <= countOfValidNeighbors; i++) {
      if (randomValue <= i / countOfValidNeighbors) {
        index = i - 1;
      }
    }

    return validNeighbors.get(index);
  }

  private boolean isValidCell(MazeCell cell, int mazeWidth, int mazeHeight) {
    return cell.x >= 0 && cell.x < mazeWidth && cell.y >= 0 && cell.y < mazeHeight;
  }

  @Override
  public MazeCell[][] generate(Maze maze, MazeCell initalCell) {
    Stack<MazeCell> backtrack = new Stack<>();

    MazeCell randomValidNeighbor = getRandomValidNeighbor(maze, initalCell);

    return null;
  }
}
