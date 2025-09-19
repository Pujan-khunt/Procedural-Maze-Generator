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
      int neighboringCellX = cell.x + directions[i][0];
      int neighboringCellY = cell.y + directions[i][1];

      boolean isValidNeighboringCell =
          isValidCell(neighboringCellX, neighboringCellY, mazeWidth, mazeHeight)
              && !isVisited(maze.getMazeCell(neighboringCellX, neighboringCellY));
      MazeCell neighboringCell = null;
      if (isValidNeighboringCell) {
        neighboringCell = maze.getMazeCell(neighboringCellX, neighboringCellY);
      }
      neighbors[i] = new Neighbor(neighboringCell, isValidNeighboringCell);
    }

    return getRandomNeighbor(neighbors);
  }

  private boolean isValidCell(int cellX, int cellY, int mazeWidth, int mazeHeight) {
    return cellX >= 0 && cellX < mazeWidth && cellY >= 0 && cellY < mazeHeight;
  }

  private boolean isVisited(MazeCell cell) {
    return cell.visited;
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
      double prob = i / countOfValidNeighbors;
      if (randomValue <= prob) {
        index = i - 1;
      }
    }

    return validNeighbors.get(index);
  }

  private void visit(MazeCell source, MazeCell destination, Maze maze) {
    maze.setVisitedCount(maze.getVisitedCount() + 1);
    source.visited = true;
    if (destination == null) {
      return;
    }

    // y_diff and x_diff can only have 3 possible value: 0, 1, -1
    // NOTE: x_diff and y_diff can't both have same value.
    int y_diff = source.y - destination.y;
    int x_diff = source.x - destination.x;

    // Removing walls from source and destination cells.
    if (y_diff == 1) {
      source.left = false;
      destination.right = false;
    } else if (y_diff == -1) {
      source.right = false;
      destination.left = false;
    } else if (x_diff == 1) {
      source.top = false;
      destination.bottom = false;
    } else if (x_diff == -1) {
      source.bottom = false;
      destination.top = false;
    } else {
      throw new IllegalStateException(
          "Y/X difference can be either 1/-1 both can't be zero/other value at the same time. "
              + "X diff: "
              + x_diff
              + ", Y diff: "
              + y_diff);
    }
  }

  @Override
  public void generate(Maze maze, MazeCell initialCell) {
    // Stack to keep track of the path and backtrack.
    Stack<MazeCell> backtrack = new Stack<>();

    // Storing copy to restore paramter later.
    MazeCell initialCellCopy = initialCell;

    // Visit and add starting cell to stack.
    visit(initialCell, null, maze);
    backtrack.add(initialCell);

    // Loop until all cells are visited.
    int mazeHeight = maze.getHeight();
    int mazeWidth = maze.getWidth();
    while (maze.getVisitedCount() != mazeHeight * mazeWidth) {
      MazeCell randomValidNeighbor = getRandomValidNeighbor(maze, initialCell);
      if (randomValidNeighbor == null) {
        // No valid remaining neighbors, backtrack.
        backtrack.pop();
        // initialCell = backtrack.peek();
        continue;
      }
      visit(initialCell, randomValidNeighbor, maze);
      backtrack.add(randomValidNeighbor);
      initialCell = randomValidNeighbor;
    }

    // Update function parameter to original value.
    initialCell = initialCellCopy;
  }
}
