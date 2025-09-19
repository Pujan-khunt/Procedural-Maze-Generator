package com.maze.models;

public class MazeCell {
  public boolean left = true, right = true, top = true, bottom = true;
  public boolean visited = false; // All cells are unvisited on maze creation.
  public int x;
  public int y;

  public MazeCell(int x, int y) {
    this.left = true;
    this.right = true;
    this.top = true;
    this.bottom = true;
    this.x = x;
    this.y = y;
  }

  @Override
  public String toString() {
    return "MazeCell [left="
        + left
        + ", right="
        + right
        + ", top="
        + top
        + ", bottom="
        + bottom
        + "]";
  }
}
