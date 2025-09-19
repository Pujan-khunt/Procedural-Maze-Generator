package com.maze.models;

public class MazeCell {
  public boolean left, right, top, bottom;
  public boolean visited;
  public int x;
  public int y;

  public MazeCell(int x, int y) {
    this.left = true;
    this.right = true;
    this.top = true;
    this.bottom = true;
    this.visited = false; // All cells are unvisited when created.
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
