package com.maze.models;

public class Maze {
  private final int width;
  private final int height;
  final MazeCell[][] maze;

  public Maze(int width, int height) {
    this.width = width;
    this.height = height;
    this.maze = new MazeCell[this.width][this.height];
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }
}
