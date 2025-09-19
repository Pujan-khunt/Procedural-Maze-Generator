package com.maze.models;

public class Maze {
  private final int width;
  private final int height;
  private int visitedCount;
  private final MazeCell[][] maze;

  public Maze(int width, int height) {
    this.width = width;
    this.height = height;
    this.maze = new MazeCell[this.width][this.height];
    for (int i = 0; i < this.width; i++) {
      for (int j = 0; j < this.height; j++) {
        this.maze[i][j] = new MazeCell(i, j);
      }
    }
  }

  public int getWidth() {
    return this.width;
  }

  public int getHeight() {
    return this.height;
  }

  public MazeCell getMazeCell(int x, int y) {
    return this.maze[x][y];
  }

  public void setMazeCell(MazeCell cell, int x, int y) {
    this.maze[x][y] = cell;
  }

  public void setVisitedCount(int visitedCount) {
    this.visitedCount = visitedCount;
  }

  public int getVisitedCount() {
    return this.visitedCount;
  }
}
