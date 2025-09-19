package com.maze.models;

public class MazeCell {
  public boolean left, right, top, bottom;
  public boolean visited;
  public int x;
  public int y;

  public MazeCell(boolean left, boolean right, boolean top, boolean bottom, int x, int y) {
    this.left = left;
    this.right = right;
    this.top = top;
    this.bottom = bottom;
    this.visited = false; // All cells are unvisited when created.
    this.x = x;
    this.y = y;
  }
}
